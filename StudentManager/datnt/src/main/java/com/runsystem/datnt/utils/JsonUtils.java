package com.runsystem.datnt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	public static String objectToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;

		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	public static void main(String[] args) {
	}
	
}