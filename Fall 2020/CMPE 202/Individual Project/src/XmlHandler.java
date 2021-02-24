import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class XmlHandler implements FileHandler {

    private FileHandler successor = null;
    private CreditCardFactory factory = new CreditCardFactory();

    public void handleFile(String file, String fileOut) {

        if(file.contains(".xml")) {
            try {

                FileWriter myWriter = new FileWriter(fileOut);
                long number;
                int date;
                String name, tempDate;

                File xmlFile = new File(file);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                // create new root for fileOut output
                Document new_dom = dBuilder.newDocument();
                Element newRoot = new_dom.createElement("root");

                NodeList nList = doc.getElementsByTagName("row");

                for(int temp = 0;temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    // Have to make sure node is legitimate before proceeding
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        try {
                            number = Long.parseLong(eElement.getElementsByTagName("CardNumber").item(0)
                                    .getTextContent());
                            tempDate = eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent()
                                    .replaceAll("/", "");

                            date = Integer.parseInt(tempDate);
                            name = eElement.getElementsByTagName("NameOfCardholder").item(0)
                                    .getTextContent();

                            CreditCard card = factory.createCreditCard(number, name, date);

                            //Creating a new row instance to add children underneath
                            Element new_row = new_dom.createElement("row");

                            // Create new entry for card number
                            Element card_number = new_dom.createElement("CardNumber");
                            card_number.appendChild(new_dom.createTextNode(eElement.getElementsByTagName("CardNumber").item(0)
                                    .getTextContent()));
                            new_row.appendChild(card_number);

                            // Create new entry for card type
                            Element card_type = new_dom.createElement("CardType");

                            if(card == null) {
                                // Create new entry for card type
                                card_type.appendChild(new_dom.createTextNode("Not a valid card"));
                            }

                            else {
                                card_type.appendChild(new_dom.createTextNode(card.getCardType()));
                            }
                            new_row.appendChild(card_number);
                            new_row.appendChild(card_type);
                            newRoot.appendChild(new_row);

                        } catch (NumberFormatException e) {
                            //Creating a new row instance to add children underneath
                            Element new_row = new_dom.createElement("row");
                            // Create new entry for card number
                            Element card_number = new_dom.createElement("CardNumber");
                            card_number.appendChild(new_dom.createTextNode(eElement.getElementsByTagName("CardNumber").item(0)
                                    .getTextContent()));
                            new_row.appendChild(card_number);

                            // Create new entry for card type
                            Element card_type = new_dom.createElement("CardType");
                            card_type.appendChild(new_dom.createTextNode("Not a valid card"));
                            new_row.appendChild(card_type);
                            newRoot.appendChild(new_row);
                        }

                    } // end if
                } // end for

                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(newRoot),
                        new StreamResult(new FileOutputStream(fileOut)));

            } catch(Exception e) {
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
        } //end else

    } // end handleFile

    public void setSuccessor(FileHandler next) {
        this.successor = next;
    } // end setSuccessor

}
// credit for parsing: https://stackoverflow.com/questions/428073/what-is-the-best-simplest-way-to-read-in-an-xml-file-in-java-application