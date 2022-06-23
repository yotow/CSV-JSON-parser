import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public final String JSON_FILENAME = "data2.json";
    private static final List<Employee> employees = new ArrayList<>();

    public List<Employee> parseXML(String s) {

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            document = builder.parse(new File(s));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        Node root = document.getDocumentElement();
        read(root);
        return employees;
    }

    private static void read(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Element element = (Element) node_;
                try {
                    long id = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    employees.add(new EmployeeBuilder().age(age).id(id).lastName(lastName).firstName(firstName).country(country).build());
                } catch (Exception e) {
                    //ignore
                }
                NamedNodeMap map = element.getAttributes();
                for (int c = 0; c < map.getLength(); c++) {
                    String attrName = map.item(c).getNodeName();
                    String attrVal = map.item(c).getNodeValue();
                    System.out.println(attrName + " " + attrVal);
                }
                read(node_);
            }
        }
    }
}
