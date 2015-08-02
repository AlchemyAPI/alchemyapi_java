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
public class STestTextExtract {

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    /**
     * Extract page text from a web URL. Ignoring ads, navigation links,and other content.
     */
    @Test
    public void urlClean() {
        final Document document = alchemyApi.urlGetText("http://www.techcrunch.com/");
        System.out.println(document);
    }

    /**
     * Extract raw page text from a web URL. Including ads, navigation, and other content.
     */
    @Test
    public void urlRaw() {
        final Document document = alchemyApi.urlGetRawText("http://www.techcrunch.com/");
        System.out.println(document);
    }

    /**
     * Extract a title from a web URL.
     */
    @Test
    public void urlTitle() {
        final Document document = alchemyApi.urlGetTitle("http://www.techcrunch.com/");
        System.out.println(document);
    }

    /**
     * Extract page text from html. Ignoring ads, navigation links,and other content.
     */
    @Test
    public void htmlClean() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetText(html, "http://www.test.com/");
        System.out.println(document);
    }

    /**
     * Extract page text from html. Including ads, navigation links,and other content.
     */
    @Test
    public void htmlRaw() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetRawText(html, "http://www.test.com/");
        System.out.println(document);
    }

    /**
     * EExtract a title from html.
     */
    @Test
    public void htmlTitle() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetTitle(html, "http://www.test.com/");
        System.out.println(document);
    }

}
