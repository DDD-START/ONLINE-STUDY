public class Receiver {
    private String name;
    private String phoneNumber;

    public Receiver(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // 밸류타입인 Receiver는 엔티티와 다르게 모든 값을 비교한다.
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true; // 메모리 주소값 비교
        if (!(other instanceof Receiver)) return false;
        Receiver that = (Receiver) other;
        return this.name.equals(that.name) &&
                this.phoneNumber.equals(that.phoneNumber);
    }
}
