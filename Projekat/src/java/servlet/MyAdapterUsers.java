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
import entity.Artikal;
import entity.Users;
import java.lang.reflect.Type;

/**
 *
 * @author david
 */
public class MyAdapterUsers implements JsonSerializer<Users> {

    @Override
    public JsonElement serialize(Users u, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",u.getId());
        jsonObject.addProperty("name", u.getIme());
        jsonObject.addProperty("lastName", u.getPrezime());
        jsonObject.addProperty("adress", u.getAdresa());
        jsonObject.addProperty("email", u.getEmail());
        jsonObject.addProperty("username", u.getUsername());
        jsonObject.addProperty("password", u.getPassword());
         jsonObject.addProperty("phone", u.getPhone());
        jsonObject.addProperty("role", u.getRole());
        return jsonObject;
    }

}
