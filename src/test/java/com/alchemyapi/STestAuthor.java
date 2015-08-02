package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.helpers.TestApiFactory;
import com.alchemyapi.helpers.DocumentUtils;
import com.alchemyapi.helpers.ResourceUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class STestAuthor {

	private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

	@Test
	public void parseFromTestData() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
		final String html = ResourceUtils.toString("data/example.html");

		final Document document = alchemyApi.htmlGetAuthor(html, new URL("http://www.test.com/"));
		System.out.println(DocumentUtils.toString(document));
	}

	@Test
	public void parseFromUrl() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
	    final Document document = alchemyApi.urlGetAuthor(new URL("http://www.politico.com/blogs/media/2012/02/detroit-news-ed-upset-over-romney-edit-115247.html"));
		System.out.println(DocumentUtils.toString(document));
	}

}
