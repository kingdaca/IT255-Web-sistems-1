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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author David
 */
@WebServlet(name = "deleteOrder", urlPatterns = {"/deleteOrder"})
public class deleteOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String id = request.getParameter("id");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.createNativeQuery("DELETE FROM narudzbenica WHERE id = ?").setParameter(1, Integer.parseInt(id)).executeUpdate();

            out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
            out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.print("<script>");
            out.print("$(document).ready(function(){");
            out.print("swal('Uspesno!!!','Uspesno ste obrisali narudzbenicu','success');");
            out.print("})");
            out.print("</script>");

            RequestDispatcher rd = request.getRequestDispatcher("tabelNarudzbenica.jsp");
            rd.include(request, response);
            response.setHeader("Refresh", "1;URL=tabelNarudzbenica.jsp");
            emf.close();

        }catch(NoResultException ex){
            
        }
    }

}
