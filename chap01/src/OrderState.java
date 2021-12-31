public enum OrderState {
    PAYMENT_WAITING { // 결제대기
        public boolean isShippingChangeable() {
            return true;
        }
    },
    PREPARING { // 상품준비중
        public boolean isShippingChangeable() {
            return true;
        }
    },
    SHIPPED, DELIVERING, DELIVERY_COMPLETED; // 선적중, 배송중, 배송완료
    public boolean isShippingChangeable() {
        return false;
    }
}