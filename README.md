# Gym꾼
 <br>

<h2>프로젝트 개요</h2>

 저는 운동하는 것을 좋아합니다. 헬스를 즐겨하는데 헬스장을 가보면 운동을 처음 시작하시는 분들(=헬린이)이 처음 운동을 시작하려고 헬스장에 오면 뭐부터 할지 막막하고 운동을 해도 본인이 정확하게 운동을 하고 있는지 힘들고 답답합니다.

그래서 YouTube를 통해서 스스로 공부해서 운동하거나 PT 등 외부적인 도움을 받습니다.(혹은 운동에 흥미를 잃어 중간에 포기하게 됩니다) PT(Personal Training)를 받는 경우 운동에 대해 잘 알려주는 PT가 있는 반면 제대로 알려주지 않고 시간만 때우는 PT들도 있습니다.

돈과 시간만 날리고 운동에 흥미를 잃게 되고, 헬스장에 잘 나오지 않게 됩니다

그래서 저는 이러한 문제점들을 해결하기 위해 헬스를 처음 시작하는 사람들(혹은 운동을 좋아하는 사람들) 위한 커뮤니티 공간, 리뷰를 통한 좋은 PT 찾기, 유용한 헬스장 정보 등의 서비스를 제공하여 누구나 재미있게, 쉽게, 함께 운동할 수 있는 문화를 만들려고 합니다.

<br>

<h2>서비스 기능 설명</h2>

- [Rest API 통신 기능 제공](https://github.com/underdarks/real_pt/blob/main/doc/common/readme.md)
- [회원 관리 API](https://github.com/underdarks/real_pt/blob/main/doc/member/doc.md)
- [헬스장 관리 API](https://github.com/underdarks/real_pt/blob/main/doc/gym/doc.md)
- [PT 관리 API](https://github.com/underdarks/real_pt/blob/main/doc/pt/doc.md)


<br>
<h2>ERD 설계도</h2>

엔티티간의 상세한 관계는 기능별 문서에서 자세히 설명할 예정<br><br>

![erd](https://user-images.githubusercontent.com/41244406/164969291-369a8548-0731-48e7-a8eb-1ca217d47d82.PNG)
  
<br>

<h2>개발 환경</h2>

- 개발 환경
   - 운영 서버 : AWS EC2, RDS(Mysql)
   - 개발 및 테스트 서버 : 로컬 환경
- Spring Boot 2.6.3
- JPA (Hibernate)
- Java 11.0.13
- JUnit 5
- Gradle 
- Lombok
- Mysql 8.0.27
- Spring Security
- Swaager 2.9.2

![build_gradle](https://user-images.githubusercontent.com/41244406/164969440-41a77820-ed42-4d18-855d-f324bdf709bc.PNG)


<br>

<h2>API 테스트 및 문서 관리</h2>

- <Strong>테스트 : 단위 테스트 & SpringBoot 통합 테스트, Postman</Strong>
- <Strong>문서 관리 : Swaager</Strong>(http://3.39.200.110:8080/swagger-ui.html)
 
 
 <br>
 <h2>프로젝트 일정</h2>
 2022.02.21 ~ Keep Going


