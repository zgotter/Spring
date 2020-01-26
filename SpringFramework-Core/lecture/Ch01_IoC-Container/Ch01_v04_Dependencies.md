# Ch01. IoC Container

## 1.4 Dependencies (의존성)

- 어플리케이션은 하나 이상의 객체로 이루어져 있다.
- 그 객체간의 함께 working을 하게 된다.
- 객체 간의 의존성 관계가 발생하게 된다.
- `kr.co.test.cli.di` 패키지의 클래스 예제 파일 참고

<br>

### 1.4.1 Dependency Injection (DI)

- DI를 이용해 Bean을 통해 객체 간의 관계를 설정하고 객체 간의 의존성을 디커플링할 수 있다.

- DI를 통해 테스트를 좀 더 수월하게 할 수 있다.
  - 인터페이스를 이용해 `stub` 이나 `mock` 을 만들 수 있다.

<br>

#### 1.4.1.1 생성자 기반 의존성 주입 (Constructor-based Dependency Injection)

- bean 설정 시, `constructor-arg` 태그를 이용하여 객체 간 의존성 주입
- 객체가 생성될 때, 한 번만 실행되기 때문에 객체 생성 시 필요한 코드를 넣고 싶을 때 사용
- 생성자 기반 의존성 주입 권장

<br>

#### 1.4.1.2 `Setter` 기반 의존성 주입 (Setter-based Dependency Injection)

- bean 설정 시, `property` 태그를 이용하여 `setter` 지정
- 코드가 실행되다가 객체의 dependency를 변경하고 싶을 때 Runtime의 어느 시점에서든지 관계를 재설정할 수 있다.
- 하지만, 객체 간의 관계는 객체 생성 시 정의하고 이후에는 변경을 안 하는 것이 복잡도를 감소시킨다.

<br>

#### 1.4.1.3 의존성 처리 프로세스 (Dependency Resolution Process)

- `ApplicationContext`가 XML, Java Code, Annotation을 통해 Configuration Metadata를 읽어서 Beans에 대한 설정을 확인
- 각각의 Bean들에 대해 properties, constructor(생성자), factory method를 통해 Bean들을 제공한다.

<br>

#### 1.4.1.4 상호 참조 (Circular dependencies)

- A 클래스가 B 클래스에 의존성이 있고, B 클래스가 A 클래스에 의존성이 있을 때, Spring Framework는 서로 간의 상호 참조가 가능하기 때문에 서로 만들 지 못하는 상황이 발생하는 데 이를 상호 참조라고 한다.

<br>

### 1.4.2 의존성 및 설정 (Dependencies and Configuration in Detail)

#### 1.4.2.0 JDBC Beans 설정 실습

- 여러 개의 DAO를 생성할 때 반복되는 부분 처리 필요
- 소스 코드 참고

<br>

#### 1.4.2.1  Straight Values (Primitives, Strings, and so on)

- 문자열 같은 값은 `property` 를 통해 받을 수 있다.

  ```xml
  <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
      <!-- results in a setDriverClassName(String) call -->
      <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
      <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
      <property name="username" value="root"/>
      <property name="password" value="masterkaoli"/>
  </bean>
  ```

  ```xml
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:p="http://www.springframework.org/schema/p"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
      https://www.springframework.org/schema/beans/spring-beans.xsd">
  	<!-- 
  		xmlns:p 를 선언하면 별도의 property 태그를 지정하지 않고 
  		bean의 p 속성으로 사용 가능
  	-->
      <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource"
          destroy-method="close"
          p:driverClassName="com.mysql.jdbc.Driver"
          p:url="jdbc:mysql://localhost:3306/mydb"
          p:username="root"
          p:password="masterkaoli"/>
  
  </beans>
  ```

- `property`를 리스트 형태로 받을 수 있다.

  ```xml
  <bean id="mappings"
      class="org.springframework.context.support.PropertySourcesPlaceholderConfigurer">
  
      <!-- typed as a java.util.Properties -->
      <property name="properties">
          <value>
              jdbc.driver.className=com.mysql.jdbc.Driver
              jdbc.url=jdbc:mysql://localhost:3306/mydb
          </value>
      </property>
  </bean> 
  ```

<br>

#### 1.4.2.2 References to Other Beans (Collaborators)

- `ref` 속성을 이용하여 bean을 또 다른 bean과 연결하여 사용

  ```xml
  <ref bean="someBean"/>
  ```

  ```xml
  <!-- in the parent context -->
  <bean id="accountService" class="com.something.SimpleAccountService">
      <!-- insert dependencies as required as here -->
  </bean>
  ```

  ```xml
  <!-- in the child (descendant) context -->
  <bean id="accountService" <!-- bean name is the same as the parent bean -->
      class="org.springframework.aop.framework.ProxyFactoryBean">
      <property name="target">
          <ref parent="accountService"/> <!-- notice how we refer to the parent bean -->
      </property>
      <!-- insert other configuration and dependencies as required here -->
  </bean>
  ```

<br>

#### 1.4.2.3 Inner Beans

- Bean 안에 Bean을 설정할 수 있다.

  ```xml
  <bean id="outer" class="...">
      <!-- instead of using a reference to a target bean, simply define the target bean inline -->
      <property name="target">
          <bean class="com.example.Person"> <!-- this is the inner bean -->
              <property name="name" value="Fiona Apple"/>
              <property name="age" value="25"/>
          </bean>
      </property>
  </bean>
  ```

- 많이 사용되지는 않음

<br>

#### 1.4.2.4 Collections

- bean 안에 `<list/>`, `<set/>`, `<map/>`, `<prop/>` elements를 각각에 맞는 Type으로 매핑시켜준다.

  ```xml
  <bean id="moreComplexObject" class="example.ComplexObject">
      <!-- results in a setAdminEmails(java.util.Properties) call -->
      <property name="adminEmails">
          <props>
              <prop key="administrator">administrator@example.org</prop>
              <prop key="support">support@example.org</prop>
              <prop key="development">development@example.org</prop>
          </props>
      </property>
      <!-- results in a setSomeList(java.util.List) call -->
      <property name="someList">
          <list>
              <value>a list element followed by a reference</value>
              <ref bean="myDataSource" />
          </list>
      </property>
      <!-- results in a setSomeMap(java.util.Map) call -->
      <property name="someMap">
          <map>
              <entry key="an entry" value="just some string"/>
              <entry key ="a ref" value-ref="myDataSource"/>
          </map>
      </property>
      <!-- results in a setSomeSet(java.util.Set) call -->
      <property name="someSet">
          <set>
              <value>just some string</value>
              <ref bean="myDataSource" />
          </set>
      </property>
  </bean>
  ```

<br>

#### 1.4.2.5 XML Shortcut with the p-namespace

- p-namespace를 지정하여 shortcut으로 사용할 수 있다.

  ```xml
  <beans ...
          xmlns:p="http://www.springframework.org/schema/p"
         ...>
      
  	<bean name="classic" class="com.example.ExampleBean">
          <property name="email" value="someone@somewhere.com"/>
      </bean>
  
      <bean name="p-namespace" class="com.example.ExampleBean"
          p:email="someone@somewhere.com"/>
      
  </beans>
  ```

<br>

#### 1.4.2.6 XML Shortcut with the c-namespace

- c-namespace를 지정하여 shortcut 사용 가능

  ```xml
  <beans ...
          xmlns:c="http://www.springframework.org/schema/c"
         ...>
      
      <bean id="beanTwo" class="x.y.ThingTwo"/>
      <bean id="beanThree" class="x.y.ThingThree"/>
  
      <!-- traditional declaration with optional argument names -->
      <bean id="beanOne" class="x.y.ThingOne">
          <constructor-arg name="thingTwo" ref="beanTwo"/>
          <constructor-arg name="thingThree" ref="beanThree"/>
          <constructor-arg name="email" value="something@somewhere.com"/>
      </bean>
  
      <!-- c-namespace declaration with argument names -->
      <bean id="beanOne" class="x.y.ThingOne" c:thingTwo-ref="beanTwo"
          c:thingThree-ref="beanThree" c:email="something@somewhere.com"/>
      
  </beans>
  ```

<br>

#### 1.4.2.7 Using `depends-on`

- `depends-on` 이라는 것은 두 개의 bean에 관계(특히, 만들어지는 순서의 관계)가 있을 때 `depends-on` 속성에 명시된 `id` 값을 갖는 bean을 먼저 생성한 뒤 해당 bean을 생성하게 된다.

  ```xml
  <bean id="beanOne" class="ExampleBean" depends-on="manager"/>
  <bean id="manager" class="ManagerBean" />
  ```

<br>

#### 1.4.2.8 Lazy-initialized Beans

- `lazy-init="true"` 속성을 지정하게 되면 spring container가 XML 읽고 bean을 생성할 때 해당 속성이 지정된 bean에 대해서는 해당 bean을 사용하는 시점에서 객체를 생성하게 된다. 

- 소스 코드 참고
  - Debug 모드로 차이점 확인

<br>

#### 1.4.2.9 Autowiring Collaborators

- bean 태그의 `autowire` 속성을 이용해 여러 가지 속성 자동으로 생성
- 소스 코드 참고

- XML 설정을 사용할 때에는 Autowiring 기능을 사용하는 것을 추천하지 않는다.
- 명시적으로 bean을 설정하는 것이 좋다.