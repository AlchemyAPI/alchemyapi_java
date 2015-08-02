package com.alchemyapi;

import com.alchemyapi.api.AlchemyAPI;
import com.alchemyapi.util.DocumentUtils;
import com.alchemyapi.util.ResourceUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class STestAuthor {
	
    @Test
	public void demo() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
		// Create an AlchemyAPI object.
		final AlchemyAPI alchemyAPI = AlchemyAPI.GetInstanceFromString(ResourceUtils.toString("api_key.txt"));
	        
		// Load a Html document to analyze.
		final String htmlDoc = ResourceUtils.toString("data/example.html");
	    final Document doc = alchemyAPI.URLGetAuthor("http://www.politico.com/blogs/media/2012/02/detroit-news-ed-upset-over-romney-edit-115247.html");
		System.out.println(DocumentUtils.toString(doc));
	        
		final Document doc2 = alchemyAPI.HTMLGetAuthor(htmlDoc, "http://www.test.com/");
		System.out.println(DocumentUtils.toString(doc2));
	}

}
