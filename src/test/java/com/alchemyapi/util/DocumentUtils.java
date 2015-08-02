package com.alchemyapi.util;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by kenny
 */
// replace with HtmlSoup
public class DocumentUtils {

    private static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();

    public static String toString(final Document document) {
        try {
            final DOMSource domSource = new DOMSource(document);
            final StringWriter writer = new StringWriter();
            final StreamResult result = new StreamResult(writer);
            final Transformer transformer = TRANSFORMER_FACTORY.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        }
    }

}
