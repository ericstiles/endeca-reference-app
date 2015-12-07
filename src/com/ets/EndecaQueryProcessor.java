package com.ets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.endeca.navigation.AssocDimLocations;
import com.endeca.navigation.AssocDimLocationsList;
import com.endeca.navigation.DimLocation;
import com.endeca.navigation.DimVal;
import com.endeca.navigation.Dimension;
import com.endeca.navigation.DimensionList;
import com.endeca.navigation.ENEQuery;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.ENEQueryResults;
import com.endeca.navigation.ERec;
import com.endeca.navigation.ERecList;
import com.endeca.navigation.HttpENEConnection;
import com.endeca.navigation.Navigation;
import com.endeca.navigation.Property;
import com.endeca.navigation.PropertyMap;
import com.endeca.navigation.UrlENEQuery;
import com.ets.model.ResponseBean;
import com.ets.model.UIDimension;

import utils.TableBuilder;

public class EndecaQueryProcessor {
	public static String returnSpaceString(int space) {
		String spacing = "";
		for (int i = 0; i < space; i++) {
			spacing = spacing + " ";
		}
		return spacing;
	}
	private List<UIDimension> associatedDimensions = new ArrayList<UIDimension>();
	private List<UIDimension> listDimension = new ArrayList<UIDimension>();
	private Map<String, String> mapQueryMethods = new HashMap<String, String>();
	private Map<String, String> mapQueryResultsMethods = new HashMap<String, String>();
	private ENEQueryResults qr = null;

	private StringBuffer sb = null;

	public List<UIDimension> getRefinementDimensions() {
		return associatedDimensions;
	}

	public Map<String, String> getMapQueryMethods() {
		return mapQueryMethods;
	}

	public Map<String, String> getMapQueryResultsMethods() {
		return mapQueryResultsMethods;
	}

	public List<UIDimension> getNavigation() {
		return listDimension;
	}

	public void printAssocDimLocationsList(AssocDimLocations ad, int space) {
		TableBuilder tb = new TableBuilder(space);
		tb.addRow("Property", "Value");
		tb.addRow("------", "------");
		DimVal dr = ad.getDimRoot();
		sb.append(returnSpaceString(space) + "Root Dimension:").append("\n");
		printDimVal(dr, space + 2);
		sb.append(returnSpaceString(space + 2) + "Total Number of Dim Locations: " + ad.getTotalNumLocations())
				.append("\n");
		if (ad.getTotalNumLocations() > 0) {
			for (int i = 0; i < ad.getTotalNumLocations(); i++) {
				sb.append(returnSpaceString(space + 2) + "  Dim Location " + i + ":").append("\n");
				printDimLocation((DimLocation) ad.get(i), space + 2);
			}
		}
	}

	public void printDimLocation(DimLocation dl, int space) {
		sb.append(returnSpaceString(space + 2) + "object: " + dl).append("\n");
		printDimVal(dl.getDimValue(), space + 4);
	}

	public void printDimVal(DimVal dl, int space) {
		TableBuilder tb = new TableBuilder(space);
		tb.addRow("Property", "Value");
		tb.addRow("------", "------");
		tb.addRow("Object", dl.toString());
		tb.addRow("Dimension Id: ", dl.getDimensionId() + "");
		tb.addRow("Dimension Name:  ", dl.getDimensionName());
		tb.addRow("Id:  ", dl.getId() + "");
		tb.addRow("Name:  ", dl.getName());
		tb.addRow("Property Map:  ", dl.getProperties() + "");
		tb.addRow("Synonyms:  ", dl.getSynonyms() + "");
		tb.addRow("Leaf:  ", dl.isLeaf() + "");
		tb.addRow("MultiSelectAnd:  ", dl.isMultiSelectAnd() + "");
		tb.addRow("MultiSelectOr:  ", dl.isMultiSelectOr() + "");
		tb.addRow("Navigable:  ", dl.isNavigable() + "");
		sb.append(tb.toString());
	}

	public void run(ResponseBean bean) {
		sb = new StringBuffer();

		try {

			ENEQuery usq;
			usq = new UrlENEQuery(bean.getParameters(), "UTF-8");
			// usq.setNavNumERecs(NUMBER_OF_RECORDS_RETURNED);

			qr = new HttpENEConnection("localhost", 15000).query(usq);

			processENDEQueryMethods(usq);
			processENEQueryResultsMethods(qr);
			
			bean.setQueryResultsMethods(this.getMapQueryResultsMethods());
			bean.setQueryMethods(this.getMapQueryMethods());
			bean.setNavigation(this.getNavigation());		
			bean.setRefinementsList(this.getRefinementDimensions());
			bean.setNavigationDescriptorIds(usq.getNavDescriptors());
			
			
			if (qr.containsNavigation()) {
				setNavigation(qr.getNavigation(), bean.getParameterMap());
				bean.setRecordCount(qr.getNavigation().getTotalNumERecs());
				bean.setPagination(bean.getRecordCount());
				mapQueryResultsMethods.put("nav.getERecs().size()", qr.getNavigation().getERecs().size() + "");
				bean.setRecordsList(qr.getNavigation().getERecs());
				
				String[] page = bean.getParameterMap().get("No");
				if(null == page || page.length == 0){
					bean.setCurrentPage(0);
				} else {
					bean.setCurrentPage((new Integer(page[0]).intValue()) / bean.getRecordsPerPage());
				}
				
				
				// SupplementList merchList = nav.getSupplements();

			} else if (qr.containsERecs()) {
				//Not implemented yet
			}
		} catch (ENEQueryException e) {
			e.printStackTrace();
		}

		//Left over from running command line.  
		//Remove as functionality is added on jsp page
		//print(qr);
		//System.out.println(sb.toString());

	}

	public void setMapQueryMethods(Map<String, String> mapQueryMethods) {
		this.mapQueryMethods = mapQueryMethods;
	}

	public void setMapQueryResultsMethods(Map<String, String> mapQueryResultsMethods) {
		this.mapQueryResultsMethods = mapQueryResultsMethods;
	}

	public void setNavigation(Navigation navigation, Map<String, String[]> parameterMap) {
		//System.out.println("navigation.getRefinementDimensions().size():" + navigation.getCompleteDimensions().size());
		DimensionList dl = navigation.getRefinementDimensions();
		Dimension d = null;
		for (int i = 0; i < dl.size(); i++) {
			d = (Dimension) dl.get(i);
			listDimension.add(new UIDimension(d));
			//System.out.println(d.getRoot().getName());
		}
		//System.out.println("navigation.getDescriptorDimensions().size():" + navigation.getDescriptorDimensions().size());
		dl = navigation.getDescriptorDimensions();
		d = null;
		for (int i = 0; i < dl.size(); i++) {
			d = (Dimension) dl.get(i);
			associatedDimensions.add(new UIDimension().setUIDimensionBasedOnNavigationState(d));
			//System.out.println(list1.get(i));
		}
	}

	private void print(ENEQueryResults qr) {
		if (qr.containsNavigation()) {
			printERecs(qr.getNavigation().getERecs());
			sb.append("merchList:" + qr.getNavigation().getSupplements()).append("\n");
		} else if (qr.containsERecs()) {
			sb.append("Starting to print records retrieved").append("\n");
			printERecs(qr.getERecs());

		}
	}

	private void printERecs(ERecList eRecList) {
		for (int i = 0; i < eRecList.size(); i++) {
			sb.append("---START---").append("\n");
			ERec e = (ERec) eRecList.get(i);
			sb.append("  Object: " + e).append("\n");
			sb.append("  Spec: " + e.getSpec()).append("\n");
			AssocDimLocationsList list = e.getDimValues();
			sb.append("  AssocDimLocationsList size: " + list.size()).append("\n");
			for (int j = 0; j < list.size(); j++) {
				printAssocDimLocationsList((AssocDimLocations) list.get(j), 4);
			}
			sb.append(returnSpaceString(5) + "ERec Properties").append("\n");
			printPropertiesMap(e.getProperties(), 7);
			sb.append("---STOP---");
		}
	}

	private void printPropertiesMap(PropertyMap properties, int space) {
		Set set = properties.entrySet();
		Iterator it = set.iterator();
		TableBuilder tb = new TableBuilder(space);
		tb.addRow("Key", "Value");
		tb.addRow("---", "---");
		while (it.hasNext()) {
			Property property = (Property) it.next();
			tb.addRow((String) property.getKey(), (String) property.getValue());
		}
		sb.append(tb);
	}

	private void processENDEQueryMethods(ENEQuery usq) {
		Method[] methods = ENEQuery.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			try {
				//System.out.println(methods[i].getName());
				if (methods[i].getName().startsWith("get")) {
					mapQueryMethods.put("usq." + methods[i].getName(), methods[i].invoke(usq) + "");
				}
			} catch (IllegalAccessException e) {
				System.out.println("1. FAILING:  method call:" + "usq." + methods[i].getName());
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("2. FAILING:  method call:" + "usq." + methods[i].getName());
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.out.println("3. FAILING:  method call:" + "usq." + methods[i].getName());
				e.printStackTrace();
			}

		}
	}

	private void processENEQueryResultsMethods(ENEQueryResults qr) {
		mapQueryResultsMethods.put("qr.getTotalNetworkAndComputeTime()", qr.getTotalNetworkAndComputeTime() + "");
		mapQueryResultsMethods.put("qr.containsAggrERec()", qr.containsAggrERec() + "");
		mapQueryResultsMethods.put("qr.containsDimensionSearch()", qr.containsDimensionSearch() + "");
		mapQueryResultsMethods.put("qr.containsDimSearch()", qr.containsDimSearch() + "");
		mapQueryResultsMethods.put("qr.containsERec()", qr.containsERec() + "");
		mapQueryResultsMethods.put("qr.containsERecs()", qr.containsERecs() + "");
		mapQueryResultsMethods.put("qr.containsNavigation()", qr.containsNavigation() + "");
		mapQueryResultsMethods.put("qr.getAggrERec()", qr.getAggrERec() + "");
		mapQueryResultsMethods.put("qr.getDimensionSearch()", qr.getDimensionSearch() + "");
		mapQueryResultsMethods.put("qr.getERec()", qr.getERec() + "");
		mapQueryResultsMethods.put("qr.getERecs()", qr.getERecs() + "");
		mapQueryResultsMethods.put("qr.getNavigation()", qr.getNavigation() + "");
		mapQueryResultsMethods.put("qr.getNavigation().getTotalNumERecs())",
				qr.getNavigation().getTotalNumERecs() + "");
	}
}
