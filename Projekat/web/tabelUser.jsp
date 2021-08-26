
<%@page import="java.util.List"%>
<%@page import="entity.Users"%>
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
    
    List<Users> users = new ArrayList<Users>();
    
    users = em.createNamedQuery("Users.findAll").getResultList();
    
%>
<div class="row">
    <div class="col-lg-12" style="text-align: center; margin-top: 50px; color: white;">
        <h1> Tabela korisnika </h1>
    </div>
</div>
<table class="table table-hover"
       style="color: black; border: 5px orange solid; margin-top: 20px; background-color: white;">
    <tr>
        <th>id</th>
        <th>ime</th>
        <th>prezime</th>
        <th>email</th>
        <th>username</th>
        <th>role</th>
        <th></th>
    </tr>
    <from metod="POST" action="">
        <% for(Users s : users){%>
        <tr>
            <td><% out.print(s.getId());%></td>
            <td><% out.print(s.getIme());%></td>
            <td><% out.print(s.getPrezime());%></td>
            <td><% out.print(s.getEmail());%></td>
            <td><% out.print(s.getUsername());%></td>
            <td><% out.print(s.getRole());%></td>
            <td style="width: 15%">
                <a href='deleteUser.jsp?id=<% out.print(s.getId());%>"' title='Delete Record' data-toggle='tooltip'>
                    <button type="submit" name="delete" class="btn btn-danger"> Obrisi</button>
                </a>
                <a href='updateUser.jsp?id=<% out.print(s.getId());%>' title="Update Record" data-toggle="tooltip">
                    <button type="button" class="btn btn-info">Izmeni</button>
                </a>
            </td>
        </tr>
        <%}%>
    </from>
</table>

