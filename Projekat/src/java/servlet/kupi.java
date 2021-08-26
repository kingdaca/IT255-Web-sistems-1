/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Artikal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
@WebServlet(name = "kupi", urlPatterns = {"/kupi"})
public class kupi extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        List<Artikal> korpa = (List<Artikal>) session.getAttribute("korpa");

        int id_user = (int) session.getAttribute("id");
        et.begin();
        for (Artikal ar : korpa) {

            em.createNativeQuery("INSERT INTO narudzbenica (id_user,id_artikla) VALUES(?,?)")
                    .setParameter(1, id_user)
                    .setParameter(2, ar.getId())
                    .executeUpdate();
        }
        et.commit();

        out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
        out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
        out.print("<script>");
        out.print("$(document).ready(function(){");
        out.print("swal('Cestitamo!!!','Uspesno ste narucili proizvod','success');");
        out.print("})");
        out.print("</script>");

        RequestDispatcher rd = request.getRequestDispatcher("model.jsp");
        rd.include(request, response);
        session.removeAttribute("korpa");
        response.setHeader("Refresh", "2;URL=model.jsp");
        

    }

}
