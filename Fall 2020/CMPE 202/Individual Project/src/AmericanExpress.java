public class AmericanExpress implements CreditCard {
    private long cardNumber;
    private String customer;
    private int expirationDate;

    public AmericanExpress(long cardNumber, String customer, int expirationDate) {
        this.cardNumber = cardNumber;
        this.customer = customer;
        this.expirationDate = expirationDate;
    }

    @Override
    public String getCardType() {
        return "AmericanExpress";
    }
}
