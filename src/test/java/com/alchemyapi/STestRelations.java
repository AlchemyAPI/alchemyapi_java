package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.parameters.RelationParameters;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestRelations {

    private static final Logger LOGGER = Logger.getLogger(STestRelations.class);

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetRelations("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    @Test
    public void text() {
        // Extract a ranked list of relations from a text string.
        final Document document = alchemyApi.textGetRelations(
                "Hello there, my name is Bob Jones. I live in the United States of America. Where do you live, Fred?");
        LOGGER.info(document);
    }

    @Test
    public void html() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetRelations(html, "http://www.test.com/");
        LOGGER.info(document);
    }

    @Test
    public void parameterizedText() {
		final RelationParameters relationParams = new RelationParameters();
		relationParams.setSentiment(true);
		relationParams.setEntities(true);
		relationParams.setDisambiguate(true);
		relationParams.setSentimentExcludeEntities(true);
		final Document document = alchemyApi.textGetRelations("Madonna enjoys tasty Pepsi. I love her style.", relationParams);
        LOGGER.info(document);
		
		relationParams.setSentiment(true);
		relationParams.setRequireEntities(true);
		relationParams.setSentimentExcludeEntities(true);
		final Document document2 = alchemyApi.textGetRelations("Madonna enjoys tasty Pepsi. I love her style.", relationParams);
        LOGGER.info(document2);
    }

}
