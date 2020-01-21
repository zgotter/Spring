# 1. maven & logback

## 1.1 maven

- Java 쪽에서 build를 담당



## 1.2 logback

- 로그를 기록하기 위해 사용
- Spring 등에서 기본으로 사용하고 있는 로깅 라이브러리



## 1.3 왜 maven을 써야 하는 가?

- 먼저 maven을 이용하지 않고 Java로 컴파일하는 방법 학습



## 1.4 Java로 컴파일 하기

### 1.4.1 Java Hello World 만들기

- `notepad Main.java`
- `javac Main.java`
  - 컴파일
  - `Main.class` 파일이 생성됨
- `java Main`
  - `Main.java` 실행
  - `Hello World` 가 출력되는 것 확인



### 1.4.2 `Main.class` 를 패키지로 만들기

- `kr > co > maven` 디렉토리를 생성한 후 `Main.java` 파일을 해당 디렉토리로 이동
- `Main.java` 상단에 패키지 경로 입력
  - `package kr.co.maven;`
- `javac kr\co\maven\Main.java`
  - 컴파일
  - class 파일 생성

- `java -cp . kr.co.maven.Main`
  - 패키지 하위에 있는 자바 파일 실행
  - `Hello World` 출력 확인



### 1.4.3 Jar 파일 묶기

- class 파일이 여러 개 생길 수 있기 때문에 jar로 묶는다.
- `Mainifest.txt` 파일 생성
  - 내용 : `Main-Class:kr.co.maven.Main`
- jar 파일 생성
  - `jar -cvmf Mainifest.txt maven.jar kr\*`



- jar 파일은 아카이빙을 위한 압축 파일이다.
- 아카이빙이란 여러 개의 파일을 하나의 파일로 묶어서 관리하기 편하게끔 사용하는 것



- jar 파일 실행
  - `java -cp maven.jar kr.co.maven.Main`
- jar 파일로만 실행
  - `Mainifest.txt` 파일 생성
  - Mainifest 파일 추가하여 jar 파일 생성
  - 실행 : `java -jar maven.jar`



### 1.4.4 logback 적용하기

- `lib` 디렉토리에 아래 3개 파일 위치 시키기
  - `logback-classic.jar`
  - `logback-core.jar`
  - `slf4j-api.jar`



- Main.java 파일 편집

  ```java
  package kr.co.maven;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  
  class Main {
      
      private static Logger logger = LoggerFactory.getLogger(Main.class);
      
      public static void main(String[] args) {
          
          logger.info("Hello world!!");
          
      }
      
  }
  ```

- 수정한 Main.java 컴파일  : jar 파일을 인식 안시켜줬기 때문에 에러 발생



- jar 경로 인식 후 컴파일
  - `javac -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar kr\co\maven\Main.java`



- Main.java 실행
  - `java -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar;. kr.co.maven.Main`
    - 로그 패턴에 맞게  앞부분의 문자열과 출력 정보가 출력됨



### 1.4.5 logback이 포함된 jar 파일 만들기

- manifest.txt 수정

  - 아래 내용 추가

    ```
    Class-Path: lib\logback-class.jar;lib\logback-core.jar;lib\slf4j-api.jar
    
    ```



- jar 파일 생성
  - `jar -c -m manifest.txt -f maven.jar kr\*`



- jar 파일 실행
  - `java -jar maven.jar`



### 1.4.6 logback XML 설정 파일 추가

- logback.xml 파일 생성

  - logback 에서 원하는 이름

  - classPath root 에 위치 시키면 됨 

  - 아래 내용 추가

    ```xml
    <configuration>
    
      <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type
             ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
          <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
      </appender>
    
      <root level="debug">
        <appender-ref ref="STDOUT" />
      </root>
    </configuration>
    ```

  

- 주요 태그

  - appender
    - ConsoleAppender 사용
  - root



- root tag level의 종류
  - `trace`, `debug`, `info`, `warn`, `error`
  - $\rightarrow$ 로 갈 수록 로그가 적게 찍힌다.



- logback 설정파일 포함한 jar 파일 생성
  - `jar -c -m manifest.txt -f maven.jar krx\* logback.xml`



- jar 파일 실행하여 설정 파일이 동작하는 지 확인
  - `java -jar maven.jar`



Java 커멘드를 통해 컴파일, jar 아카이빙, jar 실행하는 과정은 복잡하다. 이 과정들이 maven을 통해 얼마나 쉽게 수행되는 지 확인해 보자.



## 1.5 maven 이용 컴파일 및 빌드하기

### 1.5.1 maven 설치 여부 확인

- `mvn --version`



### 1.5.2 maven 프로젝트 생성

- maven은 관례에 의한 설정을 이용하고 있다.

  - 프로젝트 root에 `pom.xml` 이란 파일이 있다.
  - root에 `src` 라는 디렉토리가 있다.
  - `src` 하위에는 `main`, `test` 디렉토리가 있다.
  - 각각의 `main`, `test` 디렉토리에 `java` 디렉토리가 있다.
  - `java` 디렉토리 하위에는 패키지 - java 파일이 위치하게 된다.

  ```
  my-app
  |-- pom.xml
  `-- src
      |-- main
      |   `-- java
      |       `-- com
      |           `-- mycompany
      |               `-- app
      |                   `-- App.java
      `-- test
          `-- java
              `-- com
                  `-- mycompany
                      `-- app
                          `-- AppTest.java
  ```



### 1.5.3 `pom.xml` 파일 만들기



#### 1.5.3.1 maven 설정

- `project` 태그가 최상위에 존재한다.
- `project` 하위에 `modelVersion` 태그가 존재한다.



#### 1.5.3.2 프로젝트 설정

- `project` 태그 하위에 존재



`groupId`

-  패키지 경로 입력

  ```xml
  <groupId>kr.co.maven</groupId>
  ```



`artifactId`

- 프로젝트의 이름

  ```xml
  <artifactId>spring-basic</artifactId>
  ```



`version`

- 버전

  ```xml
  <version>1.0-SNAPSHOT</version>
  ```



`properties`

- 추후 설정



`depencencies`

- 



### 1.5.4 maven validate

- 생성한 `pom.xml` 파일이 문제 없는 지 확인

- 명령어 : `mvn validate`
- 출력 내용에 `BUILD SUCCESS` 가 있으면 문제가 없는 것이다.



### 1.5.5 폴더 구조 생성

- maven 관례에 맞게 폴더 구조 생성



- `src > main` 하위에 `java` 와 `resources` 디렉토리 생성
  - `resources` 디렉토리는 자바 파일 이외의 파일을 관리하는 데 사용된다.
- `src > test` 하위에 `src > main` 과 똑같이 디렉토리를 구성한다.



- `src > main > java` 하위에 1.4 에서 생성한 java 파일을 위치시킨다.
- `lib` 하위의 logback 관련 jar 파일은 maven에서 관리할 것이므로 없애도 된다.
- `logback.xml` 파일은 `src > main > resources` 로 이동시킨다.

- `manifest.txt` 파일은 maven 빌드 과정에서 자동으로 생성되므로 없애도 된다.
- 1.4 실습에서 생성된 `maven.jar` 도 삭제한다.



### 1.5.6 logback 추가

- 구글에 maven central 검색 : [maven central](<https://search.maven.org/>)
- 위 링크에서 여러 가지 dependency의 정보를 검색할 수 있다.
- `logback-classic` 검색
- `ch.qos.logback`을 Group ID로 갖는 `logback-classic` 을 찾을 수 있다.
- 1.2.3 버전을 선택한다.
- Apache Maven 부분에 있는 `dependency` 부분을 사용하면 된다.

- 해당 내용을 `pom.xml` 의 `dependencies` 태그 하위에 붙여 넣는다.



### 1.5.7 maven 컴파일

- `pom.xml` 파일로 설정을 끝냈으므로 1.4 에서처럼 class path에 일일히 jar 파일을 설정해주지 않아도 된다.
- 명령어 : `mvn compile`
  - `javac` 를 이용해 `.class` 파일을 생성한다.
  - 자동으로 의존성을 분석, jar 파일 다운로드, build 를 수행한다.



- compile 이 정상적으로 수행되면 root 디렉토리에 `target` 이라는 디렉토리가 생성된다.
- `target` 디렉토리 하위에는 `logback.xml` 과 같은 설정 파일과 `.class` 파일이 존재한다.
- 그 외에도 maven에서 사용하는 설정 파일들도 존재한다.



### 1.5.8 `mvn clean`

- 해당 명령어를 실행하면 `target` 폴더를 삭제할 수 있다.



### 1.5.9 maven 이 jar 파일을 인식하는 방법

- jar 파일을 넣어주지 않았는데도 어떻게 인식을 하는 것일까?
- `C:\Users\shkim\.m2\repository\ch\qos\logback` 경로에 logback 관련 jar 파일이 생성된 것을 확인할 수 있다.
- maven 은 `.m2 > repository` 폴더 하위에 모든 jar 파일을 놓아두고 build를 하고 있다.



### 1.5.10 `jar` 파일 생성

- `jar` 명령어를 실행할 때는 maven의 **plug-in** 을 이용해 실행가능한 jar 파일을 만들면 된다.
- [maven plugins](<https://maven.apache.org/plugins/index.html>) 사이트에서 사용 가능한 maven plugin 을 확인할 수 있다.



- `shade` 라는 명령어를 사용하여 jar 파일을 생성할 것이다.
- "shade usage" 부분에 나오는 plugin 내용을 복사하여 `pom.xml` 파일에 붙여넣는다.
-  `dependencies` 태그 다음에 `build > plugins`  태그를 만들고, `shade` 의 `plugin` 태그 내용을 하위에 붙여넣으면 된다. 
- `execution` 태그 하위의 `phase` 태그에 적힌 내용(`package`)이 실행될 때 `shade` plugin이 실행된다.



-  `plugin > configuration` 태그 하위에 `executable jar` 에 있는 내용을 붙여넣어야 build 할 때 jar 파일이 생성된다.

- `mainClass` 태그에 우리가 생성한 Main 클래스가 있는 경로를 지정한다.

  (`kr.co.maven.Main`)



- 명령어 : `mvn package`
  - `.class` 파일 생성뿐만 아니라 `jar` 명령어를 통해 jar 아카이빙까지 실행한다.
  - 해당 명령어가 정상적으로 수행되면 `target` 폴더 하위에 2개의 jar 파일이 생성된다.
    - `spring-basic-1.0-SNAPSHOT.jar`
      - `Main.class` 와 관련 폴더들과 관련된 의존성들도 `.class`로 풀려 jar 파일에 포함되어 있다.



### 1.5.11 maven 을 통해 생성한 jar 파일 실행

- 명령어 : `java -jar target\spring-basic-1.0-SNAPSHOT.jar`

- 해당 명령어를 실행하면 logback 메세지와 함께 `Main.java` 파일의 내용이 출력된다.

