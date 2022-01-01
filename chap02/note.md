# 아키텍처
`소프트웨어 아키텍처 에는 4가지 영역(계층)이 있습니다. (표현, 응용, 도메인, 인프라)`
1. **표현 영역**(Controler)는 http request 와 response를 처리하는 계층입니다.
2. **응용 영역**(Service)는 내부 로직을 구현합니다. 로직을 직접 구현하기보단 도메인에 로직 구현을 위임합니다. 
3. **도메인 영역**(Order, DTO, VO)은 도메인 모델을 구현하고, 도메인과 관련된 핵심 로직을 구현합니다.
4. **인프라스트럭처 영역** (jpa 를 사용한 경우 Repository) 은 구현 기술을 다룹니다. 다른 ORM 프레임워크나 외부 시스템과 연동을 지원합니다.
  
## (계층구조) 아키텍처

4가지 영역을 사용할때 보편적으로 계층구조 아키텍처를 사용합니다. 이 아키텍처에서는 상위계층이 하위계층에 의존해야하지만 개발 편의성을 위해 **유연하게** 적용합니다. 

```
표현
  v
응용
  v
도메인
  v
인프라스트럭처
```

# DIP(의존 역전 원칙)

  - 원래는 고수준 모듈(상위)이 저수준 모듈(하위)에 의존하던 구조를 반대로 저수준 모듈이 고수준 모듈의 의존하게 합니다 (예시 코드를 봐야 이해가 쉬운데, 핵심은 인터페이스 활용)
  - 이렇게 하는 이유는 저수준 모듈의 변경(ex: mybatis -> jpa 변경)이 고수준 모듈에 저수준 모듈 구현부의 교체와 테스트에서 편의성을 얻기 위함입니다.
  - `고수준 인터페이스`을 implements 해서 `저수준 모듈`을 구현해놓고, `고수준 모듈` 구현시에 저수준 모듈의 기능은 `고수준 인터페이스`를 통해서 사용합니다.
  - `고수준 인터페이스`의 이름을 도메인(고수준)과 연관지어 작성해야합니다. 저수준(인프라)과 연관지어 인터페이스명을 지으면 저수준 모듈 구현부 변경시 인터페이스명도 함께 변경되어야하는 상황이 발생합니다. 그럼 해당 인터페이스를 사용하는 도메인 영역 내부까지 수정이 필요하게됩니다.

# 도메인 영역의 주요구성요소
1. Entity
고유 식별자를 갖는 객체로 자신의 라이프 사이클을 갖습니다. (ex) 주문, 회원, 상품, 혜택) 
`관제지향 DB 테이블`과 동일하다고 여길 수 있는데. `DB 테이블`은 데이터 지향 이고, Entity 는 객체 지향입니다. 그래서 `DB 테이블`은 데이터만 가지고 있지만 Entity 객체는 데이터 이외에 여러 참조 객체(주문 엔터티는 배송지 정보 참조를 가짐)와 도메인 기능을 포함할 수 있습니다.
2. Value
`직관적인 이해할수 있도록 따로 생성한 속성 객체` 또는 `같은 개념을 같은 것들을 하나의 묶음 객체` 입니다.
case1) Money, OrderNo 등의 원시타입 변수 하나만 있는 class 를 생성하여 해당 원시타입 대신에 해당 class 참조 
case2) Order 객체에서, 주문자 정보(Orderer) 관련 된 속성만 나누어서 Class 를 새로 생성하고 해당 class 를 참조
```
public class Orderer {
	private String name;
	private String email;
}
```
3. Aggregate
시스템이 커지면서 점점 복잡해지는 Entity 관계를 그룹 단위로 관리하는 개념입니다. 각 그룹에는 ROOT Entity 가 존재하고, 다른 그룹간에 접근은 ROOT Entity를 통해서만 접근이 가능도록합니다. (3장에 더 자세히설명됨)
4. Repository
Repository 는 Aggregate 단위로 도메인 객체를 DB 에 저장하고 조회하는 기능을 내부에 구현합니다. Repository 인터페이스는 도메인 영역에 속하도록 에그리거트와 연관지어 이름을 짓습니다.
5. Domain Service
  특정 엔터티에 속하지 않는 도메인 로직을 제공합니다. `할인금액계산` 은 상품, 쿠폰, 회원등급, 구매금액등 다양한 Entity와 Value 를 필요로 하는데, 이러한 로직은 도메인 서비스에서 구현합니다.  
# 인프라스트럭처
도메인 객체의 영속성 처리, 트랜잭션, smtp, rest client 등 다른영역에서 필요로 하는 orm 프레임워크, 구현기술, 보조기능을 지원합니다. 도메인 영역에서 생성한 인터페이스를 가지고 내부 구현을 하는게 시스템을 더 유연하게합니다. 
# 모듈
모듈 설계(패키지 구조 설계)에 정답은 없습니다. 위에 4가지 영역별로 패키지 구조를 분할할 수도 있고, 최상단에 에그리거트 단위로 먼저 패키지 분할후 그 하위에 계층에 따라 패키지를 분할할 수도 있습니다.   