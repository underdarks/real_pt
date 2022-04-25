## 헬스장 관리
 헬스장 이용에 관련된 가격(이용료), 시설 사진, 기구, 운영시간 등 헬스장 정보를 관리합니다.
 <br><br>
 
## API 리스트

- [헬스장 정보 등록](#헬스장-정보-등록)
- [헬스장 정보 수정](#헬스장-정보-수정)
- [헬스장 정보 조회](#헬스장-정보-조회)
- [헬스장 정보 삭제](#헬스장-정보-삭제)
- [PT 조회](#PT-조회)
- [가격 등록](#가격-등록)
- [가격 수정](#가격-수정)
- [가격 조회](#가격-조회)
- [가격 삭제](#가격-삭제)


## API 상세 설명 
 
### 헬스장 정보 등록
 헬스장 이용시 필요한 정보(가격, 시설 사진, 기구, 운영시간, 위치 등)들을 등록합니다.(이미지 다중 업로드 기능 제공)
 <br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/gym|![정보 등록 Data](https://user-images.githubusercontent.com/41244406/165020103-1118eaa5-30b3-47e8-b134-294ba01fa1ba.PNG)|

<br>
 
> #### Response
##### 요청 성공

![헬스장 등록 성공](https://user-images.githubusercontent.com/41244406/165020909-778dd3de-e5f8-464a-8eab-ae22f7cb39e2.PNG)

##### 요청 실패

![등록 실패](https://user-images.githubusercontent.com/41244406/165028924-aefd7f0d-592e-433d-a851-ac7384d0503f.PNG)


- - -


### 헬스장 정보 수정
 헬스장 졍보를 수정합니다.(필요에 따라 이미지 추가, 삭제가 가능합니다)
 <br><br>
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|PATCH|http://localhost:8080/api/v1/gym/id|수정할 데이터|

<br>
 
> #### Response
##### 요청 성공

![수정 성공](https://user-images.githubusercontent.com/41244406/165028807-6caa0e5d-4d69-4b52-97c5-ba964da007d1.PNG)


##### 요청 실패

![수정 실패](https://user-images.githubusercontent.com/41244406/165028819-8f398eb1-5dea-4114-aa70-cf8247630740.PNG)


- - -


### 헬스장 정보 조회
 등록된 헬스장에 대한 정보를 조회합니다.<br>
 추후 앱에서 GPS 위치를 통하여 주변 헬스장을 볼 수 있게 제공할 예정입니다.<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|GET|http://localhost:8080/api/v1/gym/id||

<br>
 
> #### Response
##### 요청 성공

![헬스장 조회 성공](https://user-images.githubusercontent.com/41244406/165027857-854378f0-5438-4d14-b2e4-68e9e9108188.PNG)


##### 업로드 이미지 조회

![헬스장 이미지](https://user-images.githubusercontent.com/41244406/165027910-42563387-2a33-4636-a7a8-783df0dd2500.PNG)

##### 요청 실패

![헬스장 조회 실패](https://user-images.githubusercontent.com/41244406/165027863-d66de9be-bedc-479c-9ee8-76ff44048e9c.PNG)


- - -


### 헬스장 정보 삭제
 헬스장 정보를 삭제합니다.<br>
 헬스장 삭제 시 Member, GymImage, GymPrice와 연관관계를 가지고 있어 GymImage와 GymPrice는 cascade = CascadeType.DELETE, orphanRemoval = true 옵션을 통하여 같이 삭제되게 하였으며, 헬스장에 속한 PT(Member)같은 경우 실제 데이터를 삭제를 하지 않고 Gym <-> Member간의 연관관계를 끊었습니다(null로 값 대입) 
 
 
 <br><br>
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|DELETE|http://localhost:8080/api/v1/gym/id||

<br>
 
> #### Response
##### 요청 성공

![헬스장 삭제](https://user-images.githubusercontent.com/41244406/165055333-0a517def-9afb-4ba0-9dac-a5da592e9327.PNG)


- - -


### PT 조회
 헬스장에 근무하는 PT(Persional Trainer)들을 조회합니다.
 <br><br>
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|GET|http://localhost:8080/api/v1/gym/gym-id/pt||

<br>
 
> #### Response
##### 요청 성공

![헬스장 소속 pt 조회](https://user-images.githubusercontent.com/41244406/165034025-bc9e4d6f-548c-49cd-b96b-c1a46182af87.PNG)


##### PT 대표 이미지 

![PT 대표 이미지](https://user-images.githubusercontent.com/41244406/165034068-f971aeb3-9c78-4069-822c-43740bd6c10c.PNG)


- - -

### 가격 등록
 헬스장 가격(이용료)을 등록합니다.
 <br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/gym/gym-id/price|![요청 데이터](https://user-images.githubusercontent.com/41244406/165035278-49d05ce3-1e44-4a22-a0ad-03ee18bdcd35.PNG)|

<br>
 
> #### Response
##### 요청 성공

![헬스장 가격 등록 성공](https://user-images.githubusercontent.com/41244406/165035022-1b1edd41-f2ec-4fb7-a5f2-4d5bfb861514.PNG)



##### 요청 실패

![가격 등록 실패](https://user-images.githubusercontent.com/41244406/165035026-b8e331f8-8758-419c-8939-a1cd595f4946.PNG)

- - -


### 가격 수정
 헬스장 가격(이용료)을 수정합니다.
 <br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|PATCH|http://localhost:8080/api/v1/gym/gym-id/price/id|수정할 데이터|

<br>
 
> #### Response
##### 요청 성공

![가격 수정 성공](https://user-images.githubusercontent.com/41244406/165052187-f0462ec7-049d-4a0f-8cba-d3486899d50e.PNG)



##### 요청 실패

![가격 수정 실패](https://user-images.githubusercontent.com/41244406/165052201-44dc7dde-a3c2-4697-8f28-32b755af8576.PNG)


- - -

### 가격 조회
 헬스장 가격(이용료)들을 조회합니다.
 <br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|GET|http://localhost:8080/api/v1/gym/gym-id/price||

<br>
 
> #### Response
##### 요청 성공

![가격 조회](https://user-images.githubusercontent.com/41244406/165053582-51da1a53-5f51-471d-b9e0-1322dd50edd0.PNG)



##### 등록된 가격 없는 경우

![가격 조회 없음](https://user-images.githubusercontent.com/41244406/165053589-befb95d9-a988-470d-9ff6-69ab8516e3aa.PNG)


- - -


### 가격 삭제
 헬스장 가격을 삭제합니다.
 <br><br>
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|DELETE|http://localhost:8080/api/v1/gym/gym-id/price/id||

<br>
 
> #### Response
##### 요청 성공

![가격 삭제 성공](https://user-images.githubusercontent.com/41244406/165054199-12175458-2272-45ca-ad4f-b5172322a1b9.PNG)


##### 요청 실패

![가격 삭제 실패](https://user-images.githubusercontent.com/41244406/165054208-4fe2c908-2cdb-4d84-9955-103f39c77029.PNG)




