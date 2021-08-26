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
@WebServlet(name = "AddArticle", urlPatterns = {"/AddArticle"})
public class AddArticle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();

            String model_id = request.getParameter("model");
            String kubikaza = request.getParameter("cubic");
            String snaga = request.getParameter("power");
            String cena = request.getParameter("price");
            String opis = request.getParameter("desc");
            String img = request.getParameter("imgPath");
            String user = request.getParameter("userID");

            et.begin();
            em.createNativeQuery("INSERT INTO artikal(kubikaza,snaga,cena,opis,image,model_id,id_user) VALUES (?,?,?,?,?,?,?)")
                    .setParameter(1, kubikaza)
                    .setParameter(2, snaga)
                    .setParameter(3, cena)
                    .setParameter(4, opis)
                    .setParameter(5, img)
                    .setParameter(6, model_id)
                    .setParameter(7, user)
                    .executeUpdate();
        } catch (NoResultException e) {
        }
        {

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
