# RoutineProject

루틴과 위시리스트를 관리하는 일정관리 서비스 API

### 소개
***
하루 ,일주일 단위로 반복해서 하고 싶은 루틴을 관리하는 서비스의 API를 개발하는 개인 프로젝트 입니다.

향후 리팩터링을 통해 부족한 부분을 채울 예정입니다.

### 개발자
***
- [김동균](https://github.com/oldaim)

### 기술 스택
***
- Language: **Java 11**
- Server Framework: **Spring Boot 2.6.3**
- IDE: **Intellij IDEA**
- CI/CD : **Github Action** , **AWS CodeDeploy**
- Dependency Management: **Gradle-7.3.3**

### 주요 기능
***
- 회원의 회원가입과 로그인을 지원합니다.
- 일정의 등록, 수정, 조회, 삭제를 지원 합니다.
- 매일 오전 6시에 루틴의 상태를 Do -> Undo 로 바꿔서 다시 체크 할 수 있습니다. 
