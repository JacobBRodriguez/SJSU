import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler implements FileHandler {

    private FileHandler successor = null;
    private CreditCardFactory factory = new CreditCardFactory();

    public void handleFile(String file, String fileOut) {

        if(file.contains(".json")) {
            JSONParser parser = new JSONParser();

            try {
                JSONArray a = (JSONArray) parser.parse(new FileReader(file));

                FileWriter myWriter = new FileWriter(fileOut);
                long number;
                int date;
                String name, tempDate;

                JSONArray holdArray = new JSONArray();
                JSONObject jsonObject = null;
                for (Object item : a) {
                    JSONObject entry = (JSONObject) item;

                    try {
                        number = Long.parseLong(entry.get("CardNumber").toString());
                        tempDate = entry.get("ExpirationDate").toString().replaceAll("/", "");
                        date = Integer.parseInt(tempDate);
                        name = entry.get("NameOfCardholder").toString();

                        CreditCard card = factory.createCreditCard(number, name, date);

                        //Creating a JSONObject object
                        jsonObject = new JSONObject();

                        jsonObject.put("CardNumber", entry.get("CardNumber").toString());
                        if(card == null) {
                            jsonObject.put("Card Type", "Not a valid card");
                        }

                        else {
                            jsonObject.put("Card Type", card.getCardType());
                        }
                        holdArray.add(jsonObject);

                    } catch (NumberFormatException e) {
                        jsonObject.put("CardNumber", entry.get("CardNumber").toString());
                        jsonObject.put("Card Type", "Not a valid card");
                        holdArray.add(jsonObject);

                    }

                } // end for
                myWriter.write(holdArray.toJSONString());
                myWriter.close();
            } catch (IOException | ParseException e) {

            }
        } // end if

        else {
            if(successor != null) {
                successor.handleFile(file, fileOut);
            }
            else {
                System.out.println("File format unsupported");
            }
        } //end else


    } // end handleFile

    public void setSuccessor(FileHandler next) {
        this.successor = next;
    } // end setSuccessor
}
