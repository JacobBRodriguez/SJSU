import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler implements FileHandler {

    private FileHandler successor = null;
    private String splitBy = ",";
    private String line = "";
    private CreditCardFactory factory = new CreditCardFactory();

    public void handleFile(String file, String fileOut) {

        if(file.contains(".csv")) {

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                FileWriter myWriter = new FileWriter(fileOut);
                long number;
                int date;
                String name, tempDate;

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] ccInfo = line.split(splitBy);
                    try {
                        number = Long.parseLong(ccInfo[0]);
                        tempDate = ccInfo[1].replaceAll("/", "");
                        date = Integer.parseInt(tempDate);
                        name = ccInfo[2];
                        CreditCard card = factory.createCreditCard(number, name, date);

                        myWriter.write(ccInfo[0]);
                        if(card == null) {
                            myWriter.write(",Not a valid card\r\n");
                        }

                        else {
                            myWriter.write(",");
                            myWriter.write(card.getCardType());
                            myWriter.write("\r\n");
                        }

                    } catch (NumberFormatException e) {
                        myWriter.write(ccInfo[0]);
                        myWriter.write(",Not a valid card\r\n");
                    }

                } // end while
                myWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end if

        else {
            if(successor != null) {
                successor.handleFile(file, fileOut);
            }
            else {
                System.out.println("File format unsupported");
            }
        }


    } // end handleFile

    public void setSuccessor(FileHandler next) {
        this.successor = next;
    } // end setSuccessor
}
// Credit for CSV reading: https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
