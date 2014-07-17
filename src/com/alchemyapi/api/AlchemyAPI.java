package com.alchemyapi.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

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

    public String URLGetAuthor(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetAuthor(url, new AlchemyAPI_Params());
    }

    public String URLGetAuthor(String url, AlchemyAPI_Params params)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        CheckURL(url);

        params.setUrl(url);

        return GET("URLGetAuthor", "url", params);
    }

    public String HTMLGetAuthor(String html, String url)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return HTMLGetAuthor(html, url, new AlchemyAPI_Params());
    }

    public String HTMLGetAuthor(String html, String url,
			          AlchemyAPI_Params params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);

        params.setHtml(html);
        params.setUrl(url);

        return POST("HTMLGetAuthor", "html", params);
    }

    public String URLGetRankedNamedEntities(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedNamedEntities(url, new AlchemyAPI_NamedEntityParams());
    }
    
    public String URLGetRankedNamedEntities(String url, AlchemyAPI_NamedEntityParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    	
    	params.setUrl(url);

    	return GET("URLGetRankedNamedEntities", "url", params);
    }

    public String HTMLGetRankedNamedEntities(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRankedNamedEntities(html, url, new AlchemyAPI_NamedEntityParams());
    }
    

    public String HTMLGetRankedNamedEntities(String html, String url, AlchemyAPI_NamedEntityParams params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);
        
        params.setUrl(url);
        params.setHtml(html);

        return POST("HTMLGetRankedNamedEntities", "html", params);
    }

    public String TextGetRankedNamedEntities(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetRankedNamedEntities(text, new AlchemyAPI_NamedEntityParams());
    }
    
    public String TextGetRankedNamedEntities(String text, AlchemyAPI_NamedEntityParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckText(text);
    
    	params.setText(text);

    	return POST("TextGetRankedNamedEntities", "text", params);
    }
    
    

    public String URLGetRankedConcepts(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedConcepts(url, new AlchemyAPI_ConceptParams());
    }
    
    public String URLGetRankedConcepts(String url, AlchemyAPI_ConceptParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    
    	params.setUrl(url);

    	return GET("URLGetRankedConcepts", "url", params);
    }    
    

    public String HTMLGetRankedConcepts(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRankedConcepts(html, url, new AlchemyAPI_ConceptParams());
    }
    
    public String HTMLGetRankedConcepts(String html, String url, AlchemyAPI_ConceptParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);

	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRankedConcepts", "html", params);
	}

    public String TextGetRankedConcepts(String text) throws IOException, SAXException,
            ParserConfigurationException, XPathExpressionException {
        return TextGetRankedConcepts(text, new AlchemyAPI_ConceptParams());
    }
    
    public String TextGetRankedConcepts(String text, AlchemyAPI_ConceptParams params) throws IOException, SAXException,
    ParserConfigurationException, XPathExpressionException 
    {
		CheckText(text);
		
		params.setText(text);
		
		return POST("TextGetRankedConcepts", "text", params);
	}
    
    

    public String URLGetRankedKeywords(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedKeywords(url, new AlchemyAPI_KeywordParams());
    }
    
    public String URLGetRankedKeywords(String url, AlchemyAPI_KeywordParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    
    	params.setUrl(url);

    	return GET("URLGetRankedKeywords", "url", params);
    }    
    

    public String HTMLGetRankedKeywords(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRankedKeywords(html, url, new AlchemyAPI_KeywordParams());
    }
    
    public String HTMLGetRankedKeywords(String html, String url, AlchemyAPI_KeywordParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);

	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRankedKeywords", "html", params);
	}

    public String TextGetRankedKeywords(String text) throws IOException, SAXException,
            ParserConfigurationException, XPathExpressionException {
        return TextGetRankedKeywords(text, new AlchemyAPI_KeywordParams());
    }
    
    public String TextGetRankedKeywords(String text, AlchemyAPI_KeywordParams params) throws IOException, SAXException,
    ParserConfigurationException, XPathExpressionException 
    {
		CheckText(text);
		
		params.setText(text);
		
		return POST("TextGetRankedKeywords", "text", params);
	}

    public String URLGetLanguage(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetLanguage(url, new AlchemyAPI_LanguageParams());
    }
    
    public String URLGetLanguage(String url, AlchemyAPI_LanguageParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetLanguage", "url", params);
	}

    public String HTMLGetLanguage(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetLanguage(html, url, new AlchemyAPI_LanguageParams());
    }
    
    public String HTMLGetLanguage(String html, String url, AlchemyAPI_LanguageParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetLanguage", "html", params);
	}

    public String TextGetLanguage(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetLanguage(text, new AlchemyAPI_LanguageParams());
    }
    
    public String TextGetLanguage(String text, AlchemyAPI_LanguageParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);

	    params.setText(text);
	
	    return POST("TextGetLanguage", "text", params);
	}

    public String URLGetCategory(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetCategory(url, new AlchemyAPI_CategoryParams());
    }
    
    public String URLGetCategory(String url, AlchemyAPI_CategoryParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetCategory", "url", params);
	}

    public String HTMLGetCategory(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetCategory(html, url, new AlchemyAPI_CategoryParams());
    }
    
    public String HTMLGetCategory(String html, String url, AlchemyAPI_CategoryParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetCategory", "html", params);
	}

    public String TextGetCategory(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetCategory(text, new AlchemyAPI_TextParams());
    }
    
    public String TextGetCategory(String text, AlchemyAPI_TextParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetCategory", "text", params);
	}

    public String URLGetText(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetText(url, new AlchemyAPI_TextParams());
    }
    
    public String URLGetText(String url, AlchemyAPI_TextParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetText", "url", params);
	}

    public String HTMLGetText(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetText(html, url, new AlchemyAPI_TextParams());
    }
    
    public String HTMLGetText(String html, String url, AlchemyAPI_TextParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetText", "html", params);
	}

    public String URLGetRawText(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRawText(url, new AlchemyAPI_Params());
    }
    
    public String URLGetRawText(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetRawText", "url", params);
	}

    public String HTMLGetRawText(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRawText(html, url, new AlchemyAPI_Params());
    }
    
    public String HTMLGetRawText(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);

	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRawText", "html", params);
	}

    public String URLGetTitle(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTitle(url, new AlchemyAPI_Params());
    }
    
    public String URLGetTitle(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetTitle", "url", params);
	}

    public String HTMLGetTitle(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTitle(html, url, new AlchemyAPI_Params());
    }
    
    public String HTMLGetTitle(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetTitle", "html", params);
	}

    public String URLGetFeedLinks(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetFeedLinks(url, new AlchemyAPI_Params());
    }
    
    public String URLGetFeedLinks(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetFeedLinks", "url", params);
	}

    public String HTMLGetFeedLinks(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetFeedLinks(html, url, new AlchemyAPI_Params());
    }
    
    public String HTMLGetFeedLinks(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetFeedLinks", "html", params);
	}

    public String URLGetMicroformats(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetMicroformats(url, new AlchemyAPI_Params());
    }
    
    public String URLGetMicroformats(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetMicroformatData", "url", params);
	}

    public String HTMLGetMicroformats(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetMicroformats(html, url, new AlchemyAPI_Params());
    }
    
    public String HTMLGetMicroformats(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetMicroformatData", "html", params);
	}

    public String URLGetConstraintQuery(String url, String query)
        throws IOException, XPathExpressionException,
               SAXException, ParserConfigurationException
    {
        return URLGetConstraintQuery(url, query, new AlchemyAPI_ConstraintQueryParams());
    }
    
    public String URLGetConstraintQuery(String url, String query, AlchemyAPI_ConstraintQueryParams params)
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


    public String HTMLGetConstraintQuery(String html, String url, String query)
        throws IOException, XPathExpressionException,
               SAXException, ParserConfigurationException
    {
        return HTMLGetConstraintQuery(html, url, query, new AlchemyAPI_ConstraintQueryParams());
    }
    
    public String HTMLGetConstraintQuery(String html, String url, String query, AlchemyAPI_ConstraintQueryParams params)
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
	
	public String URLGetTextSentiment(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTextSentiment(url, new AlchemyAPI_Params());
    }
    
    public String URLGetTextSentiment(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    
    	params.setUrl(url);

    	return GET("URLGetTextSentiment", "url", params);
    }    
    

    public String HTMLGetTextSentiment(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTextSentiment(html, url, new AlchemyAPI_Params());
    }
    
    public String HTMLGetTextSentiment(String html, String url, AlchemyAPI_Params params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);

        params.setUrl(url);
        params.setHtml(html);
	
        return POST("HTMLGetTextSentiment", "html", params);
    }

    public String TextGetTextSentiment(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetTextSentiment(text, new AlchemyAPI_Params());
    }
    
    public String TextGetTextSentiment(String text, AlchemyAPI_Params params)
        throws IOException, SAXException,
        ParserConfigurationException, XPathExpressionException 
    {
        CheckText(text);
		
        params.setText(text);
		
        return POST("TextGetTextSentiment", "text", params);
    }
	
	//------------------
	
    public String URLGetTargetedSentiment(String url, String target)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return URLGetTargetedSentiment(url, target,
                                       new AlchemyAPI_TargetedSentimentParams());
    }

    public String URLGetTargetedSentiment(String url, String target,
                                            AlchemyAPI_TargetedSentimentParams params)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        CheckURL(url);
        CheckText(target);

        params.setUrl(url);
        params.setTarget(target);

        return GET("URLGetTargetedSentiment", "url", params);
    }
	
    public String HTMLGetTargetedSentiment(String html, String url, String target)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return HTMLGetTargetedSentiment(html, url, target,
                                        new AlchemyAPI_TargetedSentimentParams());
    }

    public String HTMLGetTargetedSentiment(String html, String url, String target,
                                             AlchemyAPI_TargetedSentimentParams params)
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
	
    public String TextGetTargetedSentiment(String text, String target)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return TextGetTargetedSentiment(text, target,
                                        new AlchemyAPI_TargetedSentimentParams());
    }

    public String TextGetTargetedSentiment(String text, String target,
                                             AlchemyAPI_TargetedSentimentParams params)
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
	
    public String URLGetRelations(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRelations(url, new AlchemyAPI_RelationParams());
    }
    
    public String URLGetRelations(String url, AlchemyAPI_RelationParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetRelations", "url", params);
	}

    public String HTMLGetRelations(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRelations(html, url, new AlchemyAPI_RelationParams());
    }
    
    public String HTMLGetRelations(String html, String url, AlchemyAPI_RelationParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRelations", "html", params);
	}

    public String TextGetRelations(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetRelations(text, new AlchemyAPI_RelationParams());
    }
    
    public String TextGetRelations(String text, AlchemyAPI_RelationParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetRelations", "text", params);
	}

	//------------------
	
    public String URLGetCombined(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
	AlchemyAPI_CombinedParams params = new AlchemyAPI_CombinedParams();
	params.setExtractAll();
        return URLGetCombined(url, params);
    }
    
    public String URLGetCombined(String url, AlchemyAPI_CombinedParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetCombinedData", "url", params);
	}

    public String TextGetCombined(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
	AlchemyAPI_CombinedParams params = new AlchemyAPI_CombinedParams();
	params.setExtractAll();
        return TextGetCombined(text, params);
    }
    
    public String TextGetCombined(String text, AlchemyAPI_CombinedParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetCombinedData", "text", params);
	}
    
	//------------------
	
    public String URLGetTaxonomy(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTaxonomy(url, new AlchemyAPI_TaxonomyParams());
    }
    
    public String URLGetTaxonomy(String url, AlchemyAPI_TaxonomyParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetRankedTaxonomy", "url", params);
	}

    public String HTMLGetTaxonomy(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTaxonomy(html, url, new AlchemyAPI_TaxonomyParams());
    }
    
    public String HTMLGetTaxonomy(String html, String url, AlchemyAPI_TaxonomyParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRankedTaxonomy", "html", params);
	}

    public String TextGetTaxonomy(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetTaxonomy(text, new AlchemyAPI_TaxonomyParams());
    }
    
    public String TextGetTaxonomy(String text, AlchemyAPI_TaxonomyParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckText(text);
	    
	    params.setText(text);
	
	    return POST("TextGetRankedTaxonomy", "text", params);
	}

	//------------------
	
    public String URLGetImage(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetImage(url, new AlchemyAPI_ImageParams());
    }
    
    public String URLGetImage(String url, AlchemyAPI_ImageParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetImage", "url", params);
	}

    public String URLGetRankedImageKeywords(String url) throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRankedImageKeywords(url, new AlchemyAPI_ImageParams());
    }
    
    public String URLGetRankedImageKeywords(String url, AlchemyAPI_ImageParams params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
        CheckURL(url);

        params.setUrl(url);
    
        return GET("URLGetRankedImageKeywords", "url", params);
    }

    public String ImageGetRankedImageKeywords(AlchemyAPI_ImageParams params)
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
        if (null == text || text.length() < 5)
            throw new IllegalArgumentException("Enter some text to analyze.");
    }

    private void CheckURL(String url) {
        if (null == url || url.length() < 10)
            throw new IllegalArgumentException("Enter an URL to analyze.");
    }
    
    private String GET(String callName, String callPrefix, AlchemyAPI_Params params)
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

    private String POST(String callName, String callPrefix, AlchemyAPI_Params params)
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

    private String doRequest(HttpURLConnection handle, String outputMode)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        String doc = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(handle.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            doc += inputLine + "\n";
        }
        in.close();
        handle.disconnect();

        return doc;
    }

}
