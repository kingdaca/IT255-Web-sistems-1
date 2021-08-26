
<%@page import="entity.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entity.Marka"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>



<%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    String markaId = request.getParameter("marka_id");

    List<Model> modeli = new ArrayList<Model>();

    out.print(modeli.size());
    modeli = em.createNativeQuery("SELECT * FROM model WHERE marka_id= " + markaId, Model.class).getResultList();
%>

<option> Select model : </option>
<%for (Model m : modeli) {%>
<option value="<%out.print(m.getIdModel());%>"><%out.print(m.getNazivModela());%></option>
<%}%>