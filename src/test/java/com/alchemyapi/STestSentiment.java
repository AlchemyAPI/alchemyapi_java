package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.parameters.KeywordParameters;
import com.alchemyapi.api.parameters.NamedEntityParameters;
import com.alchemyapi.api.parameters.TargetedSentimentParameters;
import com.alchemyapi.helpers.DocumentUtils;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;

public class STestSentiment {

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetTextSentiment("http://www.techcrunch.com/");
        System.out.println(DocumentUtils.toString(document));
    }

    @Test
    public void text() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final Document document = alchemyApi.textGetTextSentiment("That hat is ridiculous, Charles.");
        System.out.println(DocumentUtils.toString(document));
    }

    @Test
    public void file() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetTextSentiment(html, "http://www.test.com/");
        System.out.println(DocumentUtils.toString(document));
    }

    @Test
    public void entityTargetedSentimentText() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final NamedEntityParameters entityParams = new NamedEntityParameters();
        entityParams.setSentiment(true);
        final Document document = alchemyApi.textGetRankedNamedEntities("That Mike Tyson is such a sweetheart.", entityParams);
        System.out.println(DocumentUtils.toString(document));

        final KeywordParameters keywordParams = new KeywordParameters();
        keywordParams.setSentiment(true);
        final Document document2 = alchemyApi.textGetRankedKeywords("That Mike Tyson is such a sweetheart.", keywordParams);
        System.out.println(DocumentUtils.toString(document2));

        final TargetedSentimentParameters sentimentParams = new TargetedSentimentParameters();
        sentimentParams.setShowSourceText(true);
        final Document document3 = alchemyApi.textGetTargetedSentiment("This car is terrible.", "car", sentimentParams);
        System.out.print(DocumentUtils.toString(document3));
    }

    @Test
    public void entityTargetedSentimentUrl() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final TargetedSentimentParameters sentimentParams = new TargetedSentimentParameters();
        sentimentParams.setShowSourceText(true);

        final Document document = alchemyApi.urlGetTargetedSentiment(
                "http://techcrunch.com/2012/03/01/keen-on-anand-rajaraman-how-walmart-wants-to-leapfrog-over-amazon-tctv/",
                "Walmart",
                sentimentParams);
        System.out.print(DocumentUtils.toString(document));
    }

    @Test
    public void entityTargetedSentimentHtml() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        final TargetedSentimentParameters sentimentParams = new TargetedSentimentParameters();
        sentimentParams.setShowSourceText(true);

        final String html = ResourceUtils.toString("data/example.html");
        final Document document2 = alchemyApi.htmlGetTargetedSentiment(html, "http://www.test.com/", "WujWuj", sentimentParams);
        System.out.print(DocumentUtils.toString(document2));
    }

}
