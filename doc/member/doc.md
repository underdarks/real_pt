## 회원 관리
 헬스장 이용자, PT 등 회원들의 정보를 관리합니다.
<br>
<br>


## 회원 관리 API 기능

- [회원가입](#회원-가입)
- [로그인](#로그인)
- [회원 수정](#회원-수정)
- [회원 조회](#회원-조회)
- [회원 탈퇴](#회원-탈퇴)


###### [> code](https://github.com/underdarks/real_pt/blob/main/doc/member/code.md)

## API 상세 설명 
 
### 회원 가입
 회원 정보를 등록하고 회원 가입을 합니다.(이미지 다중 업로드 기능 제공)<br><br>
 회원 가입시 회원의 권한은 "ROLE_USER"로 Default 값을 가집니다.
 
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/member|![회원등록_데이터](https://user-images.githubusercontent.com/41244406/164993929-3625d6f5-d78e-4bdc-8b91-f563f73cece7.PNG)|

<br>
 
> #### Response
##### 요청 성공

![회원등록 성공](https://user-images.githubusercontent.com/41244406/164994552-b5561169-2c56-4520-ad32-004f07bea38e.PNG)

##### 요청 실패

![회원등록_실패](https://user-images.githubusercontent.com/41244406/164993483-68d80f2b-dde4-4ccc-a903-3c1e6ed6280d.PNG)

- - -

### 로그인
id와 pw를 넘겨 로그인 요청을 합니다. 로그인 성공시 응답으로 JWT 토큰 받습니다.<br><br>
**JWT 토큰을 활용하여 사용자 인증(Authentication)과 권한(Authorization)을 관리합니다.**
토큰의 유효시간은 60분입니다.<br><br>

> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/member/login|![로그인_데이터](https://user-images.githubusercontent.com/41244406/164994443-a0bf6970-2018-4f23-b1ba-f19d8e337af9.PNG)|

<br>
 
> #### Response
##### 요청 성공

![로그인 성공](https://user-images.githubusercontent.com/41244406/164994541-1c61348c-f2fe-4d57-ad53-20753182b322.PNG)


- - -


### 회원 수정
 회원 정보를 수정합니다.<br><br>
 
> #### Request
 |메서드|요청 URL|요청 Data|
|----|------|--------------|
|POST|http://localhost:8080/api/v1/member|![회원등록_데이터](https://user-images.githubusercontent.com/41244406/164993929-3625d6f5-d78e-4bdc-8b91-f563f73cece7.PNG)|

<br>
 
> #### Response
##### 요청 성공

![회원등록 성공](https://user-images.githubusercontent.com/41244406/164994552-b5561169-2c56-4520-ad32-004f07bea38e.PNG)

##### 요청 실패

![회원등록_실패](https://user-images.githubusercontent.com/41244406/164993483-68d80f2b-dde4-4ccc-a903-3c1e6ed6280d.PNG)

