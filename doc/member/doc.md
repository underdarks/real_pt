## 회원 관리
헬스장과 관련된 회원들의 정보를 관리합니다.
<br>

## 회원 관리 API 기능

- [회원가입](#회원-가입)
- [로그인](#로그인)
- [회원 수정](#회원-수정)
- [회원 조회](#회원-조회)
- [회원 탈퇴](#회원-탈퇴)



## API 상세 설명 
 
### 회원 가입



![회원등록 헤더](https://user-images.githubusercontent.com/41244406/161992486-fddd2cff-2f62-4eec-91d9-7866bf40c8f4.PNG)




### 로그인

<br>
회원 정보 입력 후 Post(http://localhost:8080/api/v1/member)로 요청하여 회원 가입을 한다.<br>
Response값은 PK값을 반환한다.<br><br>

![회원등록](https://user-images.githubusercontent.com/41244406/161994318-47335e27-4228-4686-a80b-2117edcb339a.PNG)


----------------------------------

<h3>회원 조회</h3>

<h4>전체 회원 조회</h4>
Get방식으로(http://localhost:8080/api/v1/member) 요청하여 모든 회원을 조회한다.<br><br>

![회원 전체 조회](https://user-images.githubusercontent.com/41244406/162015320-660fade4-9577-4eaa-9d54-9631ac18fb27.PNG)


<h4>단일 회원 조회</h4>
Get 방식으로(http://localhost:8080/api/v1/member/id) uri에 id(pk)를 같이 넘겨 회원을 조회한다.<br><br>

![단일 회원 조회](https://user-images.githubusercontent.com/41244406/162016767-1fb7a4b3-185d-42b4-bd1c-3eafb2f95446.PNG)


----------------------------------

<h3>회원 수정</h3>
PATCH 방식으로 요청하여(http://localhost:8080/api/v1/member/id) uri에 id(pk)와 HttpBody에 수정할 내용을 넘겨 회원 정보를 수정한다.<br>
Response는 수정된 정보가 반한된다<br><br>

![회원 수정](https://user-images.githubusercontent.com/41244406/162107477-37e722bd-9784-4156-b73e-031a9e70ba73.PNG)


----------------------------------
<h3>회원 탈퇴</h3>
DELETE 방식으로 요청하여(http://localhost:8080/api/v1/member/id) uri에 id를 넘겨 회원 탈퇴를 한다.<br><br>

![회원 삭제](https://user-images.githubusercontent.com/41244406/162109122-93fb24c4-7dac-41f1-a6be-4ead7aff6091.PNG)


<br>
<h2>API 문서 관리</h2>
swaager를 활용하여 API 문서 관리 자동화<br><br>

![swagger](https://user-images.githubusercontent.com/41244406/161994284-f5e6226e-8c96-40eb-9e45-208e8a93fb9d.PNG)



