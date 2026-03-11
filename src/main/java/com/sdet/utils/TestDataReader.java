package com.sdet.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class TestDataReader {

    public static Object[][] getLoginData(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(
                new FileReader(filePath));

            Object[][] data = new Object[array.size()][4];

            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                data[i][0] = obj.get("username");
                data[i][1] = obj.get("password");
                data[i][2] = obj.get("expectedMessage");
                data[i][3] = obj.get("shouldPass");
            }
            return data;

        } catch (Exception e) {
            throw new RuntimeException(
                "Failed to read test data: " + e.getMessage());
        }
    }
}