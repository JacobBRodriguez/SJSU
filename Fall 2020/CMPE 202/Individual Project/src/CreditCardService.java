import java.util.Scanner;
public class CreditCardService {

    public static void main(String args[]) {


        FileHandler csv = new CSVHandler();
        FileHandler json = new JsonHandler();
        FileHandler xml = new XmlHandler();

        csv.setSuccessor(json);
        json.setSuccessor(xml);
        Scanner userInput = new Scanner(System.in);

        String input = "";
        String outFile = null;
        while(!input.equals("exit")) {
            System.out.println("Please enter the file path location or type <exit> to exit");
            input = userInput.nextLine();
            if(input.equals("exit"))
                return;
            System.out.println("Please enter the output file name or type <exit> to exit");
            outFile = userInput.nextLine();
            if(outFile.equals("exit"))
                return;
            csv.handleFile(input, outFile);
        }
    }

}
