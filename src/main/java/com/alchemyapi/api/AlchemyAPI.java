package com.alchemyapi.api;

import com.alchemyapi.api.exceptions.AlchemyApiException;
import com.alchemyapi.api.parameters.CategoryParameters;
import com.alchemyapi.api.parameters.CombinedParameters;
import com.alchemyapi.api.parameters.ConceptParameters;
import com.alchemyapi.api.parameters.ConstraintQueryParameters;
import com.alchemyapi.api.parameters.ImageParameters;
import com.alchemyapi.api.parameters.KeywordParameters;
import com.alchemyapi.api.parameters.LanguageParameters;
import com.alchemyapi.api.parameters.NamedEntityParameters;
import com.alchemyapi.api.parameters.Parameters;
import com.alchemyapi.api.parameters.RelationParameters;
import com.alchemyapi.api.parameters.TargetedSentimentParameters;
import com.alchemyapi.api.parameters.TaxonomyParameters;
import com.alchemyapi.api.parameters.TextParameters;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.length;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

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

    public Document urlGetAuthor(final URL url) {
        return urlGetAuthor(url, new Parameters());
    }

    public Document urlGetAuthor(final URL url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetAuthor", "url", params);
    }

    public Document htmlGetAuthor(final String html, final URL url) {
        return htmlGetAuthor(html, url, new Parameters());
    }

    public Document htmlGetAuthor(final String html, final URL url, final Parameters params) {
        params.setHtml(html);
        params.setUrl(url);
        return post("HTMLGetAuthor", "html", params);
    }

    public Document urlGetRankedNamedEntities(final URL url) {
        return urlGetRankedNamedEntities(url, new NamedEntityParameters());
    }

    public Document urlGetRankedNamedEntities(final URL url, final NamedEntityParameters params) {
        params.setUrl(url);
        return get("URLGetRankedNamedEntities", "url", params);
    }

    public Document htmlGetRankedNamedEntities(final String html, final URL url) {
        return htmlGetRankedNamedEntities(html, url, new NamedEntityParameters());
    }

    public Document htmlGetRankedNamedEntities(final String html, final URL url, final NamedEntityParameters params) {
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

    public Document urlGetRankedConcepts(final URL url) {
        return urlGetRankedConcepts(url, new ConceptParameters());
    }

    public Document urlGetRankedConcepts(final URL url, final ConceptParameters params) {
        params.setUrl(url);
        return get("URLGetRankedConcepts", "url", params);
    }

    public Document htmlGetRankedConcepts(final String html, final URL url) {
        return htmlGetRankedConcepts(html, url, new ConceptParameters());
    }

    public Document htmlGetRankedConcepts(final String html, final URL url, final ConceptParameters params) {
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

    public Document urlGetRankedKeywords(final URL url) {
        return urlGetRankedKeywords(url, new KeywordParameters());
    }

    public Document urlGetRankedKeywords(final URL url, final KeywordParameters params) {
        params.setUrl(url);
        return get("URLGetRankedKeywords", "url", params);
    }

    public Document htmlGetRankedKeywords(final String html, final URL url) {
        return htmlGetRankedKeywords(html, url, new KeywordParameters());
    }

    public Document htmlGetRankedKeywords(final String html, final URL url, final KeywordParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRankedKeywords", "html", params);
    }

    public Document textGetRankedKeywords(final String text) throws IOException, SAXException,
            ParserConfigurationException, XPathExpressionException {
        return textGetRankedKeywords(text, new KeywordParameters());
    }

    public Document textGetRankedKeywords(final String text, final KeywordParameters params) {
        params.setText(text);
        return post("TextGetRankedKeywords", "text", params);
    }

    public Document urlGetLanguage(final URL url) {
        return urlGetLanguage(url, new LanguageParameters());
    }

    public Document urlGetLanguage(final URL url, final LanguageParameters params) {
        params.setUrl(url);
        return get("URLGetLanguage", "url", params);
    }

    public Document htmlGetLanguage(final String html, final URL url) {
        return htmlGetLanguage(html, url, new LanguageParameters());
    }

    public Document htmlGetLanguage(final String html, final URL url, final LanguageParameters params) {
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

    public Document urlGetCategory(final URL url) {
        return urlGetCategory(url, new CategoryParameters());
    }

    public Document urlGetCategory(final URL url, final CategoryParameters params) {
        params.setUrl(url);
        return get("URLGetCategory", "url", params);
    }

    public Document htmlGetCategory(final String html, URL url) {
        return htmlGetCategory(html, url, new CategoryParameters());
    }

    public Document htmlGetCategory(final String html, final URL url, final CategoryParameters params) {
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

    public Document urlGetText(final URL url) {
        return urlGetText(url, new TextParameters());
    }

    public Document urlGetText(final URL url, final TextParameters params) {
        params.setUrl(url);
        return get("URLGetText", "url", params);
    }

    public Document htmlGetText(final String html, final URL url) {
        return htmlGetText(html, url, new TextParameters());
    }

    public Document htmlGetText(final String html, final URL url, final TextParameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetText", "html", params);
    }

    public Document urlGetRawText(final URL url) {
        return urlGetRawText(url, new Parameters());
    }

    public Document urlGetRawText(final URL url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetRawText", "url", params);
    }

    public Document htmlGetRawText(final String html, final URL url) {
        return htmlGetRawText(html, url, new Parameters());
    }

    public Document htmlGetRawText(final String html, final URL url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetRawText", "html", params);
    }

    public Document urlGetTitle(final URL url) {
        return urlGetTitle(url, new Parameters());
    }

    public Document urlGetTitle(final URL url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetTitle", "url", params);
    }

    public Document htmlGetTitle(final String html, final URL url) {
        return htmlGetTitle(html, url, new Parameters());
    }

    public Document htmlGetTitle(final String html, final URL url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetTitle", "html", params);
    }

    public Document urlGetFeedLinks(final URL url) {
        return urlGetFeedLinks(url, new Parameters());
    }

    public Document urlGetFeedLinks(final URL url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetFeedLinks", "url", params);
    }

    public Document htmlGetFeedLinks(final String html, final URL url) {
        return htmlGetFeedLinks(html, url, new Parameters());
    }

    public Document htmlGetFeedLinks(final String html, final URL url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetFeedLinks", "html", params);
    }

    public Document urlGetMicroformats(final URL url) {
        return urlGetMicroformats(url, new Parameters());
    }

    public Document urlGetMicroformats(final URL url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetMicroformatData", "url", params);
    }

    public Document htmlGetMicroformats(final String html, final URL url) {
        return htmlGetMicroformats(html, url, new Parameters());
    }

    public Document htmlGetMicroformats(final String html, final URL url, final Parameters params) {
        params.setUrl(url);
        params.setHtml(html);
        return post("HTMLGetMicroformatData", "html", params);
    }

    public Document urlGetConstraintQuery(final URL url, final String query) {
        return urlGetConstraintQuery(url, query, new ConstraintQueryParameters());
    }

    public Document urlGetConstraintQuery(final URL url, final String query, final ConstraintQueryParameters params) {
        if(trimToEmpty(query).length() < 2) {
            throw new AlchemyApiException("Constraint query must be at least 2 characters long");
        }
        params.setUrl(url);
        params.setCQuery(query);
        return post("URLGetConstraintQuery", "url", params);
    }


    public Document htmlGetConstraintQuery(final String html, final URL url, final String query) {
        return htmlGetConstraintQuery(html, url, query, new ConstraintQueryParameters());
    }

    public Document htmlGetConstraintQuery(final String html, URL url, final String query, final ConstraintQueryParameters params) {
        if(trimToEmpty(query).length() < 2) {
            throw new AlchemyApiException("Constraint query must be at least 2 characters long");
        }
        params.setUrl(url);
        params.setHtml(html);
        params.setCQuery(query);
        return post("HTMLGetConstraintQuery", "html", params);
    }

    public Document urlGetTextSentiment(final URL url) {
        return urlGetTextSentiment(url, new Parameters());
    }

    public Document urlGetTextSentiment(final URL url, final Parameters params) {
        params.setUrl(url);
        return get("URLGetTextSentiment", "url", params);
    }

    public Document htmlGetTextSentiment(final String html, final URL url) {
        return htmlGetTextSentiment(html, url, new Parameters());
    }

    public Document htmlGetTextSentiment(final String html, URL url, final Parameters params) {
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

    public Document urlGetTargetedSentiment(final URL url, final String target) {
        return urlGetTargetedSentiment(url, target, new TargetedSentimentParameters());
    }

    public Document urlGetTargetedSentiment(final URL url, final String target, final TargetedSentimentParameters params) {
        params.setUrl(url);
        params.setTarget(target);
        return get("URLGetTargetedSentiment", "url", params);
    }

    public Document htmlGetTargetedSentiment(final String html, final URL url, final String target) {
        return htmlGetTargetedSentiment(html, url, target, new TargetedSentimentParameters());
    }

    public Document htmlGetTargetedSentiment(final String html, final URL url, final String target, final TargetedSentimentParameters params) {
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

    public Document urlGetRelations(final URL url) {
        return urlGetRelations(url, new RelationParameters());
    }

    public Document urlGetRelations(final URL url, final RelationParameters params) {
        params.setUrl(url);
        return get("URLGetRelations", "url", params);
    }

    public Document HTMLGetRelations(final String html, final URL url) {
        return HTMLGetRelations(html, url, new RelationParameters());
    }

    public Document HTMLGetRelations(final String html, final URL url, final RelationParameters params) {
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

    public Document urlGetCombined(final URL url) {
        final CombinedParameters params = new CombinedParameters();
        params.setExtractAll();
        return urlGetCombined(url, params);
    }

    public Document urlGetCombined(final URL url, final CombinedParameters params) {
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

    public Document urlGetTaxonomy(final URL url) {
        return urlGetTaxonomy(url, new TaxonomyParameters());
    }

    public Document urlGetTaxonomy(final URL url, final TaxonomyParameters params) {
        params.setUrl(url);
        return get("URLGetRankedTaxonomy", "url", params);
    }

    public Document htmlGetTaxonomy(final String html, final URL url) {
        return htmlGetTaxonomy(html, url, new TaxonomyParameters());
    }

    public Document htmlGetTaxonomy(final String html, final URL url, final TaxonomyParameters params) {
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

    public Document urlGetImage(final URL url) {
        return urlGetImage(url, new ImageParameters());
    }

    public Document urlGetImage(final URL url, final ImageParameters params) {
        params.setUrl(url);
        return get("URLGetImage", "url", params);
    }

    public Document urlGetRankedImageKeywords(final URL url) {
        return urlGetRankedImageKeywords(url, new ImageParameters());
    }

    public Document urlGetRankedImageKeywords(final URL url, final ImageParameters params) {
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

            return doRequest(handle, params.getOutputMode());

        } catch(IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    private Document get(final String callName, final String callPrefix, final Parameters params) {
        try {
            final String urlQuery = "?apikey=" + configuration.getApiKey() + params.getUrlQuery();
            final URL url = new URL(buildBaseApiUrl() + callPrefix + "/" + callName + urlQuery);

            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);

            return doRequest(httpURLConnection, params.getOutputMode());

        } catch(IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    private Document post(final String callName, final String callPrefix, final Parameters params) {
        try {
            final URL url = new URL(buildBaseApiUrl() + callPrefix + "/" + callName);
            final String data = "apikey=" + configuration.getApiKey() + params.getUrlQuery();

            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Length", Integer.toString(data.length()));

            final DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            outputStream.write(data.getBytes(Charset.forName("UTF-8")));
            outputStream.close();

            return doRequest(httpURLConnection, params.getOutputMode());

        } catch(IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    // TODO add json handling
    // TODO return pojo with parsed field, but allow a "raw" xml/json getter to protect against api updates
    private Document doRequest(final HttpURLConnection httpURLConnection, final String outputMode) {
        try {
            final DataInputStream inputStream = new DataInputStream(httpURLConnection.getInputStream());
            final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);

            inputStream.close();
            httpURLConnection.disconnect();

            switch (outputMode) {
                case Parameters.OUTPUT_XML:
                    return parseXml(document);

                case Parameters.OUTPUT_RDF:
                    return praseRdf(document);

                case Parameters.OUTPUT_JSON:
                    throw new AlchemyApiException("Json Response not supported yet");
            }
            return document;

        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new AlchemyApiException(e);
        }
    }

    private Document parseXml(final Document document) {
        final XPathFactory factory = XPathFactory.newInstance();
        final String status = getNodeValue(factory, document, "/results/status/text()");
        if (isBlank(status) || !status.equals("OK")) {
            final String statusInfo = getNodeValue(factory, document, "/results/statusInfo/text()");
            if (isNotBlank(statusInfo)) {
                throw new AlchemyApiException("Error making API call: " + statusInfo);
            }
            throw new AlchemyApiException("Error making API call: " + status);
        }
        return document;
    }

    private Document praseRdf(final Document document) {
        final XPathFactory factory = XPathFactory.newInstance();
        final String status = getNodeValue(factory, document, "//RDF/Description/ResultStatus/text()");
        if (isBlank(status) || !status.equals("OK")) {
            final String statusInfo = getNodeValue(factory, document, "//RDF/Description/ResultStatus/text()");
            if (isNotBlank(statusInfo)) {
                throw new AlchemyApiException("Error making API call: " + statusInfo);
            }
            throw new AlchemyApiException("Error making API call: " + status);
        }
        return document;
    }

    private String getNodeValue(XPathFactory factory, Document doc, String xpathStr) {
        try {
            final XPath xpath = factory.newXPath();
            final XPathExpression expr = xpath.compile(xpathStr);
            final Object result = expr.evaluate(doc, XPathConstants.NODESET);
            final NodeList results = (NodeList) result;

            if(results.getLength() == 0 || results.item(0) == null) { return null; }
            return results.item(0).getNodeValue();

        } catch (XPathExpressionException e) {
            throw new AlchemyApiException(e);
        }
    }

    private String buildBaseApiUrl() {
        return API_URL.replace("{SUB_DOMAIN}", configuration.getApiSubDomain());
    }

}
