package servlet;

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

@WebServlet(name = "DodajArtikal", urlPatterns = {"/DodajArtikal"})
public class DodajArtikal extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String model_id = request.getParameter("model");
        String kubikaza = request.getParameter("kubikaza");
        String snaga = request.getParameter("snaga");
        String cena = request.getParameter("cena");
        String opis = request.getParameter("opis");
        String img = request.getParameter("image");

        if (model_id != null || kubikaza != null || snaga != null || cena != null || opis != null || img != null) {

            et.begin();
            em.createNativeQuery("INSERT INTO artikal(kubikaza,snaga,cena,opis,image,model_id) VALUES (?,?,?,?,?,?)")
                    .setParameter(1, kubikaza)
                    .setParameter(2, snaga)
                    .setParameter(3, cena)
                    .setParameter(4, opis)
                    .setParameter(5, img)
                    .setParameter(6, model_id)
                    .executeUpdate();

            out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
            out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.print("<script>");
            out.print("$(document).ready(function(){");
            out.print("swal('Cestitamo!!!','Uspesno ste dodali novi artikal','success');");
            out.print("})");
            out.print("</script>");

          
            RequestDispatcher rd = request.getRequestDispatcher("model.jsp");
            rd.include(request, response);
            response.setHeader("Refresh", "1;URL=model.jsp");
            et.commit();
        } else {
            out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
            out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.print("<script>");
            out.print("$(document).ready(function(){");
            out.print("swal('Greska!!!','Pokusajte ponovo!!!','warning');");
            out.print("})");
            out.print("</script>");

            RequestDispatcher rd = request.getRequestDispatcher("DodajArtikal.jsp");
            rd.include(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respnse) throws ServletException, IOException {

    }

}
