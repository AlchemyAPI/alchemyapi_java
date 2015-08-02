package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestAuthor {

	private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

	@Test
	public void html() {
		final String html = ResourceUtils.toString("data/example.html");
		final Document document = alchemyApi.htmlGetAuthor(html, "http://www.test.com/");
		System.out.println(document);
	}

	@Test
	public void url() {
	    final Document document = alchemyApi.urlGetAuthor("http://www.politico.com/blogs/media/2012/02/detroit-news-ed-upset-over-romney-edit-115247.html");
		System.out.println(document);
	}

}
