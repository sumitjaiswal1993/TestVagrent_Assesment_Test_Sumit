package com.RCB.TestVagrant.Reusable;

import io.restassured.path.json.JsonPath;

public class CommonUtils {

public static JsonPath rawToJson(String response) {
		
		JsonPath js = new JsonPath(response);
		return js;
	}
}
