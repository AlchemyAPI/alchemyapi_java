package com.alchemyapi.api;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;



	public class AlchemyAPI_TaxonomyParams extends AlchemyAPI_Params{
		public static final String CLEANED_OR_RAW = "cleaned_or_raw";
		public static final String CLEANED = "cleaned";
		public static final String RAW = "raw";
		public static final String CQUERY = "cquery";
		public static final String XPATH = "xpath";

		private Boolean disambiguate;
		private Boolean linkedData;
		private Boolean coreference;
		private Boolean quotations;
		private String sourceText;
		private Boolean showSourceText;
		private String cQuery;
		private String xPath;
		private Integer maxRetrieve;
		private String baseUrl;
		private Boolean sentiment;

		public boolean isDisambiguate() {
			return disambiguate;
		}
		public void setDisambiguate(boolean disambiguate) {
			this.disambiguate = disambiguate;
		}
		public boolean isLinkedData() {
			return linkedData;
		}
		public void setLinkedData(boolean linkedData) {
			this.linkedData = linkedData;
		}
		public boolean isCoreference() {
			return coreference;
		}
		public void setCoreference(boolean coreference) {
			this.coreference = coreference;
		}
		public boolean isQuotations() {
			return quotations;
		}
		public void setQuotations(boolean quotations) {
			this.quotations = quotations;
		}
		public String getSourceText() {
			return sourceText;
		}
		public void setSourceText(String sourceText) {
			if( !sourceText.equals(AlchemyAPI_TaxonomyParams.CLEANED) && !sourceText.equals(AlchemyAPI_TaxonomyParams.CLEANED_OR_RAW) 
					&& !sourceText.equals(AlchemyAPI_TaxonomyParams.RAW) && !sourceText.equals(AlchemyAPI_TaxonomyParams.CQUERY)
					&& !sourceText.equals(AlchemyAPI_TaxonomyParams.XPATH))
			{
				throw new RuntimeException("Invalid setting " + sourceText + " for parameter sourceText");
			}
			this.sourceText = sourceText;
		}
		public boolean isShowSourceText() {
			return showSourceText;
		}
		public void setShowSourceText(boolean showSourceText) {
			this.showSourceText = showSourceText;
		}
		public String getCQuery() {
			return cQuery;
		}
		public void setCQuery(String cQuery) {
			this.cQuery = cQuery;
		}
		public String getXPath() {
			return xPath;
		}
		public void setXPath(String xPath) {
			this.xPath = xPath;
		}
		public int getMaxRetrieve() {
			return maxRetrieve;
		}
		public void setMaxRetrieve(int maxRetrieve) {
			this.maxRetrieve = maxRetrieve;
		}
		public String getBaseUrl() {
			return baseUrl;
		}
		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}
		public boolean isSentiment() {
			return sentiment;
		}
		public void setSentiment(boolean sentiment) {
			this.sentiment = sentiment;
		}

		public String getParameterString(){
			String retString = super.getParameterString();
			try{
				if(disambiguate!=null) retString+="&disambiguate="+(disambiguate ? "1":"0");
				if(linkedData!=null) retString+="&linkedData="+(linkedData?"1":"0");
				if(coreference!=null) retString+="&coreference="+(coreference?"1":"0");
				if(quotations!=null) retString+="&quotations="+(quotations?"1":"0");
				if(sourceText!=null) retString+="&sourceText="+sourceText;
				if(showSourceText!=null) retString+="&showSourceText="+(showSourceText?"1":"0");
				if(cQuery!=null) retString+="&cquery="+URLEncoder.encode(cQuery,"UTF-8");
				if(xPath!=null) retString+="&xpath="+URLEncoder.encode(xPath,"UTF-8");
				if(maxRetrieve!=null) retString+="&maxRetrieve="+maxRetrieve.toString();
				if(baseUrl!=null) retString+="&baseUrl="+URLEncoder.encode(baseUrl,"UTF-8");
				if(sentiment!=null) retString+="&sentiment="+(sentiment?"1":"0");
			}
			catch(UnsupportedEncodingException e ){
				retString = "";
			}
			return retString;
		}
	}
