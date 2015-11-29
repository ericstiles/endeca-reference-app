package com.ets.model;

import java.util.List;
import java.util.Map;

import com.endeca.navigation.AggrERecList;
import com.endeca.navigation.DimValIdList;
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
	private List<UIDimension> list1;
	private List<UIDimension> list2;
	private List<UIDimension> list3;
	private Map<String, String> mapQueryMethods;
	private List navDescriptors;
	private ERecList eRecList;

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
	public Map<String, String[]> getParameterMap(){
		return parameterMap;
	}

	public void setReferringURL(String referer) {
		this.referringURL = referer;
		
	}
	public String getReferringURL(){
		return referringURL;
	}

	public void setList1(List<UIDimension> list1) {
		this.list1 = list1;
		
	}

	public void setList2(List<UIDimension> list2) {
		this.list2 = list2;
		
	}

	public void setList3(List<UIDimension> list3) {
		this.list3 = list3;
		
	}
	public List<UIDimension> getList1(){
		return this.list1;
	}
	public List<UIDimension> getList2(){
		return this.list2;
	}
	public List<UIDimension> getList3(){
		return this.list3;
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
	
	public String getQueryString(String key, String value){
		return ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(this.getParameterMap(), key, value));
	}

	public void setRecordsList(ERecList eRecList) {
		this.eRecList = eRecList;
	}
	public List<?> getRecordsList(){
		return eRecList;
	}
}
