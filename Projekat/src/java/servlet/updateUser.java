/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Artikal;
import entity.Users;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
@WebServlet(name = "updateUser", urlPatterns = {"/updateUser"})
public class updateUser extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String role = request.getParameter("role");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.createNativeQuery("UPDATE users SET ime = ?, prezime = ?, role = ? WHERE id = ?")
                .setParameter(1, ime)
                .setParameter(2,prezime)
                .setParameter(3, role)
                .setParameter(4, Integer.parseInt(id))
                .executeUpdate();
        et.commit();
        
        HttpSession session = request.getSession();

        out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
        out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
        out.print("<script>");
        out.print("$(document).ready(function(){");
        out.print("swal('Uspesno!!!','Uspesno ste izvrsili izmenu korisnika','success');");
        out.print("})");
        out.print("</script>");

        RequestDispatcher rd = request.getRequestDispatcher("tabelUser.jsp");
        rd.include(request, response);
        response.setHeader("Refresh", "5;URL=tabelUser.jsp");
        emf.close();
        
       
    }
    
    

}
