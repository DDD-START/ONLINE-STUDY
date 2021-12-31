public class OrderLine {
    private Product product;
    private int price; // 개당 가격
    private int quantity; // 상품 개수
    private int amounts; // 총금액

    public OrderLine(Product product, int price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private int calculateAmounts() { // 총 금액 계산
        return price * quantity;
    }

    public int getAmounts() {
        return price * quantity;
    }
}
