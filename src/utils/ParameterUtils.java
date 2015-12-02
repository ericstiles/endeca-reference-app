package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class ParameterUtils {
	   /* Get actual class name to be printed on */
	   static Logger log = Logger.getLogger(ParameterUtils.class.getName());
	/**
	 * 
	 * @param parameterMap1
	 * @param parameterMap2
	 * @param property
	 * @return
	 */
	public static Map<String, String[]> complementParameterMap(Map<String, String[]> parameterMap1,
			Map<String, String[]> parameterMap2, String property) {
		List<String> tmpList = new ArrayList<String>();
		tmpList.addAll(Arrays.asList(parameterMap1.get(property)));
		tmpList.removeAll(Arrays.asList(parameterMap2.get(property)));
		parameterMap1.put(property, tmpList.toArray(new String[tmpList.size()]));
		return parameterMap1;
	}
	/**
	 * 
	 * @param parameterMap1
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, String[]> complementParameterMap(Map<String, String[]> parameterMap1, String key,
			String value) {
		log.info("-----Start Method Call-----");
		log.info("key: " + key);
		log.info("value: " + value);
		log.info(ParameterUtils.getEndecaQueryString(parameterMap1));
		Map<String, String[]> returnMap = new HashMap<String, String[]>();
		Iterator<String> it = parameterMap1.keySet().iterator();
		String iteratorKey = null;
		String[] iteratorStringArray = null;
		List<String> tmpIteratorStringList;
		boolean touched = false;
		while (it.hasNext()){
			log.info("Starting loop on iterator");
			iteratorKey = it.next();
			iteratorStringArray = ParameterUtils.adjustParameters(parameterMap1.get(iteratorKey));
			tmpIteratorStringList = new ArrayList<String>();
			if (iteratorKey.equals(key)){
				log.info("  Do Something because " + iteratorKey + " = " + key);
				touched = true;
				tmpIteratorStringList.addAll(Arrays.asList(iteratorStringArray));
				if (!tmpIteratorStringList.remove(value)){
					log.info("    Nothing removed from list: " + tmpIteratorStringList);
					if (value != null & value.length() > 0){
						tmpIteratorStringList.add(value);
						log.info("    Added " + value + " to list: " + tmpIteratorStringList);
					}
				} else {
					log.info("    Removed " + value + ". New list: " + tmpIteratorStringList);
					if (tmpIteratorStringList.size() == 0 && key.equals("N")){
						tmpIteratorStringList.add("0");
					}
					
				}
				if (tmpIteratorStringList.size() > 0 && null != value && value.length() > 0) {
					log.info("  Adding to list under key: (" + iteratorKey + ") values " + tmpIteratorStringList);
					returnMap.put(iteratorKey, tmpIteratorStringList.toArray(new String[tmpIteratorStringList.size()]));
				}				
			} else {
				log.info("  Skipping because " + iteratorKey + " != " + key);
				returnMap.put(iteratorKey, iteratorStringArray);
			}
			log.info("Finishing loop on iterator");
		}
		if (!touched && null != value && value.length() > 0){
			returnMap.put(key, new String[]{value});
		}
		log.info(ParameterUtils.getEndecaQueryString(returnMap));
		log.info("-----Leave Method Call-----");
		return returnMap;
	}
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	public static String getEndecaQueryString(Map<String, String[]> parameterMap) {
		Iterator<String> it = parameterMap.keySet().iterator();
		String parameters = "";
		String parameter = "";
		while (it.hasNext()) {
			parameter = it.next();
			String[] strings = parameterMap.get(parameter);
			if (strings != null && strings.length > 0) {
				parameter += "=";
				for (int i = 0; i < strings.length; i++) {
					parameter += strings[i];
					if (i < strings.length - 1) {
						parameter += "+";
					}
				}
			}
			parameters += parameter;
			if (it.hasNext()) {
				parameters += "&";
			}
		}
		return parameters.replace(' ', '+');
	}
	/**
	 * 
	 * @param parameterMap1
	 * @param parameterMap2
	 * @return
	 */
	public static Map<String, String[]> unionParameterMap(Map<String, String[]> parameterMap1,
			Map<String, String[]> parameterMap2) {
		Map<String, String[]> returnMap = new HashMap<String, String[]>();
		Set<String> combinedKeySet = new TreeSet<String>(), combinedValueSet = new TreeSet<String>();
		combinedKeySet.addAll(parameterMap1.keySet());
		combinedKeySet.addAll(parameterMap2.keySet());
		Iterator<String> it = combinedKeySet.iterator();
		String key;
		String[] valueArray1, valueArray2;
		while (it.hasNext()) {
			key = it.next();
			valueArray1 = parameterMap1.get(key);
			valueArray2 = parameterMap2.get(key);
			combinedValueSet = new TreeSet<String>();
			combinedValueSet.addAll(Arrays.asList(ArrayUtils.addAll(valueArray1, valueArray2)));
			returnMap.put(key, (combinedValueSet.toArray(new String[combinedValueSet.size()])));
			;
		}
		return returnMap;
	}
	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public static String [] adjustParameters(String[] parameters){
		List<String> returnList = new ArrayList<String>();
		for (String parameter: parameters){
			returnList.addAll(Arrays.asList(parameter.split(" ")));		
		}
		return returnList.toArray(new String[returnList.size()]);
	}
}
