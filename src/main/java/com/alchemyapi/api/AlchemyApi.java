package com.alchemyapi.api;

import com.alchemyapi.api.exceptions.AlchemyApiException;
import com.alchemyapi.api.parameters.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import static org.apache.commons.lang3.StringUtils.length;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Created by kenny
 */
public class AlchemyApi {

    private static final Logger LOGGER = Logger.getLogger(AlchemyApi.class);

    private static final String API_URL = "http://{SUB_DOMAIN}.alchemyapi.com/calls/";

    private final AlchemyApiConfiguration configuration;

    public AlchemyApi(final AlchemyApiConfiguration configuration) {
        if(configuration == null) { throw new AlchemyApiException("Configuration must not be null"); }
        valididateConfiguration(configuration);
        LOGGER.info("Loaded Configuration: " + configuration);
        this.configuration = configuration;
    }

    private static void valididateConfiguration(final AlchemyApiConfiguration configuration) {
        if(length(configuration.getApiKey()) < 5) { throw new AlchemyApiException("API key must be at least 5 characters"); }
    }

    public Document urlGetAuthor(final String url) {
        return urlGetAuthor(url, new Parameters());
    }

    public Document urlGetAuthor(final String url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetAuthor", "url", params);
    }

    public Document htmlGetAuthor(final String html, final String url) {
        return htmlGetAuthor(html, url, new Parameters());
    }

    public Document htmlGetAuthor(final String html, final String url, final Parameters params) {
        params.setHtml(html);
        params.setUrl(url);
        return post("HTMLGetAuthor", "html", params);
    }
    public Document urlGetEmotion(final String url) {
        return urlGetEmotion(url, new EmotionParameters());
    }

    public Document urlGetEmotion(final String url, final EmotionParameters params) {
        params.setUrl(url);
        return get("URLGetEmotion", "url", params);
    }

    public Document htmlGetEmotion(final String html, final String url) {
        return htmlGetEmotion(html, url, new EmotionParameters());
    }

    public Document htmlGetEmotion(final String html, final String url, final EmotionParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetEmotion", "html", params);
    }

    public Document textGetEmotion(final String text) {
        return textGetEmotion(text, new EmotionParameters());
    }

    public Document textGetEmotion(final String text, final EmotionParameters params) {
        params.setText(text);
        return post("TextGetEmotion", "text", params);
    }

    public Document urlGetRankedNamedEntities(final String url) {
        return urlGetRankedNamedEntities(url, new NamedEntityParameters());
    }

    public Document urlGetRankedNamedEntities(final String url, final NamedEntityParameters params) {
        params.setUrl(url);
        return get("URLGetRankedNamedEntities", "url", params);
    }

    public Document htmlGetRankedNamedEntities(final String html, final String url) {
        return htmlGetRankedNamedEntities(html, url, new NamedEntityParameters());
    }

    public Document htmlGetRankedNamedEntities(final String html, final String url, final NamedEntityParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRankedNamedEntities", "html", params);
    }

    public Document textGetRankedNamedEntities(final String text) {
        return textGetRankedNamedEntities(text, new NamedEntityParameters());
    }

    public Document textGetRankedNamedEntities(final String text, final NamedEntityParameters params) {
        params.setText(text);
        return post("TextGetRankedNamedEntities", "text", params);
    }

    public Document urlGetRankedConcepts(final String url) {
        return urlGetRankedConcepts(url, new ConceptParameters());
    }

    public Document urlGetRankedConcepts(final String url, final ConceptParameters params) {
        params.setUrl(url);
        return get("URLGetRankedConcepts", "url", params);
    }

    public Document htmlGetRankedConcepts(final String html, final String url) {
        return htmlGetRankedConcepts(html, url, new ConceptParameters());
    }

    public Document htmlGetRankedConcepts(final String html, final String url, final ConceptParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRankedConcepts", "html", params);
    }

    public Document textGetRankedConcepts(final String text) {
        return textGetRankedConcepts(text, new ConceptParameters());
    }

    public Document textGetRankedConcepts(final String text, final ConceptParameters params) {
        params.setText(text);
        return post("TextGetRankedConcepts", "text", params);
    }

    public Document urlGetRankedKeywords(final String url) {
        return urlGetRankedKeywords(url, new KeywordParameters());
    }

    public Document urlGetRankedKeywords(final String url, final KeywordParameters params) {
        params.setUrl(url);
        return get("URLGetRankedKeywords", "url", params);
    }

    public Document htmlGetRankedKeywords(final String html, final String url) {
        return htmlGetRankedKeywords(html, url, new KeywordParameters());
    }

    public Document htmlGetRankedKeywords(final String html, final String url, final KeywordParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRankedKeywords", "html", params);
    }

    public Document textGetRankedKeywords(final String text) {
        return textGetRankedKeywords(text, new KeywordParameters());
    }

    public Document textGetRankedKeywords(final String text, final KeywordParameters params) {
        params.setText(text);
        return post("TextGetRankedKeywords", "text", params);
    }

    public Document urlGetLanguage(final String url) {
        return urlGetLanguage(url, new LanguageParameters());
    }

    public Document urlGetLanguage(final String url, final LanguageParameters params) {
        params.setUrl(url);
        return get("URLGetLanguage", "url", params);
    }

    public Document htmlGetLanguage(final String html, final String url) {
        return htmlGetLanguage(html, url, new LanguageParameters());
    }

    public Document htmlGetLanguage(final String html, final String url, final LanguageParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetLanguage", "html", params);
    }

    public Document textGetLanguage(final String text) {
        return textGetLanguage(text, new LanguageParameters());
    }

    public Document textGetLanguage(final String text, final LanguageParameters params) {
        params.setText(text);
        return post("TextGetLanguage", "text", params);
    }

    public Document urlGetCategory(final String url) {
        return urlGetCategory(url, new CategoryParameters());
    }

    public Document urlGetCategory(final String url, final CategoryParameters params) {
        params.setUrl(url);
        return get("URLGetCategory", "url", params);
    }

    public Document htmlGetCategory(final String html, final String url) {
        return htmlGetCategory(html, url, new CategoryParameters());
    }

    public Document htmlGetCategory(final String html, final String url, final CategoryParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetCategory", "html", params);
    }

    public Document textGetCategory(final String text) {
        return textGetCategory(text, new TextParameters());
    }

    public Document textGetCategory(final String text, final TextParameters params) {
        params.setText(text);
        return post("TextGetCategory", "text", params);
    }

    public Document urlGetText(final String url) {
        return urlGetText(url, new TextParameters());
    }

    public Document urlGetText(final String url, final TextParameters params) {
        params.setUrl(url);
        return get("URLGetText", "url", params);
    }

    public Document htmlGetText(final String html, final String url) {
        return htmlGetText(html, url, new TextParameters());
    }

    public Document htmlGetText(final String html, final String url, final TextParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetText", "html", params);
    }

    public Document urlGetRawText(final String url) {
        return urlGetRawText(url, new Parameters());
    }

    public Document urlGetRawText(final String url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetRawText", "url", params);
    }

    public Document htmlGetRawText(final String html, final String url) {
        return htmlGetRawText(html, url, new Parameters());
    }

    public Document htmlGetRawText(final String html, final String url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRawText", "html", params);
    }

    public Document urlGetTitle(final String url) {
        return urlGetTitle(url, new Parameters());
    }

    public Document urlGetTitle(final String url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetTitle", "url", params);
    }

    public Document htmlGetTitle(final String html, final String url) {
        return htmlGetTitle(html, url, new Parameters());
    }

    public Document htmlGetTitle(final String html, final String url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetTitle", "html", params);
    }

    public Document urlGetFeedLinks(final String url) {
        return urlGetFeedLinks(url, new Parameters());
    }

    public Document urlGetFeedLinks(final String url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetFeedLinks", "url", params);
    }

    public Document htmlGetFeedLinks(final String html, final String url) {
        return htmlGetFeedLinks(html, url, new Parameters());
    }

    public Document htmlGetFeedLinks(final String html, final String url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetFeedLinks", "html", params);
    }

    public Document urlGetMicroformats(final String url) {
        return urlGetMicroformats(url, new Parameters());
    }

    public Document urlGetMicroformats(final String url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetMicroformatData", "url", params);
    }

    public Document htmlGetMicroformats(final String html, final String url) {
        return htmlGetMicroformats(html, url, new Parameters());
    }

    public Document htmlGetMicroformats(final String html, final String url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetMicroformatData", "html", params);
    }

    public Document urlGetConstraintQuery(final String url, final String query) {
        return urlGetConstraintQuery(url, query, new ConstraintQueryParameters());
    }

    public Document urlGetConstraintQuery(final String url, final String query, final ConstraintQueryParameters params) {
        if(trimToEmpty(query).length() < 2) {
            throw new AlchemyApiException("Constraint query must be at least 2 characters long");
        }
        params.setUrl(url);
        params.setCQuery(query);
        return post("URLGetConstraintQuery", "url", params);
    }


    public Document htmlGetConstraintQuery(final String html, final String url, final String query) {
        return htmlGetConstraintQuery(html, url, query, new ConstraintQueryParameters());
    }

    public Document htmlGetConstraintQuery(final String html, final String url, final String query, final ConstraintQueryParameters params) {
        if(trimToEmpty(query).length() < 2) {
            throw new AlchemyApiException("Constraint query must be at least 2 characters long");
        }
        params.setUrl(url);
        params.setHtml(html);
        params.setCQuery(query);
        return post("HTMLGetConstraintQuery", "html", params);
    }

    public Document urlGetTextSentiment(final String url) {
        return urlGetTextSentiment(url, new Parameters());
    }

    public Document urlGetTextSentiment(final String url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetTextSentiment", "url", params);
    }

    public Document htmlGetTextSentiment(final String html, final String url) {
        return htmlGetTextSentiment(html, url, new Parameters());
    }

    public Document htmlGetTextSentiment(final String html, final String url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetTextSentiment", "html", params);
    }

    public Document textGetTextSentiment(final String text) {
        return textGetTextSentiment(text, new Parameters());
    }

    public Document textGetTextSentiment(final String text, final Parameters params) {
        params.setText(text);
        return post("TextGetTextSentiment", "text", params);
    }

    public Document urlGetTargetedSentiment(final String url, final String target) {
        return urlGetTargetedSentiment(url, target, new TargetedSentimentParameters());
    }

    public Document urlGetTargetedSentiment(final String url, final String target, final TargetedSentimentParameters params) {
        params.setUrl(url);
        params.setTarget(target);
        return get("URLGetTargetedSentiment", "url", params);
    }

    public Document htmlGetTargetedSentiment(final String html, final String url, final String target) {
        return htmlGetTargetedSentiment(html, url, target, new TargetedSentimentParameters());
    }

    public Document htmlGetTargetedSentiment(final String html, final String url, final String target, final TargetedSentimentParameters params) {
        params.setHtml(html);
        params.setUrl(url);
        params.setTarget(target);
        return post("HTMLGetTargetedSentiment", "html", params);
    }

    public Document textGetTargetedSentiment(final String text, final String target) {
        return textGetTargetedSentiment(text, target, new TargetedSentimentParameters());
    }

    public Document textGetTargetedSentiment(final String text, final String target, final TargetedSentimentParameters params) {
        params.setText(text);
        params.setTarget(target);
        return post("TextGetTargetedSentiment", "text", params);
    }

    public Document urlGetRelations(final String url) {
        return urlGetRelations(url, new RelationParameters());
    }

    public Document urlGetRelations(final String url, final RelationParameters params) {
        params.setUrl(url);
        return get("URLGetRelations", "url", params);
    }

    public Document htmlGetRelations(final String html, final String url) {
        return htmlGetRelations(html, url, new RelationParameters());
    }

    public Document htmlGetRelations(final String html, final String url, final RelationParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRelations", "html", params);
    }

    public Document textGetRelations(final String text) {
        return textGetRelations(text, new RelationParameters());
    }

    public Document textGetRelations(final String text, final RelationParameters params) {
        params.setText(text);
        return post("TextGetRelations", "text", params);
    }

    public Document urlGetCombined(final String url) {
        final CombinedParameters params = new CombinedParameters();
        params.setExtractAll();
        return urlGetCombined(url, params);
    }

    public Document urlGetCombined(final String url, final CombinedParameters params) {
        params.setUrl(url);
        return get("URLGetCombinedData", "url", params);
    }

    public Document textGetCombined(final String text) {
        final CombinedParameters params = new CombinedParameters();
        params.setExtractAll();
        return textGetCombined(text, params);
    }

    public Document textGetCombined(final String text, final CombinedParameters params) {
        params.setText(text);
        return post("TextGetCombinedData", "text", params);
    }

    public Document urlGetTaxonomy(final String url) {
        return urlGetTaxonomy(url, new TaxonomyParameters());
    }

    public Document urlGetTaxonomy(final String url, final TaxonomyParameters params) {
        params.setUrl(url);
        return get("URLGetRankedTaxonomy", "url", params);
    }

    public Document htmlGetTaxonomy(final String html, final String url) {
        return htmlGetTaxonomy(html, url, new TaxonomyParameters());
    }

    public Document htmlGetTaxonomy(final String html, final String url, final TaxonomyParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRankedTaxonomy", "html", params);
    }

    public Document textGetTaxonomy(final String text) {
        return textGetTaxonomy(text, new TaxonomyParameters());
    }

    public Document textGetTaxonomy(final String text, final TaxonomyParameters params) {
        params.setText(text);
        return post("TextGetRankedTaxonomy", "text", params);
    }

    public Document urlGetImage(final String url) {
        return urlGetImage(url, new ImageParameters());
    }

    public Document urlGetImage(final String url, final ImageParameters params) {
        params.setUrl(url);
        return get("URLGetImage", "url", params);
    }

    public Document urlGetRankedImageKeywords(final String url) {
        return urlGetRankedImageKeywords(url, new ImageParameters());
    }

    public Document urlGetRankedImageKeywords(final String url, final ImageParameters params) {
        params.setUrl(url);
        return get("URLGetRankedImageKeywords", "url", params);
    }

    public Document imageGetRankedImageKeywords(final ImageParameters params) {
        try {
            final String urlQuery = "?apikey=" + this.configuration.getApiKey() + params.getUrlQuery();
            final URL url = new URL(buildBaseApiUrl() + "image/ImageGetRankedImageKeywords" + urlQuery);

            final HttpURLConnection handle = (HttpURLConnection) url.openConnection();
            handle.setDoOutput(true);

            final byte[] image = params.getImage();
            handle.addRequestProperty("Content-Length", Integer.toString(image.length));

            final DataOutputStream outputStream = new DataOutputStream(handle.getOutputStream());
            outputStream.write(image);
            outputStream.close();

            return doRequest(handle, params);

        } catch(IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    private Document get(final String callName, final String callPrefix, final Parameters parameters) {
        try {
            final String urlQuery = "?apikey=" + configuration.getApiKey() + parameters.getUrlQuery();
            final URL url = new URL(buildBaseApiUrl() + callPrefix + "/" + callName + urlQuery);

            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);

            return doRequest(httpURLConnection, parameters);

        } catch(IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    private Document post(final String callName, final String callPrefix, final Parameters parameters) {
        try {
            final URL url = new URL(buildBaseApiUrl() + callPrefix + "/" + callName);
            final String data = "apikey=" + configuration.getApiKey() + parameters.getUrlQuery();

            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Length", Integer.toString(data.length()));

            final DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            outputStream.write(data.getBytes(Charset.forName("UTF-8")));
            outputStream.close();

            return doRequest(httpURLConnection, parameters);

        } catch(IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    // TODO support json, by default
    // TODO return pojo with parsed field, but allow a "raw" xml/json getter to protect against api updates
    private Document doRequest(final HttpURLConnection httpURLConnection, final Parameters parameters) {
        try {
            final String response = IOUtils.toString(httpURLConnection.getInputStream());
            httpURLConnection.disconnect();

            switch (parameters.getOutputMode()) {
                case Parameters.OUTPUT_XML:
                    return parseXml(response, parameters);

                case Parameters.OUTPUT_RDF:
                    return praseRdf(response, parameters);

                case Parameters.OUTPUT_JSON:
                    // return parseJson(response, parameters);
                    throw new AlchemyApiException("Json responses are not currently supported");

                default:
                    throw new AlchemyApiException("Unknown output mode, must be one of [xml,rdf,json]");
            }
        } catch (IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    private JSONObject parseJson(final String response, final Parameters parameters) {
        final JSONObject json = new JSONObject(response);
        if(json.has("results")) {
            final JSONObject results = json.getJSONObject("results");
            if(!StringUtils.equals(results.optString("status"), "OK")) {
                if(results.has("statusInfo")) {
                    throw new AlchemyApiException("Error making API call: " + results.optString("statusInfo"));
                }
                throw new AlchemyApiException("Error making API call: " + results.optString("status"));
            }
        }
        return json;
    }

    private Document parseXml(final String response, final Parameters parameters) {
        final Document document = Jsoup.parse(response, parameters.getEncoding(), Parser.xmlParser());

        final Element status = document.select("results > status").first();
        if (status == null || !status.text().equals("OK")) {
            final Element statusInfo = document.select("results > statusInfo").first();
            if (statusInfo != null) {
                throw new AlchemyApiException("Error making API call: " + statusInfo);
            }
            throw new AlchemyApiException("Error making API call: " + status);
        }
        return document;
    }

    private Document praseRdf(final String response, final Parameters parameters) {
        final Document document = Jsoup.parse(response, parameters.getEncoding(), Parser.xmlParser());
        LOGGER.info("RAW: " + response);
        final Element status = document.select("rdf|RDF > rdf|Description > aapi|ResultStatus").first();
        if (status == null || !status.text().equals("OK")) {
            throw new AlchemyApiException("Error making API call: " + status);
        }
        return document;
    }

    private String buildBaseApiUrl() {
        return API_URL.replace("{SUB_DOMAIN}", configuration.getApiSubDomain());
    }

}
