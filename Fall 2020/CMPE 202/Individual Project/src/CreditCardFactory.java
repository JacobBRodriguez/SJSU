import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreditCardFactory {
    private static List<String> master_check = new ArrayList<String>(Arrays.asList("1","2","3","4","5"));

    public CreditCard createCreditCard(long cardNumber, String customer, int expirationDate) {

        String cardNum = String.valueOf(cardNumber);

        if(cardNum.substring(0,1).equals("5") && master_check.contains(cardNum.substring(1,2)) && cardNum.length() == 16) {
            return new MasterCard(cardNumber, customer, expirationDate);
        } // end if MasterCard

        else if(cardNum.substring(0,1).equals("4") && (cardNum.length()==13 || cardNum.length()==16)) {
            return new Visa(cardNumber, customer, expirationDate);
        } // end else if Visa

        else if(cardNum.substring(0,1).equals("3") && (cardNum.substring(1,2).equals("4") || cardNum.substring(1,2).equals("7") ) && cardNum.length()==15) {
            return new AmericanExpress(cardNumber, customer, expirationDate);
        } // end else if AmericanExpress

        else if(cardNum.substring(0,4).equals("6011") && cardNum.length()==16) {
            return new Discover(cardNumber, customer, expirationDate);
        } // end else if Discover

        return null;
    } // end createCreditCard
}
// https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/
