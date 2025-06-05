import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class ConfigLoader {

    public static void main(String[] args) throws Exception {

        // ❌ Parsing XML with DocumentBuilder (anti-pattern if relying on local config files)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDoc = builder.parse(new FileInputStream("config.aaa"));  // <-- DETECT: javax.xml.parsers.DocumentBuilder.parse

        // ❌ Loading properties file from filesystem
        Properties props = new Properties();
        FileInputStream propStream = new FileInputStream("app.properties");  // <-- DETECT: FileInputStream("*.properties")
        props.load(propStream);

        // ❌ Reading another XML file directly
        FileInputStream anotherXml = new FileInputStream("another-config.xml"); // <-- DETECT: FileInputStream("*.xml")

        // ❌ Reading from an INI file (also platform-coupled config anti-pattern)
        FileReader iniReader = new FileReader("config.ini");  // <-- DETECT: FileReader("*.ini")
    }
}
