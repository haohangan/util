package org.eva.core.zk;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;

public class TestJson {
  @Test
  public void test(){
	  Map<String,String> map = new HashMap<String,String>();
	  map.put("a", "1");
	  map.put("b", "2");
	  map.put("c", "3");
	  map.put("d", "4");
	  map.put("e", "5");
	  Gson gson = new Gson();
	  String jsonStr = gson.toJson(map);
	  System.out.println(jsonStr);
  }
}
