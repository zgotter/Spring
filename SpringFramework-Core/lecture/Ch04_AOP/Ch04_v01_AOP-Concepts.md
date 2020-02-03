# Ch04. AOP

# v01. AOP Concepts

**AOP**

- **A**spect **O**riented **P**rogramming
- 관점 지향 프로그래밍
- 횡단 관심사(cross-cutting concern)의 분리를 허용함으로써 모듈성을 증가시키는 것이 목적인 프로그래밍 패러다임

<br>

**횡단 관심사(cross-cutting concern)**

- 특정 기능이 공통적으로 필요하면 각각의 클래스의 해당 기능을 구현해야 한다.
- 이런 작업들을 반복적으로 해야하기 때문에 불편하다.
- 이를 해결하기 위해 특정 메서드 단위로 공통 사항을 찾는다.
- 공통 사항 중에서 cross-cutting, 즉 횡단 관심사들을 찾게 된다.
- 반복적인 부분을 외부에서 구현하여 반복되는 부분을 줄일 수 있다.
- 횡단 관심사들을 모아서 모듈화를 편하게끔 하는 것을 AOP라고 부른다.

<br>

**Spring에서의 AOP**

- Spring에서는 다음 2가지 방법을 통해 AOP 기능을 사용할 수 있다.
  - 스키마 베이스
  - `@AspectJ` annotation을 이용

<br>

## 1.1 AOP Concepts

### 1.1.1 Aspect

- Spring에서는 Aspect라고 선언된 부분이 횡단 관심사에서 공통적으로 시행되는 부분들을 Aspect에서 관리하고 있다.
- 횡단 관심사에서 실행될 부분의 Before, After 등을 Aspect에서 관리한다.
- ex) Transaction Management

<br>

### 1.1.2 Join Point

- Aspect에서 관리하는 부분을 제외한 비즈니스 로직
- 여러 개가 있을 수 있다.

<br>

### 1.1.3 Advice

- Join Point가 언제 동작할 지에 대한 정보

  1. **Before advice**
     - 특정 Join Point 전에 동작

  2. **After returning advice**
     - Join Point가 다 동작하고 나서 문제가 없을 때 동작
  3. **After throwing advice**
     - Join Point 동작 중 Exception을 발생 시켰을 때 동작
  4. **After (finally) advice**
     - 정상(normal) 또는 에러(exceptional)일 때 모두 동작
  5. **Around advice**
     - 위의 4가지를 하나로 합쳐 놓은 기능
     - Join Point를 감싸고 있다.(surround)
     - 주로 많이 사용

<br>

### 1.1.4 Pointcut

- Join Point에 대해서 판단할 수 있는 것
- 여러 개의 Join Point에 대해서 어떻게 Join Point를 걸 지 Pointcut을 건다.
- 하나의 Pointcut을 통해서 여러 개의 Join Point가 만들어질 수 있다.

<br>

### 1.1.5 Target Object

- 각각의 AOP가 동작되야 될 Target

<br>

### 1.1.6 AOP proxy

- AOP는 proxy 방식으로 동작한다.
  - JDK dynamic proxy
  - CGLIB proxy

<br>

### 1.1.7 Weaving

- Join Point에서 동작하다가 Aspect로 넘어가서 동작을 하는 행위
- Weaving은 다음과 같은 여러 시점에서 발생할 수 있다.
  - compile time
  - runtime
  - Spring AOP (pure Java AOP) $\rightarrow$ runtime