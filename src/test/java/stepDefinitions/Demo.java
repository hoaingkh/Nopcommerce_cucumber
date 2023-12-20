package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class Demo {
	public static void main(String[] args) {
		Map<String, String> map1 = new HashedMap();
		Map<String, String> map2 = new HashedMap();
		
		List<Map<String, String>> listMap = new ArrayList<>();
		
		map1.put("key1", "value1");
		map1.put("key2", "value2");
		map1.put("key5", "value5");

		map2.put("key3", "value3");
		map2.put("key4", "value4");
		
		listMap.add(map1);
		listMap.add(map2);
		
		
		System.out.println(listMap);
	}
}
