/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Artikal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
@WebServlet(name = "getProductsByUserId", urlPatterns = {"/getProductsByUserId"})
public class getProductsByUserId extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        setAccessControlHeaders(response);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        List<Artikal> artikli = new ArrayList<Artikal>();
        
        String idUser = request.getParameter("UserId");

        et.begin();
        artikli = em.createNativeQuery("SELECT * FROM artikal WHERE id_user = ?",Artikal.class).setParameter(1, idUser).getResultList();
        et.commit();

        PrintWriter out = response.getWriter();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.disableHtmlEscaping();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.serializeNulls();
        gsonBuilder.registerTypeAdapter(Artikal.class, new MyTypeAdapter());
        Gson gson = gsonBuilder.create();

        String json = gson.toJson(artikli);

        out.print(json);

        out.close();


    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        // TODO Auto-generated method stub
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
