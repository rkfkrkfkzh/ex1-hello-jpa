
### 조인 전략
#### 장점
+ 테이블 정규화
+ 외래키 참조 무결성 제약조건 활용가능
+ 저장공간 효율화
#### 단점
+ 조회시 조인을 많이 사용, 성능저하
+ 조회 쿼리가 복잡
+ 데이터 저장시 insert sql 2번호출
***

### 단일 테이블 전략
#### 장점
+ 조인이 필요없어 조회 성능이 빠름
+ 조회 쿼리 단순
#### 단점
+ 자식 엔티티가 매핑한 column은 모두 null 허용
+ 단일 테이블에 모든 것 저장하므로 테이블이 커질 수 있는 상황에 따라서 조회 성는이 오히려 느려질 수 있다.
  (임계점 넘는 일 거의 무)
---
#### @Inheritance(strategy = InheritanceType.JOINED) -> 조인 전략
#### @DiscriminatorColumn 단일테이블전략 사용시 안써도 필수로 적용됨 default D type
#### InheritanceType.SINGLE_TABLE -> 단일테이블전략
#### InheritanceType.TABLE_PER_CLASS -> 구현 클래스마다 테이블 전략
#### @MappedSuperclass 
+ 상속관계 매핑 no, Entity No, table mapping no
+ 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
+ 조회, 검색(em.find()불가)
+ 직접 생성해서 사용할 일이 없으므로 추상 클래스 권장
+ 주로 등록일, 수정일, 등록자, 수정자 같은 전체 Entity에서 공통으로 적용하는 정보를 모을때 사용
+ @Entity, @MappedSuperclass로 지정 클래스만 상속가능
***
#### Inheritance 계승
#### strategy 전략
#### Discriminator 판별기
***
### 프록시의 특징
+ 프록시 객체는 처음 사용할 떄 1번만 초기화
+ 프록시 객체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아니다. 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능
+ 프록시 개체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야함 (==비교 실패, 대신 instance of 사용)
+ 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환
+ 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화할때 문제 발생(하이버네이트는 org.hibernate.LazyInitializationException 예외 터짐)
***
### 프록시 확인
+ emf.getPersistenceUnitUtil().isLoaded(); 프록시 인스턴스의 초기화 여부 확인
+ Hibernate.initialize(reference); 프록시 강제 초기화(JPA 내부에서는 강제호출로 초기화)
***
### 영속성 전이 : CASCADE
+ 특정 엔티티를 영속 상태로 만들때 연관된 엔티티도 함께 영속상태로 만들고 싶을때
+ ex) Parent Entity를 저장할 때 Child Entity도 함꼐 저장
+ @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
### 영속성 전이 + 고아객체, 생명주기
+ cascade = CascadeType.ALL, orphanRemoval = true
+ 스스로 생명주기를 관리하는 Entity는 em.persist()로 영속화, em.remove()로 제거
+ 두 옵션을 모두 활성화 하면 부모 Entity를 통해 자식의 생명주기를 관리할 수 있다.
+ 도메인 주도 설계(DDD)의 Aggregate Root개념을 구현할 때 유용
***
### 객체 타입의 한계
+ 항상 값을 복사해서 사용하면 공유 참조로 인해 발생하는 부작용을 피할 수 있다.
+ 문제는 임베디드 타입처럼 직접 정의한 값 타입은 자바의 기본 타입이 아니라 객체 타입이다.
+ 자바 기본 타입에 값을 대입하면 값을 복사한다.
+ 객체 타입은 참조 값을 직접 대입하는 것을 막을 방법이 없다.
+ 객체의 공유 참조는 피할 수 없다.
### 불변 객체
+ 객체 타입을 수정할 수 없게 만들면 부작용을 원천 차단
+ 값 타입은 변 객체(immutable object)로 설계
+ 불변 객체 : 생성 시점 이후 절대 값을 변경할 수 없는 객체
+ 생성자로만 값을 설정, 수정자(setter)를 만들지 않으면 됨
+ 참고 : Integer, String은 자바가 제공하는 대표적 불변객체






