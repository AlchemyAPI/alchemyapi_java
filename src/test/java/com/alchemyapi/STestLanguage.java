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
public class STestLanguage {

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetLanguage("http://news.google.fr/");
        System.out.println(document);
    }

    @Test
    public void textEnglish() {
        final Document document = alchemyApi.textGetLanguage("This is some english language text. What language do you speak?");
        System.out.println(document);
    }

    @Test
    public void textJapanese() {
        final Document document = alchemyApi.textGetLanguage("私の名前はケニー・ケーソンです。プログラミングすることが大好きです。");
        System.out.println(document);
    }

    @Test
    public void textChinese() {
        final Document document = alchemyApi.textGetLanguage("你好，我名字叫肯尼。我很喜欢学外语。");
        System.out.println(document);
    }

    @Test
    public void html() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetLanguage(html, "http://www.test.com/");
        System.out.println(document);
    }

}
