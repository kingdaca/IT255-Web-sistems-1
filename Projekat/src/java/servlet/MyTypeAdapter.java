/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.Artikal;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 *
 * @author david
 */
public class MyTypeAdapter implements JsonSerializer<Artikal> {

    @Override
    public JsonElement serialize(Artikal a, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", a.getId());
        jsonObject.addProperty("cubic", a.getKubikaza());
        jsonObject.addProperty("price", a.getCena());
        jsonObject.addProperty("power", a.getSnaga());
        jsonObject.addProperty("desc", a.getOpis());
        jsonObject.addProperty("img", a.getImage());
        jsonObject.addProperty("model_id", a.getModelId().getIdModel());
        jsonObject.addProperty("mark", a.getModelId().getMarkaId().getNaziv());
        jsonObject.addProperty("model", a.getModelId().getNazivModela());
        jsonObject.addProperty("user", a.getIdUser().getId());
        return jsonObject;
    }
}
