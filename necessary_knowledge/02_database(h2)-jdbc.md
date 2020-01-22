# 2. database(h2) & jdbc

## 2.1 h2 database

- 경량화된 데이터베이스
- java 로 작성된 관계형 데이터베이스
- [h2 database 사이트 링크](<https://www.h2database.com/html/main.html>)



- 다운로드 후 `bin` 폴더에 있는 `h2.dat` 파일 실행
- 윈도우에서는 `h2-1.4.200.jar` 를 실행하면 jar 파일을 바로 실행시키므로 `h2.dat` 파일을 실행시킬 필요 없다.
- 내부적으로 h2 database 서버가 실행되었고, h2 data 관리 콘솔이 실행된다.



### 2.1.1 h2 database 기본 설정

- 초기 세팅된 정보 그대로 사용



- 연결 버튼 클릭

- `C:\Users\shkim` 디렉토리에 `test.mv.db` 파일이 생성된다.

- `create` SQL문 실행

  ```sql
  CREATE TABLE MEMBER();
  ```

- 작업 관리자에서 `Java(TM) Platform SE binary`를 종료시키면 h2 database 서버가 종료된다.

- h2 database 서버를 종료했다가 다시 실행해도 생성한 테이블은 그대로 남아있는다.
  (`test.mv.db` 파일 형태로 관련 정보가 저장되어 있기 때문)



### 2.1.2 h2 database의 장점

1. **파일과 메모리 모드 지원**
   - 파일 형태(`test.mv.db`)로 데이터가 유지됨
   - 테스트 코드 작성 시 메모리에 넣고 빠르게 사용 및 삭제 가능
   - **메모리 모드 사용법**
     - JDBC URL 변경 : `jdbc:h2:mem:db명`
     - 위와 같이 설정하고 연결하면 메모리 위에서 사용할 수 있다.
     - 메모리 상에서 실행되기 때문에 다시 연결을 하면 관련 정보는 사라진다.
2. **호환 모드 지원**
   - [h2 database engine > Features > Compatibility](<https://www.h2database.com/html/features.html#compatibility>)
   - 다양한 호환성 모드 지원 내용 확인
   - MySQL, MSSQL, Oracle 등



### 2.1.3 h2 database 어플리케이션에 포함시키기

- h2 database가 jar 로 배포되기 때문에 직접 만든 프로젝트에 dependency로 추가해주고 어플리케이션에 임베딩하여 사용 가능하다.



#### 2.1.3.1 intellij 에서 maven project Import

- intellij에서 `import project` 를 선택
- `01_maven-logback` 폴더의 `pom.xml`을 클릭하여 프로젝트를 실행
- `Import Project from Maven` 창이 팝업된다.



- `Import Maven projects automatically` 를 체크
  - Maven 프로젝트의 dependency를 자동으로 다운받는다.



- `JDK for importer`
  - intellij JVM이 잡힌 것 확인
  - intellij JVM이 1.8 버전이기 때문에 `JAVA_HOME`의 JDK 1.12.0 버전을 선택한다.



- `Next > Next > Next`



- `Project name` 확인
  - project name 은 `pom.xml`의 `artifactId` 와 동일하게 지정하는 것이 좋다.



- `Finish` 버튼 클릭



- intellij 가 백그라운드에서 maven dependency 및 프로젝트 구조에 대한 indexing 작업 등 여러 가지를 수행하느라 약간의 시간이 소요된다.



#### 2.1.3.2 Module 세팅 (JDK 설정)

- `pom.xml` 파일에서 `properties` 태그에 Import 시 지정한 JDK 버전을 입력한다.

- Import 할 때 설정한 JDK 버전에 맞게 `Project > Open Module Settings` 에서 JDK 버전 설정한다.



#### 2.1.3.3 h2 database 임베딩

- maven central에서 "h2-database" 검색
- Group ID가 `com.h2database`인 항목의 Last Version 선택
- dependency 를 복사하여 `pom.xml`의 `dependencies`에 추가



- intellij 우측에 `Maven` 창을 선택하여 dependency가 추가되었는 지 확인
  (이 과정을 버릇을 들여 놓으면 좋다.)



## 2.2 jdbc

- 표준화된 jdbc 인터페이스를 통하여 다양한 벤더의 API를 사용할 수 있다.
- 이를 통해 파생된 장점
  - jdbc 인터페이스를 이용해서 또 다른 라이브러리들이 만들어질 수 있다.
    (ex. MyBatis, HIBERNATE)



### 2.2.1 jdbc API

- [Java JDBC API](<https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/index.html>)
  - jdbc는 `java.sql`, `javax.sql` 패키지를 통해 제공



-  [Java 8 API > java.sql](<https://docs.oracle.com/javase/8/docs/api/>)
  - 다양한 인터페이스, 클래스, 예외 등을 지원
  - 하단에 버전에 대한 히스토리 명시되어 있음
  - 중요한 부분은 **Interface Summary** 에 있는 인터페이스들이다.



### 2.2.2 h2 database console 을 통한 테이블 생성/입력/조회

1. h2 database를 이용해 `MEMBER` 테이블 생성

   ```sql
   create table member(
     id int auto_increment,
     username varchar(255) not null,
     password varchar(255) not null,
     primary key(id)
   );
   ```

2. `MEMBER` 테이블 데이터 입력

   ```sql
   insert into member (username, password) values('shkim','1234');
   ```

3. `MEMBER` 테이블 조회

   - 좌측에 테이블명을 더블 클릭하면 기본적인 `SELECT`문이 자동 생성된다.

   ```sql
   SELECT * FROM MEMBER 
   ```

   - 데이터 입력된 것 확인

4. h2 database 콘솔 프로세스 종료



### 2.2.3 maven project에서 jdbc 사용

- `java.sql`은 java SE에 기본으로 들어가 있는 클래스이므로 `pom.xml`에 따로 dependency를 추가할 필요가 없다.

- `Main.java` 내용 참고 



