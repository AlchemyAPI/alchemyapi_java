package com.alchemyapi.api;

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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlchemyAPI {

    private String _apiKey;
    private String _requestUri = "http://access.alchemyapi.com/calls/";

    private AlchemyAPI() {
    }


    static public AlchemyAPI GetInstanceFromFile(String keyFilename)
        throws FileNotFoundException, IOException
    {
        AlchemyAPI api = new AlchemyAPI();
        api.LoadAPIKey(keyFilename);

        return api;
    }

    static public AlchemyAPI GetInstanceFromString(String apiKey)
    {
        AlchemyAPI api = new AlchemyAPI();
        api.SetAPIKey(apiKey);

        return api;
    }

    public void LoadAPIKey(String filename) throws IOException, FileNotFoundException
    {
        if (null == filename || 0 == filename.length())
            throw new IllegalArgumentException("Empty API key file specified.");

        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);

        BufferedReader breader = new BufferedReader(new InputStreamReader(fis));

        _apiKey = breader.readLine().replaceAll("\\n", "").replaceAll("\\r", "");

        fis.close();
        breader.close();

        if (null == _apiKey || _apiKey.length() < 5)
            throw new IllegalArgumentException("Too short API key.");
    }

    public void SetAPIKey(String apiKey) {
        _apiKey = apiKey;

        if (null == _apiKey || _apiKey.length() < 5)
            throw new IllegalArgumentException("Too short API key.");
    }

    public void SetAPIHost(String apiHost) {
        if (null == apiHost || apiHost.length() < 2)
            throw new IllegalArgumentException("Too short API host.");

        _requestUri = "http://" + apiHost + ".alchemyapi.com/calls/";
    }

    public Document URLGetAuthor(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetAuthor(url, new Parameters());
    }

    public Document URLGetAuthor(String url, Parameters params)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        CheckURL(url);

        params.setUrl(url);

        return GET("URLGetAuthor", "url", params);
    }

    public Document HTMLGetAuthor(String html, String url)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return HTMLGetAuthor(html, url, new Parameters());
    }

    public Document HTMLGetAuthor(String html, String url,
			          Parameters params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);

        params.setHtml(html);
        params.setUrl(url);

        return POST("HTMLGetAuthor", "html", params);
    }

    public Document URLGetRankedNamedEntities(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedNamedEntities(url, new NamedEntityParameters());
    }
    
    public Document URLGetRankedNamedEntities(String url, NamedEntityParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    	
    	params.setUrl(url);

    	return GET("URLGetRankedNamedEntities", "url", params);
    }

    public Document HTMLGetRankedNamedEntities(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRankedNamedEntities(html, url, new NamedEntityParameters());
    }
    

    public Document HTMLGetRankedNamedEntities(String html, String url, NamedEntityParameters params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);
        
        params.setUrl(url);
        params.setHtml(html);

        return POST("HTMLGetRankedNamedEntities", "html", params);
    }

    public Document TextGetRankedNamedEntities(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetRankedNamedEntities(text, new NamedEntityParameters());
    }
    
    public Document TextGetRankedNamedEntities(String text, NamedEntityParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckText(text);
    
    	params.setText(text);

    	return POST("TextGetRankedNamedEntities", "text", params);
    }
    
    

    public Document URLGetRankedConcepts(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedConcepts(url, new ConceptParameters());
    }
    
    public Document URLGetRankedConcepts(String url, ConceptParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    
    	params.setUrl(url);

    	return GET("URLGetRankedConcepts", "url", params);
    }    
    

    public Document HTMLGetRankedConcepts(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRankedConcepts(html, url, new ConceptParameters());
    }
    
    public Document HTMLGetRankedConcepts(String html, String url, ConceptParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);

	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRankedConcepts", "html", params);
	}

    public Document TextGetRankedConcepts(String text) throws IOException, SAXException,
            ParserConfigurationException, XPathExpressionException {
        return TextGetRankedConcepts(text, new ConceptParameters());
    }
    
    public Document TextGetRankedConcepts(String text, ConceptParameters params) throws IOException, SAXException,
    ParserConfigurationException, XPathExpressionException 
    {
		CheckText(text);
		
		params.setText(text);
		
		return POST("TextGetRankedConcepts", "text", params);
	}
    
    

    public Document URLGetRankedKeywords(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedKeywords(url, new KeywordParameters());
    }
    
    public Document URLGetRankedKeywords(String url, KeywordParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    
    	params.setUrl(url);

    	return GET("URLGetRankedKeywords", "url", params);
    }    
    

    public Document HTMLGetRankedKeywords(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRankedKeywords(html, url, new KeywordParameters());
    }
    
    public Document HTMLGetRankedKeywords(String html, String url, KeywordParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);

	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRankedKeywords", "html", params);
	}

    public Document TextGetRankedKeywords(String text) throws IOException, SAXException,
            ParserConfigurationException, XPathExpressionException {
        return TextGetRankedKeywords(text, new KeywordParameters());
    }
    
    public Document TextGetRankedKeywords(String text, KeywordParameters params) throws IOException, SAXException,
    ParserConfigurationException, XPathExpressionException 
    {
		CheckText(text);
		
		params.setText(text);
		
		return POST("TextGetRankedKeywords", "text", params);
	}

    public Document URLGetLanguage(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetLanguage(url, new LanguageParameters());
    }
    
    public Document URLGetLanguage(String url, LanguageParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetLanguage", "url", params);
	}

    public Document HTMLGetLanguage(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetLanguage(html, url, new LanguageParameters());
    }
    
    public Document HTMLGetLanguage(String html, String url, LanguageParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetLanguage", "html", params);
	}

    public Document TextGetLanguage(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetLanguage(text, new LanguageParameters());
    }
    
    public Document TextGetLanguage(String text, LanguageParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);

	    params.setText(text);
	
	    return POST("TextGetLanguage", "text", params);
	}

    public Document URLGetCategory(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetCategory(url, new CategoryParameters());
    }
    
    public Document URLGetCategory(String url, CategoryParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetCategory", "url", params);
	}

    public Document HTMLGetCategory(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetCategory(html, url, new CategoryParameters());
    }
    
    public Document HTMLGetCategory(String html, String url, CategoryParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetCategory", "html", params);
	}

    public Document TextGetCategory(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetCategory(text, new TextParameters());
    }
    
    public Document TextGetCategory(String text, TextParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetCategory", "text", params);
	}

    public Document URLGetText(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetText(url, new TextParameters());
    }
    
    public Document URLGetText(String url, TextParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetText", "url", params);
	}

    public Document HTMLGetText(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetText(html, url, new TextParameters());
    }
    
    public Document HTMLGetText(String html, String url, TextParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetText", "html", params);
	}

    public Document URLGetRawText(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRawText(url, new Parameters());
    }
    
    public Document URLGetRawText(String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetRawText", "url", params);
	}

    public Document HTMLGetRawText(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRawText(html, url, new Parameters());
    }
    
    public Document HTMLGetRawText(String html, String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);

	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRawText", "html", params);
	}

    public Document URLGetTitle(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTitle(url, new Parameters());
    }
    
    public Document URLGetTitle(String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetTitle", "url", params);
	}

    public Document HTMLGetTitle(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTitle(html, url, new Parameters());
    }
    
    public Document HTMLGetTitle(String html, String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetTitle", "html", params);
	}

    public Document URLGetFeedLinks(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetFeedLinks(url, new Parameters());
    }
    
    public Document URLGetFeedLinks(String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetFeedLinks", "url", params);
	}

    public Document HTMLGetFeedLinks(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetFeedLinks(html, url, new Parameters());
    }
    
    public Document HTMLGetFeedLinks(String html, String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetFeedLinks", "html", params);
	}

    public Document URLGetMicroformats(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetMicroformats(url, new Parameters());
    }
    
    public Document URLGetMicroformats(String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetMicroformatData", "url", params);
	}

    public Document HTMLGetMicroformats(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetMicroformats(html, url, new Parameters());
    }
    
    public Document HTMLGetMicroformats(String html, String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetMicroformatData", "html", params);
	}

    public Document URLGetConstraintQuery(String url, String query)
        throws IOException, XPathExpressionException,
               SAXException, ParserConfigurationException
    {
        return URLGetConstraintQuery(url, query, new ConstraintQueryParameters());
    }
    
    public Document URLGetConstraintQuery(String url, String query, ConstraintQueryParameters params)
    throws IOException, XPathExpressionException,
           SAXException, ParserConfigurationException
	{
	    CheckURL(url);
	    if (null == query || query.length() < 2)
	        throw new IllegalArgumentException("Invalid constraint query specified");
	    
	    params.setUrl(url);
	    params.setCQuery(query);
	
	    return POST("URLGetConstraintQuery", "url", params);
	}


    public Document HTMLGetConstraintQuery(String html, String url, String query)
        throws IOException, XPathExpressionException,
               SAXException, ParserConfigurationException
    {
        return HTMLGetConstraintQuery(html, url, query, new ConstraintQueryParameters());
    }
    
    public Document HTMLGetConstraintQuery(String html, String url, String query, ConstraintQueryParameters params)
    throws IOException, XPathExpressionException,
           SAXException, ParserConfigurationException
	{
	    CheckHTML(html, url);
	    if (null == query || query.length() < 2)
	        throw new IllegalArgumentException("Invalid constraint query specified");

	    params.setUrl(url);
	    params.setHtml(html);
	    params.setCQuery(query);
	
	    return POST("HTMLGetConstraintQuery", "html", params);
	}
	
	public Document URLGetTextSentiment(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTextSentiment(url, new Parameters());
    }
    
    public Document URLGetTextSentiment(String url, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    
    	params.setUrl(url);

    	return GET("URLGetTextSentiment", "url", params);
    }    
    

    public Document HTMLGetTextSentiment(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTextSentiment(html, url, new Parameters());
    }
    
    public Document HTMLGetTextSentiment(String html, String url, Parameters params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);

        params.setUrl(url);
        params.setHtml(html);
	
        return POST("HTMLGetTextSentiment", "html", params);
    }

    public Document TextGetTextSentiment(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetTextSentiment(text, new Parameters());
    }
    
    public Document TextGetTextSentiment(String text, Parameters params)
        throws IOException, SAXException,
        ParserConfigurationException, XPathExpressionException 
    {
        CheckText(text);
		
        params.setText(text);
		
        return POST("TextGetTextSentiment", "text", params);
    }
	
	//------------------
	
    public Document URLGetTargetedSentiment(String url, String target)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return URLGetTargetedSentiment(url, target,
                                       new TargetedSentimentParameters());
    }

    public Document URLGetTargetedSentiment(String url, String target,
                                            TargetedSentimentParameters params)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        CheckURL(url);
        CheckText(target);

        params.setUrl(url);
        params.setTarget(target);

        return GET("URLGetTargetedSentiment", "url", params);
    }
	
    public Document HTMLGetTargetedSentiment(String html, String url, String target)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return HTMLGetTargetedSentiment(html, url, target,
                                        new TargetedSentimentParameters());
    }

    public Document HTMLGetTargetedSentiment(String html, String url, String target,
                                             TargetedSentimentParameters params)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        CheckHTML(html, url);
        CheckText(target);
		
        params.setHtml(html);
        params.setUrl(url);
        params.setTarget(target);

        return POST("HTMLGetTargetedSentiment", "html", params);
    }
	
    public Document TextGetTargetedSentiment(String text, String target)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return TextGetTargetedSentiment(text, target,
                                        new TargetedSentimentParameters());
    }

    public Document TextGetTargetedSentiment(String text, String target,
                                             TargetedSentimentParameters params)
        throws IOException, SAXException, ParserConfigurationException,
	       XPathExpressionException
    {
        CheckText(text);
        CheckText(target);

        params.setText(text);
        params.setTarget(target);

        return POST("TextGetTargetedSentiment", "text", params);
    }

	//------------------
	
    public Document URLGetRelations(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRelations(url, new RelationParameters());
    }
    
    public Document URLGetRelations(String url, RelationParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetRelations", "url", params);
	}

    public Document HTMLGetRelations(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRelations(html, url, new RelationParameters());
    }
    
    public Document HTMLGetRelations(String html, String url, RelationParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRelations", "html", params);
	}

    public Document TextGetRelations(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetRelations(text, new RelationParameters());
    }
    
    public Document TextGetRelations(String text, RelationParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetRelations", "text", params);
	}

	//------------------
	
    public Document URLGetCombined(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
	CombinedParameters params = new CombinedParameters();
	params.setExtractAll();
        return URLGetCombined(url, params);
    }
    
    public Document URLGetCombined(String url, CombinedParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetCombinedData", "url", params);
	}

    public Document TextGetCombined(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
	CombinedParameters params = new CombinedParameters();
	params.setExtractAll();
        return TextGetCombined(text, params);
    }
    
    public Document TextGetCombined(String text, CombinedParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetCombinedData", "text", params);
	}
    
	//------------------
	
    public Document URLGetTaxonomy(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTaxonomy(url, new TaxonomyParameters());
    }
    
    public Document URLGetTaxonomy(String url, TaxonomyParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetRankedTaxonomy", "url", params);
	}

    public Document HTMLGetTaxonomy(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTaxonomy(html, url, new TaxonomyParameters());
    }
    
    public Document HTMLGetTaxonomy(String html, String url, TaxonomyParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRankedTaxonomy", "html", params);
	}

    public Document TextGetTaxonomy(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetTaxonomy(text, new TaxonomyParameters());
    }
    
    public Document TextGetTaxonomy(String text, TaxonomyParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetRankedTaxonomy", "text", params);
	}

	//------------------
	
    public Document URLGetImage(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetImage(url, new ImageParameters());
    }
    
    public Document URLGetImage(String url, ImageParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetImage", "url", params);
	}

    public Document URLGetRankedImageKeywords(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedImageKeywords(url, new ImageParameters());
    }
    
    public Document URLGetRankedImageKeywords(String url, ImageParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
        CheckURL(url);

        params.setUrl(url);
    
        return GET("URLGetRankedImageKeywords", "url", params);
    }

    public Document ImageGetRankedImageKeywords(ImageParameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
        URL url = new URL(_requestUri + "image/ImageGetRankedImageKeywords?" + 
            "apikey=" + this._apiKey + params.getParameterString());
        System.out.println(url.toString());

        HttpURLConnection handle = (HttpURLConnection) url.openConnection();
        handle.setDoOutput(true);

        byte[] image = params.getImage();
        handle.addRequestProperty("Content-Length", Integer.toString(image.length));

        DataOutputStream ostream = new DataOutputStream(handle.getOutputStream());
        ostream.write(image);
        ostream.close();

        return doRequest(handle, params.getOutputMode());
    }

    private void CheckHTML(String html, String url) {
        if (null == html || html.length() < 5)
            throw new IllegalArgumentException("Enter a HTML document to analyze.");

        if (null == url || url.length() < 10)
            throw new IllegalArgumentException("Enter an URL to analyze.");
    }

    private void CheckText(String text) {
        if (null == text )
            throw new IllegalArgumentException("Enter some text to analyze.");
    }

    private void CheckURL(String url) {
        if (null == url || url.length() < 10)
            throw new IllegalArgumentException("Enter an URL to analyze.");
    }
    
    private Document GET(String callName, String callPrefix, Parameters params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    StringBuilder uri = new StringBuilder();
	    uri.append(_requestUri).append(callPrefix).append('/').append(callName)
	       .append('?').append("apikey=").append(this._apiKey);
	    uri.append(params.getParameterString());
	
	    URL url = new URL(uri.toString());
	    HttpURLConnection handle = (HttpURLConnection) url.openConnection();
	    handle.setDoOutput(true);
	
	    return doRequest(handle, params.getOutputMode());
	}

    private Document POST(String callName, String callPrefix, Parameters params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        URL url = new URL(_requestUri + callPrefix + "/" + callName);

        HttpURLConnection handle = (HttpURLConnection) url.openConnection();
        handle.setDoOutput(true);

        StringBuilder data = new StringBuilder();

        data.append("apikey=").append(this._apiKey);
        data.append(params.getParameterString());

        handle.addRequestProperty("Content-Length", Integer.toString(data.length()));

        DataOutputStream ostream = new DataOutputStream(handle.getOutputStream());
        ostream.write(data.toString().getBytes());
        ostream.close();

        return doRequest(handle, params.getOutputMode());
    }

    // TODO currently hard-coded to handle xml, create xml/json adapter based on request
    // TODO return pojo with parsed field, but allow a "raw" xml/json getter to protect against api updates
    private Document doRequest(HttpURLConnection handle, String outputMode)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        DataInputStream istream = new DataInputStream(handle.getInputStream());
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(istream);

        istream.close();
        handle.disconnect();

        XPathFactory factory = XPathFactory.newInstance();

        if(Parameters.OUTPUT_XML.equals(outputMode)) {
        	String statusStr = getNodeValue(factory, doc, "/results/status/text()");
        	if (null == statusStr || !statusStr.equals("OK")) {
        		String statusInfoStr = getNodeValue(factory, doc, "/results/statusInfo/text()");
        		if (null != statusInfoStr && statusInfoStr.length() > 0)
        			throw new IOException("Error making API call: " + statusInfoStr + '.');

        		throw new IOException("Error making API call: " + statusStr + '.');
        	}
        }
        else if(Parameters.OUTPUT_RDF.equals(outputMode)) {
        	String statusStr = getNodeValue(factory, doc, "//RDF/Description/ResultStatus/text()");
        	if (null == statusStr || !statusStr.equals("OK")) {
        		String statusInfoStr = getNodeValue(factory, doc, "//RDF/Description/ResultStatus/text()");
        		if (null != statusInfoStr && statusInfoStr.length() > 0)
        			throw new IOException("Error making API call: " + statusInfoStr + '.');

        		throw new IOException("Error making API call: " + statusStr + '.');
        	}
        }

        return doc;
    }

    private String getNodeValue(XPathFactory factory, Document doc, String xpathStr)
        throws XPathExpressionException
    {
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile(xpathStr);
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList results = (NodeList) result;

        if (results.getLength() > 0 && null != results.item(0))
            return results.item(0).getNodeValue();

        return null;
    }
}
