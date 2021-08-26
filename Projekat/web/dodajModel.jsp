<%@page import="entity.Model"%>
<%@page import="entity.Marka"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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

    String markaId = request.getParameter("marka");
    String modelNaziv = request.getParameter("model");
    List<Marka> marke = new ArrayList<Marka>();

    marke = em.createNamedQuery("Marka.findAll").getResultList();

    if (modelNaziv != null) {
        et.begin();
        em.createNativeQuery("INSERT INTO model(naziv_modela,marka_id) VALUES(?,?)")
                .setParameter(1, modelNaziv)
                .setParameter(2, markaId)
                .executeUpdate();
        et.commit();
        response.sendRedirect("dodajArtikal.jsp");
    }

%>
<div class="container">
    <div class="row">
        <div class=" col-lg-12" style="margin-top: 150px; background-color: white; border: 5px orange solid;">
            <form method="POST">
                <div class="form-group">
                    <label for="usr">Naziv modela </label> <br>
                    <label> Izaberi marku : </label>
                    <select name="marka" style="margin-bottom: 5px; margin-top: 10px">
                        <option value=""> Izaberite marku</option>
                        <%for (Marka m : marke) {%>
                        <option value="<%out.print(m.getIdMarka());%>"><% out.print(m.getNaziv());%> </option >
                        <%}%> 
                    </select>
                    <input type="text" class="form-control" name="model" placeholder="Unesite naziv modela">
                </div>
                <button type="submit" class="btn btn-primary" name="dodaj"
                        style="margin-left: 45%; margin-top: 10px; margin-bottom: 10px;">Dodaj model
                </button>
            </form>
        </div>
    </div>
</div>