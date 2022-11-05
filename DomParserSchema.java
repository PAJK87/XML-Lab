import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class DomParserSchema {

    public static void main(String[] args){
        File xmlFile = new File("ModelDataSchema.xml");
        File xsdFile = new File("ModelData.xsd");

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory sfactory = SchemaFactory.newInstance(language);
        Schema schema = null;
        try {
            schema = sfactory.newSchema(xsdFile);
        } catch (SAXException e3) {
            e3.printStackTrace();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse(xmlFile);
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        
    
        Validator validator = schema.newValidator();
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Validation Passed");


        
    }
    
}
