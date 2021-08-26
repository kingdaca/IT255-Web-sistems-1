package servlet;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Users user = new Users();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
            EntityManager em = emf.createEntityManager();
            user = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
            if (username != null || password != null) {
                if (user.getPassword().equals(password)) {
                    HttpSession session = req.getSession();
                    session.setMaxInactiveInterval(90 * 60);
                    session.setAttribute("username", username);
                    session.setAttribute("id", user.getId());
                    session.setAttribute("loggedin", "true");
                    session.setAttribute("role", user.getRole());

                    out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
                    out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                    out.print("<script>");
                    out.print("$(document).ready(function(){");
                    out.print("swal('Dobrodosli!!!','','success');");
                    out.print("})");
                    out.print("</script>");

                    RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                    rd.include(req, resp);
                    emf.close();

                } else {
                    out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
                    out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                    out.print("<script>");
                    out.print("$(document).ready(function(){");
                    out.print("swal('Greska!!!','Pogresna lozinka,pokusajte ponovo!!!','warning');");
                    out.print("})");
                    out.print("</script>");

                    RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                    rd.include(req, resp);
                }
            } else {
            }
        } catch (NoResultException ex) {

            out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
            out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.print("<script>");
            out.print("$(document).ready(function(){");
            out.print("swal('Greska!!!','Korisnik nije pronadjen','error');");
            out.print("})");
            out.print("</script>");

            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.include(req, resp);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
