/**
 * 
 */
package one;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import utils.ParameterUtils;

/**
 * @author ericstiles
 *
 */
public class TestParameterUtils {

	@Test
	public void testGetEndecaQueryString_one() {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("N", new String[] { "0", "1" });
		String string = ParameterUtils.getEndecaQueryString(map);
		String solution = "N=0+1";
		if (!string.equals(solution)) {
			fail("1. Expected:" + solution + ", Returned:" + string);
		}

		map.put("Ne", new String[] { "1" });
		string = ParameterUtils.getEndecaQueryString(map);
		solution = "Ne=1&" + solution;
		if (!string.equals(solution)) {
			fail("2. Expected:" + solution + ", Returned:" + string);
		}
	}
	@Test
	public void testUnionParameterMap_one() {
		Map<String, String[]> map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		Map<String, String[]> map2 = new HashMap<String, String[]>();
		map2.put("N", new String[] { "2", "3" });		
		
		String string  = ParameterUtils.getEndecaQueryString(ParameterUtils.unionParameterMap(map1, map2));
		String solution = "N=0+1+2+3";

		if (!string.equals(solution)) {
			fail("1. Expected:" + solution + ", Returned:" + string);
		}
		
		////////////////////////

		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		map2 = new HashMap<String, String[]>();
		map2.put("N", new String[] { "1", "2" });		
		
		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.unionParameterMap(map1, map2));
		solution = "N=0+1+2";
		
		if (!string.equals(solution)) {
			fail("2. Expected:" + solution + ", Returned:" + string);
		}		
		
		////////////////////////

		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		map2 = new HashMap<String, String[]>();
		map2.put("Ne", new String[] { "1", "2" });		
		
		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.unionParameterMap(map1, map2));
		solution = "Ne=1+2&N=0+1";
		
		if (!string.equals(solution)) {
			fail("3. Expected:" + solution + ", Returned:" + string);
		}		
				
		////////////////////////

		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		map1.put("Ne", new String[] { "0",});
		map2 = new HashMap<String, String[]>();
		map2.put("N", new String[] { "1", "2" });		
		
		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.unionParameterMap(map1, map2));
		solution = "Ne=0&N=0+1+2";
		
		if (!string.equals(solution)) {
			fail("4. Expected:" + solution + ", Returned:" + string);
		}	
	}
	@Test
	public void testComplementParameterMap_one() {
		Map<String, String[]> map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		Map<String, String[]> map2 = new HashMap<String, String[]>();
		map2.put("N", new String[] { "2", "3" });		
		
		String string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1, map2,"N"));
		String solution = "N=0+1";

		if (!string.equals(solution)) {
			fail("1. Expected:" + solution + ", Returned:" + string);
		}
		
		////////////////////////

		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		map1.put("Ne", new String[] { "0",});
		map2 = new HashMap<String, String[]>();
		map2.put("N", new String[] { "1", "2" });

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1, map2,"N"));
		solution = "Ne=0&N=0";

		if (!string.equals(solution)) {
			fail("2. Expected:" + solution + ", Returned:" + string);
		}
	}
	@Test
	public void testComplementParameterMap_two() {
		Map<String, String[]> map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });		
		String string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"Ne", "1"));
		String solution = "Ne=1&N=0+1";

		if (!string.equals(solution)) {
			fail("1. When passing: " + "Ne, 1,  Expected:" + solution + ", Returned:" + string);
		}
		
		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		map1.put("Ne", new String[] { "0"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"Ne", "0"));
		solution = "N=0+1";

		if (!string.equals(solution)) {
			fail("2. When passing: " + "Ne, 0,  Expected:" + solution + ", Returned:" + string);
		}
		
		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		map1.put("Ne", new String[] { "0", "1"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"Ne", "0"));
		solution = "Ne=1&N=0+1";

		if (!string.equals(solution)) {
			fail("3. Expected:" + solution + ", Returned:" + string);
		}
		
		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0", "1" });
		map1.put("Ne", new String[] { "0", "1"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"Ne", "2"));
		solution = "Ne=0+1+2&N=0+1";

		if (!string.equals(solution)) {
			fail("4. Expected:" + solution + ", Returned:" + string);
		}

		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"Ne", "1"));
		solution = "Ne=1&N=0";

		if (!string.equals(solution)) {
			fail("5. Expected:" + solution + ", Returned:" + string);
		}
		
		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0"});
		map1.put("Ne", new String[] { "11485", "1"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"Ne", "1"));
		solution = "Ne=11485&N=0";

		if (!string.equals(solution)) {
			fail("6. Expected:" + solution + ", Returned:" + string);
		}
		
		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "4294962484"});
		map1.put("Ne", new String[] { "10093"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"N", "4294962484"));
		solution = "Ne=10093&N=0";

		if (!string.equals(solution)) {
			fail("7. Expected:" + solution + ", Returned:" + string);
		}

		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0"});
		map1.put("Ne", new String[] { "1"});
		map1.put("No", new String[] { "80"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"No", ""));
		solution = "Ne=1&N=0";

		if (!string.equals(solution)) {
			fail("8. Expected:" + solution + ", Returned:" + string);
		}
		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("N", new String[] { "0"});
		map1.put("Ne", new String[] { "1"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"No", ""));
		solution = "Ne=1&N=0";

		if (!string.equals(solution)) {
			fail("9. Expected:" + solution + ", Returned:" + string);
		}	
		/////////////////////////
		map1 = new HashMap<String, String[]>();
		map1.put("No", new String[] { "0"});
		map1.put("Ne", new String[] { "1"});
		map1.put("N", new String[] { "3"});

		string  = ParameterUtils.getEndecaQueryString(ParameterUtils.complementParameterMap(map1,"Ne", "1"));
		solution = "No=0&N=3";

		if (!string.equals(solution)) {
			fail("10. Expected:" + solution + ", Returned:" + string);
		}	
	}
	@Test
	public void testAdjustParameters_one() {	
		String[] strings  = ParameterUtils.adjustParameters(new String[] { "0 1", "2" });		
		if (strings.length != 3 && strings[0].equals("0") && strings[1].equals("1") && strings[2].equals("2")) {
			fail("1. Expected: string array length of " + 3 + ", Returned array length:" + strings.length);
		}		
		strings  = ParameterUtils.adjustParameters(new String[] { "0 1", "2 3" });		
		if (strings.length != 4 && strings[0].equals("0") && strings[1].equals("1") && strings[2].equals("2") && strings[3].equals("3")) {
			fail("2. Expected: string array length of " + 4 + ", Returned array length:" + strings.length);
		}	
		strings  = ParameterUtils.adjustParameters(new String[] { "0 1"});		
		if (strings.length != 2 && strings[0].equals("0") && strings[1].equals("1")) {
			fail("3. Expected: string array length of " + 2 + ", Returned array length:" + strings.length);
		}
	}
}
