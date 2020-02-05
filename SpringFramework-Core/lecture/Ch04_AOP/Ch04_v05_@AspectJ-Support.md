# Ch04. AOP

# v05. `@AspectJ` Support

- `@AspectJ` Annotation 기반 AOP 설정
- Spring에서는 `AspectJ`라고 만들어진 라이브러리를 지원한다.

<br>

## 5.1 Enabling `@AspectJ` Support

- `AspectJ`는 외부 라이브러리이므로 Enable 하게끔 처리를 해줘야 한다.

- `@EnableAspectJAutoProxy` annotation 선언

  ```java
  @Configuration
  @EnableAspectJAutoProxy
  public class AppConfig {
  
  }
  ```

<br>

## 5.2 Declaring an Aspect

- Aspect 선언

- `@Aspect` annotation만 추가해주면 된다.

  ```java
  package org.xyz;
  import org.aspectj.lang.annotation.Aspect;
  
  @Aspect
  public class NotVeryUsefulAspect {
  
  }
  ```

<br>

## 5.3 Declaring a Pointcut

- Pointcut 선언

- `@Aspect` 안에다가 Pointcut을 선언한다.

  ```java
  @Pointcut("execution(* transfer(..))") // the pointcut expression
  private void anyOldTransfer() {} // the pointcut signature
  ```

<br>

### 5.3.1 Pointcut 설정 (Supported Pointcut Designators)

- `execution`
  - 가장 간단하게 사용 가능
- `within`
- `this`
- `target`
- `args`
- `@target`
- `@args`
- `@within`
- `@annotation`

<br>

## 5.4 Declaring Advice

- Advice 선언

<br>

### 5.4.1 Before Advice

- `@Before` annotation을 사용하여 Before Advice를 선언할 수 있다.

  ```java
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.Before;
  
  @Aspect
  public class BeforeExample {
  
      @Before("com.xyz.myapp.SystemArchitecture.dataAccessOperation()")
      public void doAccessCheck() {
          // ...
      }
  
  }
  ```

- 기존의 Advice 안에 Pointcut을 바로 넣어줄 수 있다.

  ```java
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.Before;
  
  @Aspect
  public class BeforeExample {
  
      @Before("execution(* com.xyz.myapp.dao..(..))")
      public void doAccessCheck() {
          // ...
      }
  
  }
  ```

<br>

### 5.4.2 After Returning Advice

- `@AfterReturning` annotation을 사용하여 After Returning Advice를 선언할 수 있다.

  ```java
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.AfterReturning;
  
  @Aspect
  public class AfterReturningExample {
  
      @AfterReturning("com.xyz.myapp.SystemArchitecture.dataAccessOperation()")
      public void doAccessCheck() {
          // ...
      }
  
  }
  ```

<br>

### 5.4.3 After Throwing Advice

- `@AfterThrowing` annotation을 사용하여 After Throwing Advice를 선언할 수 있다.

  ```java
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.AfterThrowing;
  
  @Aspect
  public class AfterThrowingExample {
  
      @AfterThrowing("com.xyz.myapp.SystemArchitecture.dataAccessOperation()")
      public void doRecoveryActions() {
          // ...
      }
  
  }
  ```

<br>

### 5.4.4 After (finally) Advice

- `@After` annotation을 사용하여 After Advice를 선언할 수 있다.

  ```java
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.After;
  
  @Aspect
  public class AfterFinallyExample {
  
      @After("com.xyz.myapp.SystemArchitecture.dataAccessOperation()")
      public void doReleaseLock() {
          // ...
      }
  
  }
  ```

<br>

### 5.4.5 Around Advice

- `@Around` annotation을 사용하여 5.4.1 ~ 5.4.4의 모든 기능을 사용할 수 있는 Around Advice를 선언할 수 있다.

- `@Around()` annotation의 매개변수로 Pointcut의 메서드명만 넣어주면 된다.

  ```java
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.Around;
  import org.aspectj.lang.ProceedingJoinPoint;
  
  @Aspect
  public class AroundExample {
  
      @Around("com.xyz.myapp.SystemArchitecture.businessService()")
      public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
          // start stopwatch
          Object retVal = pjp.proceed();
          // stop stopwatch
          return retVal;
      }
  
  }
  ```

  

