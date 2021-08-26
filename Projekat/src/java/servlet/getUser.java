/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
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
@WebServlet(name = "getUser", urlPatterns = {"/getUser"})
public class getUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            setAccessControlHeaders(response);
            PrintWriter out = response.getWriter();

            String username = request.getParameter("username");
            String pass = request.getParameter("password");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();
            Users user = new Users();

            et.begin();
            user = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
            if (user.getPassword().equals(pass) == true && user.getUsername().equals(username) == true) {
                et.commit();

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
                gsonBuilder.disableHtmlEscaping();
                gsonBuilder.excludeFieldsWithoutExposeAnnotation();
                gsonBuilder.serializeNulls();
                gsonBuilder.registerTypeAdapter(Users.class, new MyAdapterUsers());
                Gson gson = gsonBuilder.create();

                String json = gson.toJson(user);

                out.print(json);

                out.close();
            }
        } catch (NoResultException ex) {

        }
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
