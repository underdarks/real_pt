## 헬스장 관리
 헬스장 이용에 관련된 가격(이용료), 시설 사진, 기구, 운영시간 등 헬스장 정보를 관리합니다.
<br>
<br>


## API 리스트

- [헬스장 정보 등록](#헬스장-정보-등록)
- [헬스장 정보 수정](#헬스장-정보-수정)
- [헬스장 정보 조회](#헬스장-정보-조회)
- [헬스장 정보 삭제](#헬스장-정보-삭제)
- [가격 등록](#가격-등록)
- [가격 수정](#가격-수정)
- [가격 조회](#가격-조회)
- [가격 삭제](#가격-삭제)


###### [> code](https://github.com/underdarks/real_pt/blob/main/doc/member/code.md)

## API 상세 설명 
 
### 헬스장 정보 등록
 헬스장 이용시 필요한 정보들을 등록합니다.(이미지 다중 업로드 기능 제공)<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/gym|![정보 등록 Data](https://user-images.githubusercontent.com/41244406/165020103-1118eaa5-30b3-47e8-b134-294ba01fa1ba.PNG)|

<br>
 
> #### Response
##### 요청 성공

![헬스장 등록 성공](https://user-images.githubusercontent.com/41244406/165020909-778dd3de-e5f8-464a-8eab-ae22f7cb39e2.PNG)


- - -


### 헬스장 정보 수정
 헬스장 졍보를 수정합니다.(필요에 따라 이미지 추가, 삭제가 가능합니다)<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/gym|![정보 등록 Data](https://user-images.githubusercontent.com/41244406/165020103-1118eaa5-30b3-47e8-b134-294ba01fa1ba.PNG)|

<br>
 
> #### Response
##### 요청 성공

![헬스장 등록 성공](https://user-images.githubusercontent.com/41244406/165020909-778dd3de-e5f8-464a-8eab-ae22f7cb39e2.PNG)


- - -


### 헬스장 정보 조회
 등록된 헬스장에 대한 정보를 조회합니다.<br>
 추후 앱에서 GPS 위치기반 기능이 들어가면 위치 기반에 있는 헬스장의 리스트 
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/gym|![정보 등록 Data](https://user-images.githubusercontent.com/41244406/165020103-1118eaa5-30b3-47e8-b134-294ba01fa1ba.PNG)|

<br>
 
> #### Response
##### 요청 성공

![헬스장 등록 성공](https://user-images.githubusercontent.com/41244406/165020909-778dd3de-e5f8-464a-8eab-ae22f7cb39e2.PNG)


- - -



### 헬스장 정보 삭제
 헬스장 이용시 필요한 정보들을 등록합니다.(이미지 다중 업로드 기능 제공)<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/gym|![정보 등록 Data](https://user-images.githubusercontent.com/41244406/165020103-1118eaa5-30b3-47e8-b134-294ba01fa1ba.PNG)|

<br>
 
> #### Response
##### 요청 성공

![헬스장 등록 성공](https://user-images.githubusercontent.com/41244406/165020909-778dd3de-e5f8-464a-8eab-ae22f7cb39e2.PNG)


- - -






