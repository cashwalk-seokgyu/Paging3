package com.andcorns2.paging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.andcorns2.paging3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var itemAdapter : ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setAdapter()

        lifecycleScope.launch {
            mainViewModel.list.collect{
                itemAdapter.submitData(it)
            }
        }
        /**
         * 아이템 삭제 이후 PagingDataAdapter의 refresh를 호출하여 UI 갱신
         */
        mainViewModel.itemDeleteObserve.observe(this) {
            itemAdapter.refresh()
        }
    }

    private fun setAdapter() {
        itemAdapter = ItemAdapter()

        binding.rv.run {
            adapter = itemAdapter.apply {
                removeListener(object : ItemAdapter.CustomListenerInterface {
                    override fun removeListener(position: Int, sampleData: ItemData) {
                        mainViewModel.setItemDelete(sampleData.title)
                    }
                })

                addLoadStateListener { loadState ->
                    when (loadState.source.refresh) {
                        is LoadState.Loading -> {

                        }

                        else -> {

                        }
                    }
                }
            }

            itemAnimator = null
            layoutManager = LinearLayoutManager(binding.rv.context)
        }
    }
}