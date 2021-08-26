
<%@page import="entity.Narudzbenica"%>
<%@page import="entity.Artikal"%>
<%@page import="java.util.List"%>
<%@page import="entity.Users"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@include file="header.jsp" %>


<%
    
    try{
    String role = (String) session.getAttribute("role");
    if (role.equals("admin") == false || loggedin.equals("true") == false) {
        response.sendRedirect("index.jsp");
    }
    }catch(Exception ex){
        
    } 
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    List<Narudzbenica> order = new ArrayList<Narudzbenica>();

    order = em.createNamedQuery("Narudzbenica.findAll").getResultList();

%>
<div class="row">
    <div class="col-lg-12" style="text-align: center; margin-top: 50px; color: white;">
        <h1> Tabela narudzbenica </h1>
    </div>
</div>
<table class="table table-hover"
       style="color: black; border: 5px orange solid; margin-top: 20px; background-color: white;">
    <tr>
        <th>Id </th>
        <th>id User</th>
        <th>ime</th>
        <th>prezime</th>
        <th>Adresa</th>
        <th>email</th>
        <th>username</th>
        <th>Marka</th>
        <th>Model</th>
        <th>Cena</th>
        <th></th>
    </tr>  
    <from metod="POST" action="">
        <% for (Narudzbenica n : order) {%>
        <tr>
            <td><% out.print(n.getId());%></td>
            <td><% out.print(n.getIdUser().getId());%></td>
            <td><% out.print(n.getIdUser().getIme());%></td>
            <td><% out.print(n.getIdUser().getPrezime());%></td>
            <td><% out.print(n.getIdUser().getAdresa());%></td>
            <td><% out.print(n.getIdUser().getEmail());%></td>
            <td><% out.print(n.getIdUser().getUsername());%></td>
            <td><% out.print(n.getIdArtikla().getModelId().getMarkaId().getNaziv());%></td>
            <td><% out.print(n.getIdArtikla().getModelId().getNazivModela());%></td>
            <td><% out.print(n.getIdArtikla().getCena());%></td>
            <td style="width: 15%">
                <a href='deleteOrder.jsp?id=<% out.print(n.getId());%>"' title='Delete Record' data-toggle='tooltip'>
                    <button type="submit" name="delete" class="btn btn-danger"> Obrisi</button>
                </a>
            </td>
        </tr>
        <%}%>
    </from>
</table>

