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
public class STestConcept {

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetRankedConcepts("http://www.techcrunch.com/");
        System.out.println(document);
    }

     @Test
     public void text() {
         final Document document = alchemyApi.textGetRankedConcepts(
                 "This thing has a steering wheel, tires, and an engine. Do you know what it is?");
         System.out.println(document);
     }

    @Test
    public void html() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetRankedConcepts(html, "http://www.test.com/");
        System.out.println(document);
    }

}
