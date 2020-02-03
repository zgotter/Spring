# Ch04. AOP

# v03. AOP Proxies

## 3.1 Proxy

- A라는 클래스가 있고 이 클래스를 감싸는 A Proxy라는 클래스가 있다.
- 외부에서는 A Proxy로 실행시키고 이 Proxy에서 특정 코드를 실행시킨다.
- 그런 다음에 다시 A 클래스를 동작시키고 이 Proxy가 원하는 대로 내 코드를 동작시키는 형태를 Proxy라고 한다.

<br>

## 3.2 Proxy를 만드는 방법

- Java 코드로 만듬
- Java SDK의 dynamic proxies를 이용해 만듬
  - Interface만 제공
  - 구현 클래스인 경우에는 dynamic proxies 사용 불가
- CGLIB proxies 사용
  - byte code를 직접 조작하여 proxy 동작을 하도록 만듬

<br>

## 3.3 Understanding AOP Proxies

- [Understanding AOP Proxies](https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/core.html#aop-understanding-aop-proxies)

