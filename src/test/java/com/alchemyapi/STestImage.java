package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.parameters.ImageParameters;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestImage {

    private static final Logger LOGGER = Logger.getLogger(STestImage.class);

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetImage("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    @Test
    public void imageUrl() {
        final Document document = alchemyApi.urlGetRankedImageKeywords("http://farm4.staticflickr.com/3726/11043305726_fdcb7785ec_m.jpg");
        LOGGER.info(document);
    }

    @Test
    public void imageFile() {
        final byte[] imageBytes = ResourceUtils.toBytes("data/cat.jpg");
        final ImageParameters imageParams = new ImageParameters();
        imageParams.setImage(imageBytes);
        imageParams.setImagePostMode(ImageParameters.RAW);
        final Document document = alchemyApi.imageGetRankedImageKeywords(imageParams);
        LOGGER.info(document);
    }

}
