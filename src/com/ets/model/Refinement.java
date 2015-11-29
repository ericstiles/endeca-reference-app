package com.ets.model;

import com.endeca.navigation.DimVal;

public class Refinement {
	public long id;
	public String name = null;
	public String count = null;
	public Refinement(DimVal dv){
		this.name=dv.getName();
		this.count = (String) dv.getProperties().get("DGraph.Bins");
		this.id = dv.getId();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Refinement [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
