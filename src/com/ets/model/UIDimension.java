package com.ets.model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import com.endeca.navigation.DimVal;
import com.endeca.navigation.DimValList;
import com.endeca.navigation.Dimension;

public class UIDimension {
	private long id;

	private String name;

	private boolean open;

	private AbstractList<Refinement> refinements;

	private String url;

	public UIDimension() {

	}

	public UIDimension(Dimension d) {
		name = d.getName();
		id = d.getId();
		DimValList dl = d.getRefinements();
		setRefinements(dl);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public List<Refinement> getRefinements() {
		return refinements;
	}
	public String getUrl() {
		return url;
	}
	public boolean isOpen() {
		return open;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setRefinements(DimValList dl) {
		refinements = new ArrayList<Refinement>();
		DimVal dv = null;
		for (int i = 0; i < dl.size(); i++) {
			dv = dl.getDimValue(i);
			refinements.add(new Refinement(dv));
		}

	}

	public UIDimension setUIDimensionBasedOnNavigationState(Dimension d) {
		name = d.getName();
		id = d.getId();
		setRefinements(d.getCompletePath());
		return this;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UIDimension [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", open=");
		builder.append(open);
		builder.append(", refinements=");
		builder.append(refinements);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

}
