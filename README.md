# Gym꾼
 <br>

<h2>프로젝트 개요</h2>

 저는 운동하는 것을 좋아합니다. 그중 헬스를 즐겨 하는데 헬스장을 가보면 운동을 처음 시작하시는 분들(=헬린이)이 처음 운동을 시작하려고 헬스에 오면 뭐부터 할지 막막하고 운동을 해도 본인이 정확하게 운동을 하고 있는지도 의문이 들고 막막합니다.

그래서 YouTube를 통해서 스스로 공부해서 운동하거나 PT 등 외부적인 도움을 받습니다.(혹은 운동에 흥미를 잃어 중간에 포기하게 됩니다)

그래서 저는 헬스를 처음 시작하는 사람들(혹은 운동을 좋아하는 사람들) 위한 헬스 커뮤니티, PT 리뷰, 헬스장 정보 제공 등 서비스를 제공하여 누구나 재미있게, 쉽게, 함께 운동할 수 있는 문화를 만들려고 합니다.


<br>

<h2>서비스 기능 설명</h2>

- <Strong>Rest API를 제공하여 API 통신 기능 제공</Strong>
- <Strong>회원 관리 API 제공</Strong>
  - 회원가입
  - 아이디 찾기 
  - 비밀번호 찾기
  - 회원 수정 
  - 회원 조회
  - 회원 탈퇴
 
- <Strong>헬스장(Gym) 관리 API 제공</Strong>
  - Gym 등록
  - Gym 수정
  - Gym 조회
  - Gym 삭제
  
- <Strong>헬스장 가격(GymPrice) 관리 API 제공</Strong>
  - 가격 등록(월단위 ex. 1개월, 3개월, 6개월..)
  - 가격 조회
  - 가격 수정
  - 가격 삭제

- <Strong>헬스장 리뷰 (GymReview) 관리 API 제공</Strong>
  - 리뷰 등록(별점, 리뷰 내용, 사진 업로드, 리뷰 댓글 기능 제공) 
  - 리뷰 조회
  - 리뷰 수정
  - 리뷰 삭제

- <Strong>Personal Training(PT) 관리 API 제공</Strong>
  - PT 등록
  - PT 수정
  - PT 조회
  - PT 삭제
  
- <Strong>PT 가격(PtPrice) 관리 API 제공</Strong>
  - 가격 등록(횟수 단위 ex.10회 20회 30회.... )
  - 가격 조회
  - 가격 수정
  - 가격 삭제

- <Strong>PT 리뷰 (PtReview) 관리 API 제공</Strong>
  - 리뷰 등록(별점, 리뷰 내용, 사진 업로드, 리뷰 댓글 기능 제공) 
  - 리뷰 조회
  - 리뷰 수정
  - 리뷰 삭제

<br>
<h2>개발 진행 중인 Tasks</h2>

- <Strong>헬스 커뮤니티(글쓰기, 답변달기, 글 수정, 글 삭제 ...)</Strong>
- <Strong>1:1 대화 기능</Strong>
- <Strong>SNS 로그인 연동(KaKaoTalk, FaceBook 등..)</Strong>
  
  
<br>

<h2>개발 환경</h2>

- Spring Boot 2.6.3
- JPA (Hibernate)
- Java 11.0.13
- Gradle 
- Lombok
- Mysql 8.0.27

![1](https://user-images.githubusercontent.com/41244406/159150270-39c95cdd-b8a3-4f66-9337-2bd713663447.PNG)

<br>
<h2>API 테스트 및 관리 </h2>
 PostMan을 사용하여 테스트를 진행하고 있으며 Swagger를 사용하여 API 문서 관리를 하고 있습니다.

<br>
<Strong>PostMan</Strong>
<br>
-회원 등록 Test

![memberEnroll](https://user-images.githubusercontent.com/41244406/159385045-1e475c34-d16b-4f7d-8c2d-54f7e4b7c99f.PNG)

<br>
회원 조회 Test

![memberSearch](https://user-images.githubusercontent.com/41244406/159206823-9e3b9220-b8a5-421c-a00a-73f8e72b69b3.png)



<br>
<Strong>Swagger</Strong>

![api swagger(member)](https://user-images.githubusercontent.com/41244406/159385122-eb54c82b-4cb9-40c9-81e3-3c99af7a3e6c.PNG)



