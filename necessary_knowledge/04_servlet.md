# 4. servlet

- 자바를 이용해서 웹 페이지를 동적으로 생성하는 서버측 프로그램 또는 그 사양
- 서블릿 4.0 버전 사용
  - 서블릿 4.0 버전은 자바 플랫폼 JavaEE 8 이다.
- 전통적으로 톰캣과 호환되어 왔다.
- "자바 플랫폼, 엔터프라이즈 에디션"에서 JavaEE Enterprise Platform을 지원하는 다양한 벤더들을 확인할 수 있다.
- 이러한 벤더들에서 servlet 뿐만 아니라 스펙으로 있는 다양한 기능들(ex. J2EE, JSP, JMS 등)을 실제로 구현체로 만들고 판매를 하고 있다.

<br>

## 4.1 JavaSE vs JavaEE

### 4.1.1 JavaSE

- Standard Edition

<br>

### 4.1.2 JavaEE

- Enterprise Edition
- JavaEE를 사용하기 위해선 dependency를 추가해줘야 한다.
- [Java EE API](<https://docs.oracle.com/javaee/7/api/>)
- Java EE package
  -  패키지 root가 `javax` 로 되어 있다.
  - `javax.servlet`

<br>

## 4.2 `javax.servlet`

- 해당 패키지에서 구현한 인터페이스들이 servlet이다.
- 이 servlet의 spec을 다양한 벤더들(ex. 아파치 톰캣(Apache Tomcat) , 티맥스소프트 제우스(JEUS))이 구현하고 있다.

<br>

- 주요 패키지
  - `javax.servlet.jsp` : JSP
  - `javax.servlet.jsp.jstl.core` : JSTL

<br>

### 4.2.1 Apache Tomcat

- 서블릿 서비스에 특화됨
- 실습에서 Apache Tomcat 9 버전의 서블릿 서버를 사용한다.
- 톰캣은 JavaEE의 `javax.servlet` 패키지의 모든 구현체들을 구현해 놓았다.

<br>

### 4.2.2 JEUS

- 엔터프라이즈에 필요한 다양한 기능 제공

<br>

## 4.3 Servlet Spec

- [Servlet 4.0 Spec](<https://javaee.github.io/servlet-spec/downloads/servlet-4.0/servlet-4_0_FINAL.pdf>)
- `web.xml` : `<web-app>` 태그로 검색

<br>

## 4.4 Servlet 실습

- 프로젝트에 2개의 Main 자바 클래스를 생성한다.
  1. CLI (Command Line Interface) $\rightarrow$ `kr.co.maven.cli` 라는 패키지로 구분
  2. 서블릿에서 웹 프로그램을 동작시킬 Main 클래스 $\rightarrow$ `kr.co.maven.web` 이라는 패키지로 구분

<br>

### 4.4.1 `pom.xml`에 Servlet 관련 의존성(dependency) 추가

- [Maven Central - javax.servlet 4.0.1](<https://search.maven.org/artifact/javax.servlet/javax.servlet-api>)

  ```xml
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
  </dependency>
  ```

<br>

**참고**

- dependency의 `artifactId`에 `-api`가 붙은 것들은 구현체가 없는 api를 의미한다.
- API만 Java EE를 만드는 단체에서 설계를 해놓으면 여러  벤더들이 실제 구현체를 만드는 것이다.
- 구현체에 대해서는 실습 중인 소프트웨어에는 포함시키지 않는다. 

<br>

## 4.5 maven 프로젝트을 웹 어플리케이션으로 변경

- maven 프로젝트를 jar가 아닌 war 로 패키징하여 웹 어플리케이션으로 변경해야 한다.
- 이 때 **war 플러그인**을 사용해야 한다.
- [maven war plugin](<https://maven.apache.org/plugins/maven-war-plugin/>)

<br>

### 4.5.1 `packaging` 태그 추가

- `pom.xml`의 `project` 태그의 하위 태그로 `packaging` 태그를 추가한다.

  ```xml
  <packaging>war</packaging>
  ```

<br>

### 4.5.2 프로젝트 구조 변경

- 웹 어플리케이션 구조를 따라야 되기 때문에 프로젝트 구조를 다음과 같이 변경해야 한다.

  ```xml
   |-- pom.xml
   `-- src
       `-- main
           |-- java
           |   `-- com
           |       `-- example
           |           `-- projects
           |               `-- SampleAction.java
           |-- resources
           |   `-- images
           |       `-- sampleimage.jpg
           `-- webapp
               |-- WEB-INF
               |   `-- web.xml
               |-- index.jsp
               `-- jsp
                   `-- websource.jsp
  ```

- maven 프로젝트의 구조와의 차이점은 `main` 디렉토리 하위에 `webapp` 디렉토리가 추가된다는 점 뿐이다.

<br/>

### 4.5.3 `web.xml`

- Servlet spec에 맞게 해당 파일을 꼭 만들어 줘야 한다.
- Servlet spec 4.0 문서에 `web.xml` 파일의 표준 내용이 명시되어 있다.
- `WEB-INF` 디렉토리 하위에 `web.xml` 파일을 생성해준다.

<br>

### 4.5.4 Servlet Class

- 서블릿 표준에 맞게 구현된 자바 클래스 파일 생성
- `SimpleServlet.java` 파일 참고

<br>

### 4.5.5 welcom file

- `index.jsp` 파일 참고

<br>

### 4.5.6 war 파일 생성

#### 4.5.6.1 Project Clean

- `target` 디렉토리 초기화 

- 방법 1 : Console
  - 콘솔에서 `mvn clean` 명령어 수행
- 방법 2 : GUI
  - intellij 레이아웃 우측 상단 `maven > Lifecycle > clean` 클릭

<br>

#### 4.5.6.2 Project Packaging

- 프로젝트를 build한다.
- `pom.xml`에 `war` 파일 생성 관련 설정을 하였으므로 `war` 파일이 생성된다.

- 방법 1 : Console
  - `mvn package`
- 방법 2 : GUI
  - intellij 레이아웃 우측 상단 `maven > Lifecycle > package` 클릭
- Build Success 가 출력되면 `target` 디렉토리에 프로젝트가 `war` 파일 형태로 패키징 된 것을 확인할 수 있다.

<br>

#### 4.5.6.3 war 파일명 지정

- `war` 파일명이 `artifactId`와 버전 정보가 붙어 복잡하다.
- `pom.xml` 파일의 `build` 태그 하위에 `finalName` 태그를 생성한 후 프로젝트명을 작성
- `clean`, `package`를 다시 수행
- 지정한 프로젝트명을 파일명으로 하는 `war` 파일이 생성된다.



### 4.5.7 war 파일 실행

- war 파일만으로는 웹 어플리케이션을 실행시킬 수 없다.
- servlet spec에 맞게 구현된 이 war 파일을 동작시켜줄 수 있는 **servlet spec이 구현된 구현체**가 필요하다.
- 여러 벤더들의 구현체 중 **Apache Tomcat**을 사용한다.



### 4.5.8 Apache Tomcat

- [Apache Tomcat](<http://tomcat.apache.org/>)

- 오픈 소스
- Servlet, JSP(Java Server Page), EL(Expression Language), WebSocket 등의 JavaEE 의 일부 기술들이 구현되어 있는 웹 서버
- Tomcat 9 버전 사용
  - 서블릿 4.0을 구현한 버전



### 4.5.9 톰캣 실행

- 톰캣에 `bin` 폴더 안의 `startup.bat` 파일을 클릭하면 톰캣이 실행된다. (Windows 기준)
  - bash shell 을 사용하는 경우에는 `startup.sh`을 실행하면 된다.
- 그런 다음 웹 브라우저에서 기본 포트 `8080`을 사용하여 `http://localhost:8080`으로 접속하면 톰캣이 정상적으로 실행되는 것을 확인할 수 있다.



### 4.5.10 톰캣 이용 웹 어플리케이션 실행

- 다운로드 받은 Apache Tomcat 9 를 프로젝트의 root 디렉토리 하위에 `tomcat` 폴더를 생성하여 zip 파일을 풀어서 넣는다.
  - 원래는 Tomcat을 다른 프로젝트에 넣어서 사용한다.

- 위에서 생성한 war 파일을 톰캣의 `webapps` 폴더에 넣는다.
  - 그러면 톰캣이 이 war 파일의 압축을 풀고 프로젝트로 등록을 하고 웹 어플리케이션이 동작하게 된다.

<br>

- 4.5.9 를 통해 톰캣을 실행한 상태에서 위 과정을 수행하면 톰캣이 동작하는 것을 볼 수 있다. 

- `http://localhost:8080/springbasic`
  - 기본 URL 뒤에 war 파일명(`springbasic`)을 입력하여 접속하면 설정한 JSP 파일이 열리게 된다.
- `http://localhost:8080/springbasic/simple`
  - `SimpleServlet.java` 파일 내용이 실행된다.

<br>

## 4.6 Servlet Filter

- Servlet이 동작하기 앞 또는 후에 필터 기능을 하는 것
- 주로 로그인한 사용자의 인증 처리 등에 사용
-  `web.xml`에서 설정

<br>

## 4.7 Session

- 로그인된 사용자의 정보를 Session을 통해 관리
- Session은 Serlvlet request에서 가져올 수 있다.
- Session은 Http Header의 cookie값을 이용해서 session을 관리한다.

<br>

### 4.7.1 서버 단에서의 Session 관리

- Http는 상태가 없는 프로토콜이다.
- 하지만 상태라는 것이 없으면 사용자 구별이 안 되기 때문에 보통 Header를 통해서 사용자 식별을 한다.
- Header - Cookie의 "JSESSIONID"
  - 이 JSESSIONID를 통해서 톰캣이 사용자 식별을 하고 있는 것이다.
  - 그러므로 이 값을 지우고 보내게 되면 새로운 사용자로 인식하게 된다.
  - Request Header에 JSESSIONID가 없으면, 즉 새로운 사용자라면, Response Header에 Set-Cookie를 통해 JSESSIONID를 전달하게 된다.

<br>

## 4.8 Embedded Tomcat

- 톰캣 자체로 jar로 된 라이브러리이기 때문에 `pom.xml`에 의존성을 설정한 다음 인텔리제이에서 사용할 수 있다.

<br>

### 4.8.1 `pom.xml` 설정

- `<properties>` 부분에 톰캣 버전을 명시한다.

  ```xml
  <tomcat.version>8.5.23</tomcat.version>
  ```

- `<dependencies>`에 아래와 같이 dependency를 추가한다.

  ```xml
  <dependency>
  	<groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-core</artifactId>
      <version>${tomcat.version}</version>
  </dependency>
  <dependency>
  	<groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-jasper</artifactId>
      <version>${tomcat.version}</version>
  </dependency>
  <dependency>
  	<groupId>org.apache.tomcat</groupId>
      <artifactId>tomcat-jasper</artifactId>
      <version>${tomcat.version}</version>
  </dependency>
  <dependency>
  	<groupId>org.apache.tomcat</groupId>
      <artifactId>tomcat-jasper-el</artifactId>
      <version>${tomcat.version}</version>
  </dependency>
  <dependency>
  	<groupId>org.apache.tomcat</groupId>
      <artifactId>tomcat-jsp-api</artifactId>
      <version>${tomcat.version}</version>
  </dependency>
  ```

- 이렇게 하면 해당 프로젝트는 톰캣을 내부적으로 갖고 있기 때문에 톰캣을 따로 띄우고 war 파일을 올리지 않아도 어플리케이션 실행 자체로 war가 실행 가능하다.

<br>

### 4.8.2 shade Mainclass 변경

- shade execution의 MainClass를 `kr.co.test.web.Main`으로 변경

<br>

### 4.8.3 `web.Main` 생성

- `web` 패키지 하위에 `Main` 클래스 생성

- `Main` 클래스 내부에 톰캣이 돌아갈 수 있는 코드 구현

<br>

### 4.8.4 annotation을 이용한 Servlet 설정

- Servlet 3.0부터 추가된 annotation 기법을 이용해서 `web.xml` 없이 Servlet 설정을 할 수 있도록 변경

<br>

- `web.xml` 삭제
- `SimpleServlet`에 `@WebServlet` annotation 추가
- `SimpleFilter`에 `@WebFilter` annotation 추가

<br>

### 4.8.5 실행

- `web.Main` 을 실행시켜서 톰캣 실행

