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
import entity.Marka;
import entity.Model;
import java.lang.reflect.Type;

/**
 *
 * @author david
 */
public class MyTypemarka implements JsonSerializer<Marka>{
    
    
    @Override
    public JsonElement serialize(Marka m, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", m.getIdMarka());
        jsonObject.addProperty("naziv", m.getNaziv());
        return jsonObject;
    }
    
}