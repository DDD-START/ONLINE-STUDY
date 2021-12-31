import java.util.List;

/*
< 주문 관련 요구 사항 >
1. 최소 한 종류 이상의 상품을 주문해야 한다 .
2. 한 상품을 한개 이상 주문할 수 있다 .
3. 총 주문 금액은 각 상품의 구매 가격합을 모두 더한 금액 이다 .
4. 각 상품의 구매 가격 합은 상품 가격에 구매 개수를 곱한 값 이다 .
5. 주문할 때 배송지 정보를 반드시 지정해야 한다 .
6. 배송지 정보는 받는 사람 이름, 전화번호, 주소로 구성 된다 .
7. 출고를 하면 배송지 정보를 변경 할 수 없다 .
8. 출고 전에 주문을 취소할 수 있다 .
9. 고객이 결제를 완료 하기 전에는 상품을 준비 하지 않는다 .
* */
public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;
    private List<OrderLine> orderLines;
    private int totalAmounts;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo); //
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null)
            throw new IllegalArgumentException("no shipping info");
        this.shippingInfo = shippingInfo;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines); // 요구사항 1: 최소 1개 이상
        this.orderLines = orderLines;
        calculateTotalAmounts(); // 요구사항 3: 총 주문 합계 계산

    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = orderLines.stream().mapToInt(OrderLine::getAmounts).sum();
    }

    // 1 배송지 정보 변경하기
    public void changeShippingInfo(ShippingInfo newShippingInfo) { // 규칙1: 배송지 변경
        verifyNotYetShipped();
        this.shippingInfo = newShippingInfo;
    }

    private void verifyNotYetShipped() { // 리팩토링: isShippingChangeable에서 변경
        if (state != OrderState.PAYMENT_WAITING & state != OrderState.PREPARING)
            throw new IllegalStateException("aleady shipped");
    }

    // 2 출고 상태로 변경하기
    public void changeShipped() {
        // 로직 검사
        this.state = OrderState.SHIPPED;
    }

    // 3 주문 취소하기
    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    // 4 결제완료로 변경하기
    public void completePayment() {
        // ...
    }
}
