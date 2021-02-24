import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Tester {

    @Test
    public void testCreditCardFactory() {
        CreditCardFactory factory = new CreditCardFactory();
        long masterTest = 5300000000000001L;
        long visaTest1, visaTest2;
        visaTest1 = 4000000000001L;
        visaTest2 = 4000000000000001L;
        // Create card types for asserts
        CreditCard master = new MasterCard(masterTest, "John", 5212025);
        CreditCard visa1 = new Visa(visaTest1, "John", 5212025);
        CreditCard visa2 = new Visa(visaTest2, "John", 5212025);
        CreditCard american = new AmericanExpress(340000000000001L, "John", 5212025);
        CreditCard discover = new Discover(6011000000000001L, "John", 5212025);
        // assert statements

        assertEquals(master.getCardType(), factory.createCreditCard(masterTest, "John", 5212025).getCardType());
        assertEquals(visa1.getCardType(), factory.createCreditCard(visaTest1, "John", 5212025).getCardType());
        assertEquals(visa2.getCardType(), factory.createCreditCard(visaTest2, "John", 5212025).getCardType());
        assertEquals(american.getCardType(), factory.createCreditCard(340000000000001L, "John", 5212025).getCardType());
        assertEquals(discover.getCardType(), factory.createCreditCard(6011000000000001L, "John", 5212025).getCardType());
        assertEquals(null, factory.createCreditCard(4000000000000000001L, "John", 5212025));


    }// end testCreditCardFactory

    @Test
    public void testCSVHandlerOutputToFile() throws IOException {
        FileUtils utility = new FileUtils();
        FileHandler csvHandler = new CSVHandler();
        String testCSVInput = "Testing_Files/tester_csv.csv";
        String testCSVOutput = "Testing_Files/tester_out_actual.csv";
        // creating test File output to compare
        File testOutFile =  new File("Testing_Files/tester_out_test.csv");
        // using test input and output path to have function parse data
        csvHandler.handleFile(testCSVInput, testCSVOutput);
        // creating File object of test output to compare with tester output
        File actualOutFile = new File(testCSVOutput);

        assertEquals(FileUtils.readLines(testOutFile, "UTF-8"), FileUtils.readLines(actualOutFile, "UTF-8"));
    } // end testCSVHandlerOutputToFile

    @Test
    public void testJSONHandlerOutputToFile() throws IOException {
        FileUtils utility = new FileUtils();
        FileHandler jsonHandler = new JsonHandler();
        String testJSONInput = "Testing_Files/tester_json.json";
        String testJSONOutput = "Testing_Files/tester_out_actual.json";
        // creating test File output to compare
        File testOutFile =  new File("Testing_Files/tester_out_test.json");
        // using test input and output path to have function parse data
        jsonHandler.handleFile(testJSONInput, testJSONOutput);
        // creating File object of test output to compare with tester output
        File actualOutFile = new File(testJSONOutput);

        assertEquals(FileUtils.readLines(testOutFile, "UTF-8"), FileUtils.readLines(actualOutFile, "UTF-8"));
    } // end testCSVHandlerOutputToFile

    @Test
    public void testXMLHandlerOutputToFile() throws IOException {

        FileHandler xmlHandler = new XmlHandler();
        String testXMLInput = "Testing_Files/tester_xml.xml";
        String testXMLOutput = "Testing_Files/tester_out_actual.xml";
        // creating test File output to compare
        File testOutFile =  new File("Testing_Files/tester_out_test.xml");
        // using test input and output path to have function parse data
        xmlHandler.handleFile(testXMLInput, testXMLOutput);
        // creating File object of test output to compare with tester output
        File actualOutFile = new File(testXMLOutput);

        assertEquals(FileUtils.readLines(testOutFile, "UTF-8"), FileUtils.readLines(actualOutFile, "UTF-8"));
    }

}
