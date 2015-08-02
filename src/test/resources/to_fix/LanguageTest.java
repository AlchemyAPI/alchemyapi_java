package to_fix;

import com.alchemyapi.api.AlchemyApi;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class LanguageTest {
    public static void main(String[] args)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        // Create an AlchemyAPI object.
        AlchemyApi alchemyObj = AlchemyApi.GetInstanceFromFile("api_key.txt");

        // Detect the language for a web URL.
        Document doc = alchemyObj.URLGetLanguage("http://news.google.fr/");
        System.out.println(getStringFromDocument(doc));

        // Detect the language for a text string (requires at least 100
        // characters).
        String htmlDoc = getFileContents("data/example.html");
        doc = alchemyObj.TextGetLanguage("This is some english language text.  What language do you speak?");
        System.out.println(getStringFromDocument(doc));

        // Load a HTML document to analyze.
        htmlDoc = getFileContents("data/example.html");

        // Detect the language for a HTML document.
        doc = alchemyObj.HTMLGetLanguage(htmlDoc, "http://www.test.com/");
        System.out.println(getStringFromDocument(doc));
    }

    // utility function
    private static String getFileContents(String filename) throws IOException,
            FileNotFoundException {
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();

        BufferedReader input = new BufferedReader(new FileReader(file));

        try {
            String line = null;

            while ((line = input.readLine()) != null) {
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }
        } finally {
            input.close();
        }

        return contents.toString();
    }

    // utility method
    private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
