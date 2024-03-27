### 이전 코드에서 존재하던, 데이터 가공을 위해 viewModel에서 collect 하는 것은 불필요한 처리였습니다.
해당 내용 수정했습니다.

#### AS-IS
viewModel에서 1번 + activity에서 1번 : 총 2번의 collect가 존재

#### TO-BE
viewModel에서 collect 대신 map으로 데이터 가공 : 불필요한 collect 제거, 1번의 collect
