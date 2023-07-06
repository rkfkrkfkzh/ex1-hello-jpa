
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





