package com.jymj.zhglxt.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jymj.zhglxt.bean.BcjcBbBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonToMapUtil {
    /** * @param content json字符串 * @return 如果转换失败返回null, */
    public static Map<String, List<BcjcBbBean>> jsonToMap(String content) {

        content = content.trim();
        Map<String, List<BcjcBbBean>> result = new HashMap<>();
        try {

            if (content.charAt(0) == '[') {

                JSONArray jsonArray = new JSONArray(content);
                for (int i = 0; i < jsonArray.length(); i++) {

                    Object value = jsonArray.get(i);
                    if (value instanceof JSONArray || value instanceof JSONObject) {
                        Gson gson = new Gson();
                        List<BcjcBbBean> bcjcBbBean = gson.fromJson(value.toString().trim(), new TypeToken<List<BcjcBbBean>>() {}.getType());
//                        List<BcjcBbBean> bcjcBbBean = gson.fromJson(value.toString().trim(), List<BcjcBbBean>.class);
                        result.put(i + "", bcjcBbBean);
                    } else {
                        Gson gson = new Gson();
                        List<BcjcBbBean> bcjcBbBean = gson.fromJson(value.toString().trim(), new TypeToken<List<BcjcBbBean>>() {}.getType());
                        result.put(i + "", bcjcBbBean);
                    }
                }
            } else if (content.charAt(0) == '{'){

                JSONObject jsonObject = new JSONObject(content);
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {

                    String key = iterator.next();
                    Object value = jsonObject.get(key);
                    if (value instanceof JSONArray || value instanceof JSONObject) {

                        Gson gson = new Gson();
                        List<BcjcBbBean> bcjcBbBean = gson.fromJson(value.toString().trim(), new TypeToken<List<BcjcBbBean>>() {}.getType());
                        result.put(key, bcjcBbBean);
                    } else {

                        Gson gson = new Gson();
                        List<BcjcBbBean> bcjcBbBean = gson.fromJson(value.toString().trim(), new TypeToken<List<BcjcBbBean>>() {}.getType());
                        result.put(key,bcjcBbBean);
                    }
                }
            }else {

                Log.e("异常", "json2Map: 字符串格式错误");
            }
        } catch (JSONException e) {

            Log.e("异常", "json2Map: ", e);
            result = null;
        }
        return result;
    }
}
