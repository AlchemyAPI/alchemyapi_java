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
public class STestKeyword {

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetRankedKeywords("http://www.techcrunch.com/");
        System.out.println(document);
    }

    @Test
    public void text() {
        final Document document = alchemyApi.textGetRankedKeywords(
                "Hello there, my name is Bob Jones. I live in the United States of America. Where do you live, Fred?");
        System.out.println(document);
    }

    @Test
    public void html() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetRankedKeywords(html, "http://www.test.com/");
        System.out.println(document);
    }

}
