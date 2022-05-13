## 회원 관리
 헬스장 이용자, PT 등 회원들의 정보를 관리합니다.
<br>
<br>


## API 리스트

- [회원가입](#회원-가입)
- [로그인](#로그인)
- [회원 수정](#회원-수정)
- [회원 조회](#회원-조회)
- [회원 탈퇴](#회원-탈퇴)


###### [> code](https://github.com/underdarks/real_pt/blob/main/doc/member/code.md)

## API 상세 설명 
 
### 회원 가입
 회원 정보(id, pw, email, 사진 등)를 등록하고 회원 가입을 합니다.(이미지 다중 업로드 기능 제공)<br>
 회원타입은 헬스장 이용자, PT(Personal Trainer), 대표로 구분합니다. 그리고 회원 가입시 회원의 권한은 "ROLE_USER"로 Default 값을 가집니다.<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://3.39.200.110:8080/api/v1/member|![회원등록_데이터](https://user-images.githubusercontent.com/41244406/164993929-3625d6f5-d78e-4bdc-8b91-f563f73cece7.PNG)|

<br>
 
> #### Response
##### 요청 성공

![회원등록 성공](https://user-images.githubusercontent.com/41244406/164994552-b5561169-2c56-4520-ad32-004f07bea38e.PNG)

##### 요청 실패

![회원등록_실패](https://user-images.githubusercontent.com/41244406/164993483-68d80f2b-dde4-4ccc-a903-3c1e6ed6280d.PNG)

- - -

### 로그인
id와 pw를 넘겨 로그인 요청을 합니다. 로그인 성공시 응답으로 JWT 토큰 받습니다.<br>
**JWT 토큰을 활용하여 사용자 인증(Authentication)과 권한(Authorization)을 관리합니다.**<br>
회원과 관련된 API 요청 시 응답으로 받은 토큰 함께 보내지 않으면 403 Forbidden 에러를 응답합니다.<br><br>

> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://3.39.200.110:8080/api/v1/member/login|![로그인_데이터](https://user-images.githubusercontent.com/41244406/164994443-a0bf6970-2018-4f23-b1ba-f19d8e337af9.PNG)|

<br>
 
> #### Response
##### 요청 성공

![로그인 성공](https://user-images.githubusercontent.com/41244406/164994541-1c61348c-f2fe-4d57-ad53-20753182b322.PNG)

- - -

### 회원 수정
 회원 정보를 수정합니다.<br>
 회원 정보 수정 시 HttpHeader에 JWT 토큰을 같이 보내 인증과 권한 확인 후 진행합니다.<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|PATCH|http://3.39.200.110:8080/api/v1/member/id|수정할 데이터 요청|

<br>
 
> #### Response
##### 요청 성공

![회원 수정_성공](https://user-images.githubusercontent.com/41244406/164995190-a8fa4d60-082e-4ae0-8b58-190e5eaf5163.PNG)


##### 요청 실패(권한없음)

![권한 실패](https://user-images.githubusercontent.com/41244406/164995199-b317e45b-c5f6-4304-a105-35618b9c2c59.PNG)

- - -

### 회원 조회
 회원 정보를 조회합니다.<br>
 회원 정보 조회 시 HttpHeader에 JWT 토큰을 같이 보내 인증과 권한 확인 후 진행합니다.<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|GET|http://3.39.200.110:8080/api/v1/member/id||

<br>
 
> #### Response
##### 요청 성공

![회원조회_성공](https://user-images.githubusercontent.com/41244406/164995449-52399cfb-224e-4f41-a279-19c57002527f.PNG)


###### 업로드 이미지 파일 조회
![회원 이미지 조회](https://user-images.githubusercontent.com/41244406/164995459-e7d1ecda-23d0-49aa-8ea5-368af7b4e5f6.PNG)


##### 요청 실패

![회원조회_실패](https://user-images.githubusercontent.com/41244406/164995453-2d71dda5-6ae1-4a27-8ed1-bf2da6f64520.PNG)

- - -

### 회원 탈퇴
 회원 정보를 삭제(탈퇴)합니다.<br>
 회원 정보 삭제 시 HttpHeader에 JWT 토큰을 같이 보내 인증과 권한 확인 후 진행합니다.<br>
 
 회원 삭제 같은 경우 Gym, MemberImage, PtReview, PtPRice와 연관관계를 가지고 있어 삭제 시 고려 사항이 있습니다.<br>
 **cascade = CascadeType.DELETE, orphanRemoval = true**  옵션을 활용하여 양방향 매핑되어 있는 엔티티간의 조건을 고려해서 삭제합니다.<br><br>
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|DELETE|http://localhost:8080/api/v1/member/id||

<br>
 
> #### Response
##### 요청 성공

![회원탈퇴_성공](https://user-images.githubusercontent.com/41244406/164995935-d028b076-d8a9-40ff-894c-8c1bf6db5e0a.PNG)








