/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
@WebServlet(name = "buy", urlPatterns = {"/buy"})
public class buy extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();

            PrintWriter out = response.getWriter();

            String idUser = request.getParameter("idUser");
            String idArticle = request.getParameter("idArticle");

            et.begin();
            em.createNativeQuery("INSERT INTO narudzbenica (id_user,id_artikla) VALUES(?,?)")
                    .setParameter(1, idUser)
                    .setParameter(2, idArticle)
                    .executeUpdate();
            et.commit();
        } catch (NoResultException e) {
        }
     
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
