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
import entity.Narudzbenica;
import java.lang.reflect.Type;

/**
 *
 * @author david
 */
public class MyTypeOrder implements JsonSerializer<Narudzbenica> {

    @Override
    public JsonElement serialize(Narudzbenica n, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", n.getId());
        jsonObject.addProperty("model", n.getIdArtikla().getModelId().getNazivModela());
        jsonObject.addProperty("mark", n.getIdArtikla().getModelId().getMarkaId().getNaziv());
        jsonObject.addProperty("price", n.getIdArtikla().getCena());
        return jsonObject;
    }

}
