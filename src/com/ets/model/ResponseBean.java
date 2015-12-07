package com.ets.model;

import java.util.List;
import java.util.Map;

import com.endeca.navigation.ERecList;

import utils.ParameterUtils;

public class ResponseBean {
	private int currentPage = 10;
	private List<UIDimension> dimensions = null;
	private ERecList eRecList;
	private String mainContent = "EMPTY";
	private Map<String, String> mapQueryMethods;
	private Map<String, String> mapQueryResultsMethods = null;
	private List navDescriptors;
	private int numberOfPages;
	private int numRecordsReturned=1;
	private Map<String, String[]> parameterMap;
	private String parameters="";
	private long recordCount;
	private int recordsPerPage=10;
	private String referringURL;
	private List<UIDimension> refinementsList;
	private String requestUrl="";
	private int visiblePageRange=7;

	public int getCurrentPage() {
		return currentPage;
	}

	public List<UIDimension> getNavigation() {
		return dimensions;
	}

	public List getNavigationDescriptorIds(){
		return navDescriptors;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public int getNumRecordsReturned() {
		return numRecordsReturned;
	}

	public Map<String, String[]> getParameterMap() {
		return this.parameterMap;
	}

	public String getParameters() {
		return parameters;
	}

	public Map<String, String> getQueryMethods(){
		return mapQueryMethods;
	}

	public Map<String, String> getQueryResultsMethods() {
		return mapQueryResultsMethods;		
	}

	public String getQueryString(){
		return ParameterUtils.getEndecaQueryString(this.getParameterMap());
	}

	public String getQueryString(String key, String value){
		return ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(this.getParameterMap(), key, value));
	}

	public long getRecordCount() {
		return recordCount;
	}

	public List<?> getRecordsList(){
		return eRecList;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}	
	public String getReferringURL(){
		return referringURL;
	}

	public List<UIDimension> getRefinementsList(){
		return this.refinementsList;
	}
	public String getRequestParameters() {
		return parameters;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public int getVisiblePageRange() {
		return visiblePageRange ;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage=currentPage;
	}
	public void setNavigation(List<UIDimension> dimensions) {
		this.dimensions = dimensions;
	}
	public void setNavigationDescriptorIds(List navDescriptors) {
		this.navDescriptors = navDescriptors;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public void setNumRecordsReturned(int numRecordsReturned) {
		this.numRecordsReturned = numRecordsReturned;
	}
	public void setPagination(long recordCount) {
		setNumberOfPages((int) Math.ceil((double)recordCount / recordsPerPage));
		setRecordsPerPage(recordsPerPage);
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap ;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setQueryMethods(Map<String, String> mapQueryMethods) {
		this.mapQueryMethods = mapQueryMethods;
	}
	public void setQueryResultsMethods(Map<String, String> mapQueryResultsMethods) {
		this.mapQueryResultsMethods = mapQueryResultsMethods;
		
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public void setRecordsList(ERecList eRecList) {
		this.eRecList = eRecList;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage=recordsPerPage;
	}
	public void setReferringURL(String referer) {
		this.referringURL = referer;
		
	}

	public void setRefinementsList(List<UIDimension> refinementsList) {
		this.refinementsList = refinementsList;
		
	}

	public void setRequestParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public void setVisiblePageRange(int visiblePageRange){
		this.visiblePageRange=visiblePageRange;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseBean [currentPage=");
		builder.append(currentPage);
		builder.append(", dimensions=");
		builder.append(dimensions);
		builder.append(", eRecList=");
		builder.append(eRecList);
		builder.append(", mainContent=");
		builder.append(mainContent);
		builder.append(", mapQueryMethods=");
		builder.append(mapQueryMethods);
		builder.append(", mapQueryResultsMethods=");
		builder.append(mapQueryResultsMethods);
		builder.append(", navDescriptors=");
		builder.append(navDescriptors);
		builder.append(", numberOfPages=");
		builder.append(numberOfPages);
		builder.append(", numRecordsReturned=");
		builder.append(numRecordsReturned);
		builder.append(", parameterMap=");
		builder.append(parameterMap);
		builder.append(", parameters=");
		builder.append(parameters);
		builder.append(", recordCount=");
		builder.append(recordCount);
		builder.append(", recordsPerPage=");
		builder.append(recordsPerPage);
		builder.append(", referringURL=");
		builder.append(referringURL);
		builder.append(", refinementsList=");
		builder.append(refinementsList);
		builder.append(", requestUrl=");
		builder.append(requestUrl);
		builder.append(", visiblePageRange=");
		builder.append(visiblePageRange);
		builder.append("]");
		return builder.toString();
	}	
}
