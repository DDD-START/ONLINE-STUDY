
/*
< 주문 관련 요구 사항 >
1. 최소 한 종류 이상의 상품을 주문해야 한다 .
2. 한 상품을 한개 이상 주문할 수 있다 .
3. 총 주문 금액은 각 상품의 구매 가격합을 모두 더한 금액 이다 .
4. 각 상품의 구매 가격 합은 상품 가격에 구매 개수를 곱한 값 이다 .
5. 주문할 때 배송지 정보를 반드시 지정해야 한 다 .
6. 배송지 정보는 받는 사람 이름, 전화번호, 주소로 구성 된다 .
7. 출고를 하면 배송지 정보를 변경 할 수 없다 .
8. 출고 전에 주문을 취소할 수 있다 .
9. 고객이 결제를 완료 하기 전에는 상품을 준비 하지 않는다 .
* */
public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;

    // 1 배송지 정보 변경하기
    public void changeShippingInfo(ShippingInfo newShippingInfo) { // 규칙1: 배송지 변경
        if (!isShippingChangeable()) {
            throw new IllegalStateException("can’t change shipping in" + state);
        }
        this.shippingInfo = newShippingInfo;
    }

    private boolean isShippingChangeable() {
        return state == OrderState.PAYMENT_WAITING ||
                state == OrderState.PREPARING;
    }

    // 2 출고 상태로 변경하기
    public void changeShipped() {
        // 로직 검사
        this.state = OrderState.SHIPPED;
    }

    // 3 주문 취소하기
    public void cancel() {
        // ...
    }

    // 4 결제완료로 변경하기
    public void completePayment() {
        // ...
    }
}
