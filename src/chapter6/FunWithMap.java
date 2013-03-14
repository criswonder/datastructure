package chapter6;

import java.util.HashMap;
import java.util.Map;

public class FunWithMap {
	public static void main(String args[]){
		Map map = getWithMap();
		System.out.println(map.get("493441095"));
		System.out.println(map.get("23423"));
	}

	private static Map getWithMap() {
		Map ma=new HashMap<String, String>();
		ma.put("493441095", "mao");
		ma.put("493441095", "mao11");
		return ma;
	}
	
}
