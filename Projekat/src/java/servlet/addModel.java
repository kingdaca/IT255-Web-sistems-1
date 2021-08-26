/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Model;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "addModel", urlPatterns = {"/addModel"})
public class addModel extends HttpServlet {

 
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   try {
            setAccessControlHeaders(response);
            
            PrintWriter out = response.getWriter();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();

            String markaID = request.getParameter("markId");
            String model = request.getParameter("model");
       

            Model m = new Model();

            m.setNazivModela(model);
   

            et.begin();
            em.createNativeQuery("INSERT INTO model(naziv_modela,marka_id) VALUES (?,?)")
                    .setParameter(1, m.getNazivModela())
                    .setParameter(2, markaID)
    
                    .executeUpdate();
            et.commit();
        } catch (Exception e) {
        }
        {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
