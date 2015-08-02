package com.alchemyapi;

import com.alchemyapi.api.AlchemyAPI;
import com.alchemyapi.api.AlchemyAPI_KeywordParams;
import com.alchemyapi.api.AlchemyAPI_NamedEntityParams;
import com.alchemyapi.api.AlchemyAPI_TargetedSentimentParams;
import com.alchemyapi.util.DocumentUtils;
import com.alchemyapi.util.ResourceUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class STestSentiment {

    private final AlchemyAPI alchemyAPI = AlchemyAPI.GetInstanceFromString(ResourceUtils.toString("api_key.txt"));

    @Test
    public void url() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        final Document document = alchemyAPI.URLGetTextSentiment("http://www.techcrunch.com/");
        System.out.println(DocumentUtils.toString(document));
    }

    @Test
    public void text() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final Document document = alchemyAPI.TextGetTextSentiment("That hat is ridiculous, Charles.");
        System.out.println(DocumentUtils.toString(document));
    }

    @Test
    public void file() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final String html = ResourceUtils.toString("data/example.html");

        final Document document = alchemyAPI.HTMLGetTextSentiment(html, "http://www.test.com/");
        System.out.println(DocumentUtils.toString(document));
    }

    @Test
    public void entityTargetedSentimentText() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final AlchemyAPI_NamedEntityParams entityParams = new AlchemyAPI_NamedEntityParams();
        entityParams.setSentiment(true);
        final Document document = alchemyAPI.TextGetRankedNamedEntities("That Mike Tyson is such a sweetheart.", entityParams);
        System.out.println(DocumentUtils.toString(document));

        final AlchemyAPI_KeywordParams keywordParams = new AlchemyAPI_KeywordParams();
        keywordParams.setSentiment(true);
        final Document document2 = alchemyAPI.TextGetRankedKeywords("That Mike Tyson is such a sweetheart.", keywordParams);
        System.out.println(DocumentUtils.toString(document2));

        final AlchemyAPI_TargetedSentimentParams sentimentParams = new AlchemyAPI_TargetedSentimentParams();
        sentimentParams.setShowSourceText(true);
        final Document document3 = alchemyAPI.TextGetTargetedSentiment("This car is terrible.", "car", sentimentParams);
        System.out.print(DocumentUtils.toString(document3));
    }

    @Test
    public void entityTargetedSentimentUrl() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final AlchemyAPI_TargetedSentimentParams sentimentParams = new AlchemyAPI_TargetedSentimentParams();
        sentimentParams.setShowSourceText(true);

        final Document document = alchemyAPI.URLGetTargetedSentiment("http://techcrunch.com/2012/03/01/keen-on-anand-rajaraman-how-walmart-wants-to-leapfrog-over-amazon-tctv/", "Walmart", sentimentParams);
        System.out.print(DocumentUtils.toString(document));
    }

    @Test
    public void entityTargetedSentimentHtml() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final AlchemyAPI_TargetedSentimentParams sentimentParams = new AlchemyAPI_TargetedSentimentParams();
        sentimentParams.setShowSourceText(true);

        final String html = ResourceUtils.toString("data/example.html");
        final Document document2 = alchemyAPI.HTMLGetTargetedSentiment(html, "http://www.test.com/", "WujWuj", sentimentParams);
        System.out.print(DocumentUtils.toString(document2));
    }


}
