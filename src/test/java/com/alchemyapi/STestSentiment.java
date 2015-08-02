package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.parameters.KeywordParameters;
import com.alchemyapi.api.parameters.NamedEntityParameters;
import com.alchemyapi.api.parameters.TargetedSentimentParameters;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestSentiment {

    private static final Logger LOGGER = Logger.getLogger(STestSentiment.class);

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetTextSentiment("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    @Test
    public void text() {
        final Document document = alchemyApi.textGetTextSentiment("That hat is ridiculous, Charles.");
        LOGGER.info(document);
    }

    @Test
    public void html() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetTextSentiment(html, "http://www.test.com/");
        LOGGER.info(document);
    }

    @Test
    public void entityTargetedSentimentText() {
        final NamedEntityParameters entityParams = new NamedEntityParameters();
        entityParams.setSentiment(true);
        final Document document = alchemyApi.textGetRankedNamedEntities("That Mike Tyson is such a sweetheart.", entityParams);
        LOGGER.info(document);

        final KeywordParameters keywordParams = new KeywordParameters();
        keywordParams.setSentiment(true);
        final Document document2 = alchemyApi.textGetRankedKeywords("That Mike Tyson is such a sweetheart.", keywordParams);
        LOGGER.info(document2);

        final TargetedSentimentParameters sentimentParams = new TargetedSentimentParameters();
        sentimentParams.setShowSourceText(true);
        final Document document3 = alchemyApi.textGetTargetedSentiment("This car is terrible.", "car", sentimentParams);
        System.out.print(document3);
    }

    @Test
    public void entityTargetedSentimentUrl() {
        final TargetedSentimentParameters sentimentParams = new TargetedSentimentParameters();
        sentimentParams.setShowSourceText(true);

        final Document document = alchemyApi.urlGetTargetedSentiment(
                "http://techcrunch.com/2012/03/01/keen-on-anand-rajaraman-how-walmart-wants-to-leapfrog-over-amazon-tctv/",
                "Walmart",
                sentimentParams);
        System.out.print(document);
    }

    @Test
    public void entityTargetedSentimentHtml() {
        final TargetedSentimentParameters sentimentParams = new TargetedSentimentParameters();
        sentimentParams.setShowSourceText(true);

        final String html = ResourceUtils.toString("data/example.html");
        final Document document2 = alchemyApi.htmlGetTargetedSentiment(html, "http://www.test.com/", "WujWuj", sentimentParams);
        System.out.print(document2);
    }

}
