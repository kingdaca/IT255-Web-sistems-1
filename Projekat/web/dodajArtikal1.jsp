<%@page import="entity.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entity.Marka"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@include file="header.jsp" %>    

<%
    String role = (String) session.getAttribute("role");
    if (role.equals("admin") == false || loggedin.equals("true") == false) {
        response.sendRedirect("index.jsp");
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    String markaId = request.getParameter("markaId");
    String modelNaziv = request.getParameter("nazivModela");

    int idModel = 0;

    idModel = (Integer) em.createNativeQuery("SELECT `id_model` FROM `model` WHERE naziv_modela = '" + modelNaziv + "'").getSingleResult();


  

%>

<div class="container">
    <div class="row">
        <div class="col-lg-12"
             style="color:black; background-color: white; border: 5px orange solid; margin-top: 150px;">

            <form action="DodajArtikal" method="POST" role="form">
                <input id="model" type="hidden" class="form-control" name="model" value="<%out.print(idModel);%>"/>
                <label for="kubikaza"> Kubikaza : </label>
                <input id="kubikaza" type="text" class="form-control" name="kubikaza" placeholder="Kubikaza"
                       required>
                <label for="konjaza"> Snaga u KW : </label>
                <input id="snaga" type="text" class="form-control" name="snaga" placeholder="Sanaga u KW"
                       required>

                <label for="cena"> Cena : </label>
                <input id="cena" type="text" class="form-control" name="cena" placeholder="Cena" required>
                <label for="opis"> Opis : </label>
                <textarea id="opis" class="form-control" rows="5" id="comment" name="opis" placeholder="Opis"
                          required></textarea>

                <label for="image"> Url slike : </label>
                <input id="image" type="text" class="form-control" name="image" placeholder="Url slike" required>

                <button type="submit" class="btn btn-primary" name="upisi" style="margin-left: 45%;">Dodaj model
                </button>

            </form>

        </div>
    </div>
</div>