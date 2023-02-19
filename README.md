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
- 온라인 스터디 도중 스터디원의 갑작스런 연락 중단을 경험
- 이에 문제인식을 느끼고 모임을 끝까지 마칠 수 있는 스터디 모임 플랫폼 제작 <br>
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
### 요약
- 이메일 발송 기능을 추후 **기능추가 및 확장을 고려**하여 개발
  - 전략 패턴을 활용하여 확장에 유연하도록 구현
  - enum class 을 활용하여 이메일 유형 관리
- 응답이 필요없는 이메일 발송 처리 성능 개선을 위해 **비동기 방식 활용**
  - 예외처리를 고려하여 @Scheduled/ @Async/ Java CompleteFuture 조합으로 개발
  - common pool을 사용하지 않도록 thread pool 설정
- 가장 많이 조회되는 스터디 목록의 재조회 성능을 높이고자, 캐시를 활용하여 데이터 **검색 속도** **50% 개선** (6초 → 3초)
  - **Redis 캐시** 적용하여  Disk-Based DB 접근횟수 감소시킴
- 선착순 이벤트 신청 시 발생하는 **동시성 문제 해결**
  - 분산 환경에서 원자성(atomic)을 보장하기 위해 **Redis 분산 락** 적용
    - 부하 분산을 위한 서버 다중화 상황을 가정
      (실제 동작 서버는 1개)
- master - slave 구조로 **DB replication**
  - mysql replication을 통해 slave 디비에 실시간 데이터 복제
  - master db에서는 (insert , update , delete) slave db에는 (select) 역할 분담
- 대량 이메일 전송 시 필요한 이메일 주소 조회시 **인덱스**를 활용하여 조회 성능 개선
  - 자주 조회되며 cardinality 수치가 높은 이메일 주소 칼럼을 기준
  - mysql profiling을 통해 DB 조회업무 **검색 속도 40% 개선** 검증
    (0.003초 → 0.0007초/ 컬럼 600개 기준)
```
- 서비스 클래스에 대한 **단위 테스트** 진행
  - db - status(A) .. (B)
  - 어떤 기준(ex. null , 코드 커버리지)
    1. 
- GC, CPU, 스레드 성능 지표 확인을 위해 **모니터링 툴** 도입
  - Pinpoint, NGrinder, VisualVM
  - 사례
    1. redis 캐시
    2. 동시성 문제 - 분산락 redis 캐시
    3. 이메일 비동기 처리
    4. 인덱스
- 유지보수 하기 좋도록 기능단위 **메서드 추출**을 하여 개발
    - 협업시 필요한 부분만 빠르게 확인하여 수정이 용이함
    - 사례
    1. 
    2.
```  
- CI/CD 설정과정에서 많은 리소스 발생을 줄이고자 GIT ACTION 적용
- 런타임 에러를 미연에 방지하기 위해 Querydsl 적용
- 예외처리 유지보수를 편리하게 하기 위해 @ControllerAdvice - @ExceptionHandler 사용
- JWT 기반 로그인 구현
- 엔티티 내부 구현을 캡슐화하기 위해 Response/ Request DTO 분리
- API Response 가독성을 높이고자 Response를 Header, Body, Error로 분리
- Git-Flow를 이용하여 Master 개발 결과에 영향 주지 않는 독립적인 개발환경 구축
  <br><br><br>

# 📖 비즈니스 목표
스터디원이 모임 종료후 → 후기가 남는 것을 의식하여 모임을 끝까지 마칠 수 있는 스터디 모임 플랫폼 제작
### 비즈니스 목표 달성을 위한 기능 요구사항
🔵 파란색: 서비스/ 🟢초록색: 기능 구현 완료/ ⚪ 흰색: 기능 설명 <br><br>
![feat.png](img/feat.png)
# 📖 ERD
![erd.png](img/erd.png)





