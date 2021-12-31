public class OrderLine {
    private Product product;
    private Money price; // 개당 가격
    private int quantity; // 상품 개수
    private Money amounts; // 총금액

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = new Money(price.getValue()); // 참조 투명성과 관련 문제 해결
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() { // 총 금액 계산
        return price.multiply(quantity);
    }

    public Money getAmounts() {
        return amounts;
    }
}
