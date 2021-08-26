/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author David
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String ime = req.getParameter("ime");
        String prezime = req.getParameter("prezime");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        String adresa = req.getParameter("adresa");

        Users user = new Users();

        user.setIme(ime);
        user.setPassword(pass);
        user.setPrezime(prezime);
        user.setEmail(email);
        user.setUsername(username);
        user.setAdresa(adresa);

        if (ime != null || prezime != null || adresa != null || email != null || username != null
                || pass != null || repass != null) {
            if (pass.equals(repass) == true) {
                et.begin();
                em.createNativeQuery("INSERT INTO users(ime,prezime,adresa,email,username,password) VALUES (?,?,?,?,?,?)")
                        .setParameter(1, user.getIme())
                        .setParameter(2, user.getPrezime())
                        .setParameter(3, user.getAdresa())
                        .setParameter(4, user.getEmail())
                        .setParameter(5, user.getUsername())
                        .setParameter(6, user.getPassword())
                        .executeUpdate();
                et.commit();
                out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
                out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                out.print("<script>");
                out.print("$(document).ready(function(){");
                out.print("swal('Dobrodosli!!!','Uspesno ste se registrovali','success');");
                out.print("})");
                out.print("</script>");

                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.include(req, resp);
                resp.setHeader("Refresh", "1;URL=login.jsp");
            } else {
                out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
                out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                out.print("<script>");
                out.print("$(document).ready(function(){");
                out.print("swal('Greska!!!','Lozinke se ne poklapaju!!!','warning');");
                out.print("})");
                out.print("</script>");

                RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
                rd.include(req, resp);

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
