### [SOLID 나만의 용어로 설명하기]

SRP(단일 책임 원칙)
- 객체가 단일 책임을 가지는지 항상 고민하자.
- 객체의 public 메서드는 객체의 책임 안에서 이루어지는 동작이어야 한다.

OCP(계방 폐쇠 원칙)
- 요구 사항 변경을 생각하자.
- 인터페이스 형식으로 요구사항을 분리하고, 내부에서는 추상화된 인터페이스를 참고해서 사용만 한다.
    - 구체 요구사항 내용은 안에서 모름. 바깥에서 변경해서 주입해주면 됨.

LSP(리스코프 치환 원칙)
- 상속 관계에서, 자식은 부모 메서드를 이상하게 변형 하면안된다.
- 부모 타입으로 실행하거나, 자식타입으로 실행하거나 같은 동작이어야 함.

ISP(인터페이스 분리 원칙)
- 인터 페이스를 구현할 떄, 인터페이스도 역할에 맡게 쪼개자.
- ex) “게임시작”, “게임초기화”는 어떤 게임이냐에 따라 초기화 부분이 없을 수 있음.
    - Runnable, Initializable로 분리해서 필요에 맞게 Implements 한다.

DIP(의존성 역전 원칙)
- 의존성을 직접 생성하기보다는, 상위에서 생성하고 주입받아서 사용하자.
- 주입받을 때 인터페이스 타입으로 받는다면, 내부 구현에 의존하지 않고 유연하게 사용할 수 있다.