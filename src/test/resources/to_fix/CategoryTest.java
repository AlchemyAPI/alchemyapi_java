package to_fix;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.parameters.CategoryParameters;
import com.alchemyapi.api.parameters.Parameters;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

class CategoryTest {
    public static void main(String[] args)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        // Create an AlchemyAPI object.
        AlchemyApi alchemyObj = AlchemyApi.GetInstanceFromFile("api_key.txt");

        // Categorize a web URL by topic.
        Document doc = alchemyObj.URLGetCategory("http://www.techcrunch.com/");
        System.out.println(getStringFromDocument(doc));

        // Categorize some text.
        doc = alchemyObj.TextGetCategory("Latest on the War in Iraq.");
        System.out.println(getStringFromDocument(doc));

        // Load a HTML document to analyze.
        String htmlDoc = getFileContents("data/example.html");

        // Categorize a HTML document by topic.
        doc = alchemyObj.HTMLGetCategory(htmlDoc, "http://www.test.com/");
        System.out.println(getStringFromDocument(doc));
        
        CategoryParameters categoryParameters = new CategoryParameters();
        categoryParameters.setOutputMode(Parameters.OUTPUT_RDF);
        doc = alchemyObj.HTMLGetCategory(htmlDoc, "http://www.test.com/", categoryParameters);
        System.out.println(getStringFromDocument(doc));
    }

    // utility function
    private static String getFileContents(String filename)
        throws IOException, FileNotFoundException
    {
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
