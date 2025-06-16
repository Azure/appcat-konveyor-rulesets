import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XMLInputFactoryExample {
    public static void main(String[] args) {
        try {
            // Using the system class loader (can be replaced with your custom class loader)
            ClassLoader classLoader = XMLInputFactory.class.getClassLoader();

            // Create XMLInputFactory instance using factoryId and ClassLoader
            XMLInputFactory factory = XMLInputFactory.newFactory("javax.xml.stream.XMLInputFactory", classLoader);

            // Use the factory to create an XMLStreamReader from a sample XML file
            FileInputStream xmlFile = new FileInputStream("sample.xml");
            var reader = factory.createXMLStreamReader(xmlFile);

            while (reader.hasNext()) {
                int event = reader.next();
                System.out.println("Event type: " + event);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (XMLStreamException e) {
            System.err.println("XML stream exception: " + e.getMessage());
        }
    }
}
