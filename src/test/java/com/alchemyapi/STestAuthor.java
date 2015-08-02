package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestAuthor {

	private static final Logger LOGGER = Logger.getLogger(STestAuthor.class);

	private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

	@Test
	public void html() {
		final String html = ResourceUtils.toString("data/example.html");
		final Document document = alchemyApi.htmlGetAuthor(html, "http://www.test.com/");
		LOGGER.info(document);
	}

	@Test
	public void url() {
	    final Document document = alchemyApi.urlGetAuthor("http://www.politico.com/blogs/media/2012/02/detroit-news-ed-upset-over-romney-edit-115247.html");
		LOGGER.info(document);
	}

}
