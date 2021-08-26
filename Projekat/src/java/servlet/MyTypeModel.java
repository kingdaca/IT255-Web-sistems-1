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
import entity.Model;
import entity.Users;
import java.lang.reflect.Type;

/**
 *
 * @author david
 */
public class MyTypeModel implements JsonSerializer<Model>{
    
    
    @Override
    public JsonElement serialize(Model m, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id_model", m.getIdModel());
        jsonObject.addProperty("naziv_modela", m.getNazivModela());
        jsonObject.addProperty("marka_id", m.getMarkaId().getIdMarka());
        return jsonObject;
    }

    
    
}
