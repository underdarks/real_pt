## PT(Personal Trainer) 관리
 PT 이용에 관련된 정보(PT 가격, 리뷰 등록, 리뷰 사진 등록, 댓글 좋아요/싫어요 등..)를 관리합니다.
 <br><br>
 
## API 리스트

- [리뷰 등록](#리뷰-등록)
- [리뷰 수정](#리뷰-수정)
- [리뷰 조회](#리뷰-조회)
- [리뷰 삭제](#리뷰-삭제)
- [좋아요](#좋아요)
- [싫어요](#싫어요)
- [가격 등록](#가격-등록)
- [가격 수정](#PT-가격-수정)
- [가격 조회](#PT-가격-조회)
- [가격 삭제](#PT-가격-삭제)
<br>

## API 상세 설명 
 
### 리뷰 등록
 PT에 대한 리뷰를 등록합니다.(리뷰 내용, 별점, 리뷰 사진 등..)
 <br><br>
 
  
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/pt/pt-id/review|![요청 데이터](https://user-images.githubusercontent.com/41244406/165116421-4f29e317-19c9-4297-add8-22521b99b860.PNG)|

<br>
 
> #### Response
##### 요청 성공

![리뷰 등록 성공](https://user-images.githubusercontent.com/41244406/165121697-88a003a5-8820-463d-92c6-376bbfe71bf1.PNG)


##### 요청 실패

![리뷰 등록 실패](https://user-images.githubusercontent.com/41244406/165121715-999da863-5dc4-404f-ab56-496dc1fbf2d2.PNG)

- - -

### 리뷰 수정
 PT 리뷰 내용을 수정합니다.
 <br><br>
 
  
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|PATCH|http://localhost:8080/api/v1/pt/pt-id/review/review-id|수정할 데이터|

<br>
 
> #### Response
##### 요청 성공

![리뷰 수정 성공](https://user-images.githubusercontent.com/41244406/165122140-1fedbfbd-8104-4c32-b843-ce9b0bc5ac6a.PNG)


##### 요청 실패

![리뷰 수정 실패](https://user-images.githubusercontent.com/41244406/165122160-c65b2d73-2a0d-4285-ac61-0eef8f9ea98c.PNG)

- - -

### 리뷰 조회
 PT 리뷰 내용을 조회합니다.<br>
 조회 시 리뷰 좋아요 많은 순, 싫어요 많은 순, 별점 높은 순 등... 리뷰 리스트에 대한 정렬 기능을 제공합니다.
 <br><br>
 
  
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|GET|http://localhost:8080/api/v1/pt/pt-id/review||

<br>
 
> #### Response
##### 요청 성공

![리뷰 조회 성공](https://user-images.githubusercontent.com/41244406/165122478-9001d77d-a0cb-4582-b87c-c8877b8303f9.PNG)

##### 리뷰 이미지 조회

![리뷰 조회 이미지 업로드](https://user-images.githubusercontent.com/41244406/165123077-509e9fa0-c3ae-47d5-9c5f-f7b9b174de62.PNG)


##### 등록된 리뷰 없는 경우

![리뷰 조회 데이터 없음](https://user-images.githubusercontent.com/41244406/165123018-6703c747-d538-47e1-bedc-2b8b922dbbbd.PNG)

- - -

### 리뷰 삭제
 PT 리뷰 내용을 삭제합니다.<br>
 리뷰 삭제 시 리뷰 사진도 함께 삭제됩니다.
 <br><br>
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|DELETE|http://localhost:8080/api/v1/pt/pt-id/review/review-id||

<br>
 
> #### Response
##### 요청 성공

![리뷰 삭제 성공](https://user-images.githubusercontent.com/41244406/165123938-6b979058-04fb-4073-b6a6-3763aeac0fec.PNG)



 
