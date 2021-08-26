/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Artikal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
@WebServlet(name = "korpa", urlPatterns = {"/korpa"})
public class korpa extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        HttpSession sessionArr = request.getSession();
        List<Artikal> korpa = new ArrayList<>();

        String idArtikla = request.getParameter("idArtikla");
        String naziv = request.getParameter("naziv");
        String naziv_modela = request.getParameter("naziv_modela");
        String cena = request.getParameter("cena");

        Artikal a;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        a =  (Artikal) em.createNamedQuery("Artikal.findById").setParameter("id", Integer.parseInt(idArtikla)).getSingleResult();
        if (sessionArr.getAttribute("korpa") == null) {
            korpa.add(a);
            sessionArr.setAttribute("korpa", korpa);
        } else {
            korpa = (List<Artikal>) sessionArr.getAttribute("korpa");
            korpa.add(a);
            sessionArr.setAttribute("korpa", korpa);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("model.jsp");
        rd.forward(request, response);
       response.sendRedirect("model.jsp");
        

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
