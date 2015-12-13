package com.ets.model;

import java.util.List;

import com.endeca.navigation.DimVal;

public class Refinement extends UIDimension{
	private List<Refinement> ancestors;
	private List<Refinement> completePath;
	private String count = null;
	private long id;
	private String name = null;
	/**
	 * 
	 */
	public Refinement(){
	}
	/**
	 * 
	 * @param dv
	 */
	public Refinement(DimVal dv){
		this.name=dv.getName();
		this.count = (String) dv.getProperties().get("DGraph.Bins");
		this.id = dv.getId();
	}
	public List<Refinement> getAncestors() {
		return ancestors;
	}
	public List<Refinement> getCompletePath() {
		return completePath;
	}
	public String getCount() {
		return count;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setAncestors(List<Refinement> ancestors) {
		this.ancestors = ancestors;
	}
	public void setCompletePath(List<Refinement> completePath) {
		this.completePath = completePath;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Refinement [ancestors=");
		builder.append(ancestors);
		builder.append(", completePath=");
		builder.append(completePath);
		builder.append(", count=");
		builder.append(count);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
}
