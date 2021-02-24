public class Discover implements CreditCard {
    private long cardNumber;
    private String customer;
    private int expirationDate;

    public Discover(long cardNumber, String customer, int expirationDate) {
        this.cardNumber = cardNumber;
        this.customer = customer;
        this.expirationDate = expirationDate;
    }

    @Override
    public String getCardType() {
        return "Discover";
    }
}
