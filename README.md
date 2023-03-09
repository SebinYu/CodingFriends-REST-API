- [x] 리드미 작성
- [ ] 중점사항: 나머지 사례 등록
- [ ] 중점사항: 링크 연결
  - [ ] 간단한 코드 제시만 필요한 경우 - 클래스 이동링크 연결
  - [ ] 적용내역 및 정리 내역은 노션 링크 제공
- [ ] 배포 링크 등록


<div align=center>
	<img src="https://capsule-render.vercel.app/api?type=waving&color=auto&customColorList=9&height=200&width=100%&section=header&text=Coding%20Friends!&fontSize=90" />
</div>
<div align=center>
	<h3>📚 "[REST API 서버] 개발자 스터디 모임 플랫폼" 📚</h3>
</div>
<br><br>

# 📖 개요
### 제안배경
- 익명 온라인 환경에서 신뢰할 수 있는 스터디원 찾기에 어려움을 느낌
- 이에 문제인식을 느끼고 스터디원의 과거 성실도 확인이 가능한 스터디 모임 플랫폼 제작 <br>
  - 스터디 모임 종료후 → 모든 스터디원 참여 점수 공개(별점 및 리뷰글 기반)
  - 플랫폼 이름 = Coding Friends
    <br><br>
- 해당 프로젝트는 기존 개발한 스터디 모임 플랫폼에서 서버를 분리 → [기능추가 및 리팩토링한 **REST API 서버**]
  - 기존 스터디 모임 플랫폼: https://github.com/SebinYu/CodingFriends-WEB
    <br><br>
### [REST API 서버] 프로젝트 목표
- 스터디 모집 및 진행 → 진행 종료 후: 스터디원 참여 점수 공개(별점 및 리뷰글) API를 제공합니다.
- 기능과 더불어 해당 프로젝트는 3가지 핵심 가치를 반영합니다.
  - 성능향상
  - 대규모 트래픽 대처
  - 추후 기능추가 및 확장 가능성
    <br><br><br>

# 📖 서버 구조도
![devOps.png](img/devOps.png)
<br><br><br>

# 📖 기술스택
- Back-end : Java11, Spring Boot 2.7.8, Spring Data JPA, Querydsl, JUnit5, Gradle
- Database: MySQL 8.0, MyBatis, Redis
- DevOps: AWS - EC2, S3, RDS, IAM, CodeDeploy
- CI/CD: Git Action
- Tools: IntelliJ, Git ( + Git Flow), Pinpoint, Ngrinder, VisualVM, Ubuntu, Vim, Notion
  <br><br><br>

# 🔥 프로젝트 중점사항 🔥
🔥🔥링크 클릭 시, 노션 정리본(예외처리 등..) or 관련 깃 커밋으로 이동🔥🔥
- 이메일 발송 기능을 추후 **기능추가 및 확장을 고려**하여 개발
  - [전략 패턴을 활용하여 확장에 유연하도록 구현](https://legendary-industry-40c.notion.site/50035bebe8ee4b35837856f585eb3375)
  - [enum class 을 활용하여 이메일 유형 관리](https://legendary-industry-40c.notion.site/Enum-class-900eff62400e498580ca4a41592d933c)
- [응답이 필요없는 이메일 발송 처리 성능 개선을 위해 **비동기 방식 활용**](https://legendary-industry-40c.notion.site/1923a8b5e1794c2387bf2d239effe484)
  - 예외처리를 고려하여 @Scheduled/ @Async/ Java CompleteFuture 조합으로 개발
  - common pool을 사용하지 않도록 thread pool 설정
- [반복적으로 보여지는 게시글 캐싱 기능 추가 **재검색 속도** **400% 개선** (859 ms → 22 ms)](https://legendary-industry-40c.notion.site/40-Redis-Cache-a40332cfe8ff4a0d8b468141fff8c898)
  - **Redis 캐시** 적용하여  Disk-Based DB 접근횟수 감소시킴
- [선착순 이벤트 신청 시 발생하는 **동시성 문제 해결**](https://legendary-industry-40c.notion.site/Redis-2b1b9dff3e8549ac930ffa0ac2ed3fd7)
  - 분산 환경에서 원자성(atomic)을 보장하기 위해 **Redis 분산 락** 적용
    - 부하 분산을 위한 서버 다중화 상황을 가정
      (실제 동작 서버는 1개)
- [데이터베이스 부하 분산을 위해 **DB replication**](https://legendary-industry-40c.notion.site/DB-Replication-f23e689871004568872fd29d3de333ef)
  - Slave DB에 실시간 데이터 복제
  - Master DB에서는 (insert , update , delete) Slave DB에는 (select) 역할 분담
- [**인덱스 설계**가 필요한 컬럼의 **조회 속도 400% 개선**](https://legendary-industry-40c.notion.site/c886f5e8bff143b4a1071d96f86d6f77)
  - 컬럼선택: 자주 조회되며 cardinality 수치가 높은 이메일 주소 선택 
  - mysql profiling을 통해 DB 조회업무 **검색 속도 400% 개선** 검증 <br>
    (약 0.1182초 → 약 0.0003초/ 컬럼 300개 기준)
```
- 서비스 클래스에 대한 **단위 테스트** 진행
  - db - status(A) .. (B)
  - 어떤 기준(ex. null , 코드 커버리지)
    1. 
```
- GC, CPU, 스레드 성능 지표 확인을 위해 **모니터링 툴** 도입
  - Pinpoint, NGrinder
  - 사례
    1. redis 캐시
    2. 동시성 문제 - 분산락 redis 캐시
    3. 이메일 비동기 처리

- 유지보수 하기 좋도록 기능단위 **메서드 추출**을 하여 개발
    - 협업시 필요한 부분만 빠르게 확인하여 수정이 용이
    - 사례
    1. [조직장의 스터디모임 조회를 위한 컨드롤러 메서드 분리](https://github.com/SebinYu/CodingFriends-REST-API/blob/master/src/main/java/net/skhu/codingFriends/controller/user/LeaderController.java)
    2. [authentication을 통한 사용자 정보 불러오기 메서드 분리](https://github.com/SebinYu/CodingFriends-REST-API/blob/master/src/main/java/net/skhu/codingFriends/controller/MailController.java)
  
- [CI/CD 설정과정에서 많은 리소스 발생을 줄이고자 GIT ACTION 적용](https://legendary-industry-40c.notion.site/CI-CD-GIT-ACTION-72f744900016473cb59991a0ae62aaf1)
- [런타임 에러를 미연에 방지하기 위해 Querydsl 적용](https://legendary-industry-40c.notion.site/JPA-Querydsl-e473ce4e43234212b23570fe23c49611)
- [예외처리 유지보수를 편리하게 하기 위해 @ControllerAdvice - @ExceptionHandler 사용](https://github.com/SebinYu/CodingFriends-REST-API/blob/master/src/main/java/net/skhu/codingFriends/advice/ExceptionAdvice.java)
- [JWT 기반 로그인 구현](https://legendary-industry-40c.notion.site/JWT-5c126a565ed545b4bb3ca17413b8d7c7)
- [엔티티 내부 구현을 캡슐화하기 위해 Response/ Request DTO 분리](https://github.com/SebinYu/CodingFriends-REST-API/tree/master/src/main/java/net/skhu/codingFriends/dto)
- [API Response 가독성을 높이고자 Response를 success,failure/ result data 등으로 분리](https://github.com/SebinYu/CodingFriends-REST-API/tree/master/src/main/java/net/skhu/codingFriends/response)
- [Git-Flow를 이용하여 Master 개발 결과에 영향 주지 않는 독립적인 개발환경 구축](https://github.com/SebinYu/CodingFriends-REST-API/branches)
  <br><br><br>

# 📖 비즈니스 목표
스터디원이 모임 종료후 → 후기가 남는 것을 의식하여 모임을 끝까지 마칠 수 있는 스터디 모임 플랫폼 제작
### 비즈니스 목표 달성을 위한 기능 요구사항
🔵 파란색: 서비스/ 🟢초록색: 기능 구현 완료/ ⚪ 흰색: 기능 설명 <br><br>
![feat.png](img/feat.png)
# 📖 ERD
![erd.png](img/erd.png)





