package com.github.bolexueyuan.android.analytics.analytics.net.core;


import com.github.bolexueyuan.android.analytics.analytics.net.gson.JsonDeserializationContext;
import com.github.bolexueyuan.android.analytics.analytics.net.gson.JsonDeserializer;
import com.github.bolexueyuan.android.analytics.analytics.net.gson.JsonElement;

import java.lang.reflect.Type;

/**
 * @author Aidi on 2018/1/8.
 */
public class IntegerAdapter implements JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        try {
            return json.getAsInt();
        } catch (Exception e) {
            return 0;
        }
    }

}
