package com.ets.model;

import java.util.List;
import java.util.Map;

import com.endeca.navigation.ERecList;

import utils.ParameterUtils;

public class ResponseBean {
	String parameters="";
	String requestUrl="";
	int numRecordsReturned=1;
	String mainContent = "EMPTY";
	Map<String, String> mapQueryResultsMethods = null;
	List<UIDimension> dimensions = null;
	private Map<String, String[]> parameterMap;
	private String referringURL;
	private long recordCount;
	private List<UIDimension> refinementsList;
	private Map<String, String> mapQueryMethods;
	private List navDescriptors;
	private ERecList eRecList;
	private int numberOfPages;
	private int recordsPerPage;
	private int currentPage = 10;
	private int visiblePageRange = 7;

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public List<UIDimension> getNavigation() {
		return dimensions;
	}

	public void setNavigation(List<UIDimension> dimensions) {
		this.dimensions = dimensions;
	}

	public int getNumRecordsReturned() {
		return numRecordsReturned;
	}

	public void setNumRecordsReturned(int numRecordsReturned) {
		this.numRecordsReturned = numRecordsReturned;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getRequestParameters() {
		return parameters;
	}

	public void setRequestParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setQueryResultsMethods(Map<String, String> mapQueryResultsMethods) {
		this.mapQueryResultsMethods = mapQueryResultsMethods;
		
	}
	public Map<String, String> getQueryResultsMethods() {
		return mapQueryResultsMethods;		
	}	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseBean [parameters=");
		builder.append(parameters);
		builder.append(", requestUrl=");
		builder.append(requestUrl);
		builder.append(", numRecordsReturned=");
		builder.append(numRecordsReturned);
		builder.append(", mainContent=");
		builder.append(mainContent);
		builder.append("]");
		return builder.toString();
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap ;
	}
	public Map<String, String[]> getParameterMap() {
		return this.parameterMap;
	}
	public void setReferringURL(String referer) {
		this.referringURL = referer;
		
	}
	public String getReferringURL(){
		return referringURL;
	}

	public void setRefinementsList(List<UIDimension> refinementsList) {
		this.refinementsList = refinementsList;
		
	}
	public List<UIDimension> getRefinementsList(){
		return this.refinementsList;
	}
	public void setQueryMethods(Map<String, String> mapQueryMethods) {
		this.mapQueryMethods = mapQueryMethods;
	}
	public Map<String, String> getQueryMethods(){
		return mapQueryMethods;
	}

	public void setNavigationDescriptorIds(List navDescriptors) {
		this.navDescriptors = navDescriptors;
	}
	public List getNavigationDescriptorIds(){
		return navDescriptors;
	}

	public String getQueryString(){
		return ParameterUtils.getEndecaQueryString(this.getParameterMap());
	}
	public String getQueryString(String key, String value){
		return ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(this.getParameterMap(), key, value));
	}

	public void setRecordsList(ERecList eRecList) {
		this.eRecList = eRecList;
	}
	public List<?> getRecordsList(){
		return eRecList;
	}
	public void setPagination(long recordCount) {
		int recordsPerPage = 10;
		setNumberOfPages((int) Math.ceil((double)recordCount / recordsPerPage));
		setRecordsPerPage(recordsPerPage);
		
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage=recordsPerPage;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public void setCurrentPage(int currentPage){
		this.currentPage=currentPage;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public int getVisiblePageRange() {
		return visiblePageRange ;
	}
	public void setVisiblePageRange(int visiblePageRange){
		this.visiblePageRange=visiblePageRange;
	}	
}
