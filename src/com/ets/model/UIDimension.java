package com.ets.model;

import java.util.ArrayList;
import java.util.List;

import com.endeca.navigation.DimVal;
import com.endeca.navigation.DimValList;
import com.endeca.navigation.Dimension;

public class UIDimension {
	private List<Refinement> ancestors;
	private List<Refinement> completePath;
	private long id;
	private String name;
	private boolean open;
	private List<Refinement> refinements;
	private String url;

	public UIDimension() {
		name="EMPTY";
		id=-1;
	}

	public UIDimension(Dimension d) {
		name = d.getName();
		id = d.getId();
		System.out.println("Descriptor Dimension: " + d.getName() + ", " +d.getId());
		System.out.println(" ->Setting Refinements");
		setRefinements(d.getRefinements());
		System.out.println(" ->Setting Ancestors");
		setAncestors(d.getAncestors());
		System.out.println(" ->Setting Complete Path");
		setCompletePath(d.getCompletePath());
	}

	public List<Refinement> getAncestors() {
		return ancestors;
	}

	public List<Refinement> getCompletePath() {
		return completePath;
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
	public void setAncestors(DimValList dl) {
		ancestors = new ArrayList<Refinement>();
		DimVal dv = null;
		for (int i = 0; i < dl.size(); i++) {
//		for (int i = 0; i < 0; i++) {
			dv = dl.getDimValue(i);
			ancestors.add(new Refinement(dv));
			System.out.println("     -->>Adding ancestor:" + (new Refinement(dv)));
		}
	}

	public void setCompletePath(DimValList dl) {
		completePath = new ArrayList<Refinement>();
		DimVal dv = null;
		for (int i = 0; i < dl.size(); i++) {
//		for (int i = 0; i < 0; i++) {
			dv = dl.getDimValue(i);
			completePath.add(new Refinement(dv));
			System.out.println("     -->>Adding complete path:" + (new Refinement(dv)));
		}
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
//		for (int i = 0; i < 0; i++) {
			dv = dl.getDimValue(i);
			System.out.println("     -->>Adding refinement:" + (new Refinement(dv)));
			refinements.add(new Refinement(dv));
		}
	}
	public UIDimension setUIDimensionBasedOnNavigationState(Dimension d) {
		name = d.getName();
		id = d.getId();
		System.out.println("Refinement Dimension: " + d.getName() + ", " +d.getId());
		System.out.println(" ->Setting Refinements");
		setRefinements(d.getRefinements());
		System.out.println(" ->Setting Ancestors");
		setAncestors(d.getAncestors());
		System.out.println(" ->Setting Complete Path");
		setCompletePath(d.getCompletePath());
		return this;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UIDimension [ancestors=");
		builder.append(ancestors);
		builder.append(", completePath=");
		builder.append(completePath);
		builder.append(", id=");
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
