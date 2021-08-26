<%@page import="entity.Marka"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
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

    String marka = request.getParameter("naziv");

    Marka m = new Marka();

    m.setNaziv(marka);

    if (marka != null) {
        et.begin();
        em.createNativeQuery("INSERT INTO marka(naziv) VALUE(?)")
                .setParameter(1, m.getNaziv())
                .executeUpdate();
        response.sendRedirect("dodajModel1.jsp?markaId=" + marka);
        et.commit();

    }

%>

<div class="container">
    <div class="row">
        <div class=" col-lg-12" style="margin-top: 150px; background-color: white; border: 5px orange solid;">
            <form method="post">
                <div class="form-group">
                    <label for="usr">Naziv marke : </label>
                    <input type="text" class="form-control" name="naziv">
                </div>
                <button type="submit" class="btn btn-primary"
                        style="margin-left: 45%; margin-top: 10px; margin-bottom: 10px;">Dodaj marku
                </button>
            </form>
        </div>
    </div>
</div>