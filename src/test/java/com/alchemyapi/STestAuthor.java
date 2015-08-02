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

	private final AlchemyAPI alchemyAPI = AlchemyAPI.GetInstanceFromString(ResourceUtils.toString("api_key.txt"));

	@Test
	public void parseFromTestData() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
		final String html = ResourceUtils.toString("data/example.html");

		final Document document = alchemyAPI.HTMLGetAuthor(html, "http://www.test.com/");
		System.out.println(DocumentUtils.toString(document));
	}

	@Test
	public void parseFromUrl() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
	    final Document document = alchemyAPI.URLGetAuthor("http://www.politico.com/blogs/media/2012/02/detroit-news-ed-upset-over-romney-edit-115247.html");
		System.out.println(DocumentUtils.toString(document));
	}

}
