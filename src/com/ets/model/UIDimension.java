package com.ets.model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import com.endeca.navigation.DimVal;
import com.endeca.navigation.DimValList;
import com.endeca.navigation.Dimension;

public class UIDimension {
	private AbstractList<Refinement> refinements;

	public UIDimension() {

	}

	public UIDimension(Dimension d) {
		name = d.getName();
		id = d.getId();
		DimValList dl = d.getRefinements();
		setRefinements(dl);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private void setRefinements(DimValList dl) {
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

	public List<Refinement> getRefinements() {
		return refinements;
	}

	String name;
	long id;
	boolean open;
	String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UIDimension [refinements=");
		builder.append(refinements);
		builder.append(", name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", open=");
		builder.append(open);
		builder.append("]");
		return builder.toString();
	}

}
