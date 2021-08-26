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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            setAccessControlHeaders(resp);
            
            PrintWriter out = resp.getWriter();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();

            String ime = req.getParameter("ime");
            String prezime = req.getParameter("prezime");
            String email = req.getParameter("email");
            String username = req.getParameter("username");
            String pass = req.getParameter("pass");
            String adresa = req.getParameter("adresa");
            String phone = req.getParameter("phone");

            Users user = new Users();

            user.setIme(ime);
            user.setPassword(pass);
            user.setPrezime(prezime);
            user.setEmail(email);
            user.setPassword(pass);
            user.setUsername(username);
            user.setAdresa(adresa);
            user.setPhone(phone);

            et.begin();
            em.createNativeQuery("INSERT INTO users(ime,prezime,adresa,email,username,password,phone) VALUES (?,?,?,?,?,?,?)")
                    .setParameter(1, user.getIme())
                    .setParameter(2, user.getPrezime())
                    .setParameter(3, user.getAdresa())
                    .setParameter(4, user.getEmail())
                    .setParameter(5, user.getUsername())
                    .setParameter(6, user.getPassword())
                    .setParameter(7, user.getPhone())
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

}
