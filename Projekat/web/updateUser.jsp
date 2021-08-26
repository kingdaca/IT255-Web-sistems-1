
<%@page import="entity.Users"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@include file="header.jsp" %>
<%
    String role = (String) session.getAttribute("role");
    try {
        if (role.equals("admin") == false || loggedin.equals("true") == false) {
            response.sendRedirect("index.jsp");
        }
    } catch (Exception ex) {
        response.sendRedirect("login.jsp");
    }
    String id = request.getParameter("id");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    Users u = new Users();

    et.begin();

    u = (Users) em.createNamedQuery("Users.findById").setParameter("id", Integer.parseInt(id)).getSingleResult();
    et.commit();

%>

<body style="margin-top: 10%; color: white;">
    <div class="wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12" style="border: 5px orange solid; background-color: white; color:black;">
                    <div class="page-header">
                        <h2>Izmeni korisnika</h2>
                    </div>
                    <p>Unesite izmene</p>
                    <form  style="margin-bottom: 5%;"
                           action="updateUser" method="post">
                        <div class="">
                            <input type="hidden" name="id" value="<%out.print(id);%>"  class="form-control"> 
                            <label>Ime</label>
                            <input type="text" name="ime" value="<%out.print(u.getIme());%>"  class="form-control"> 

                        </div>
                        <div class="form-group">
                            <label>Prezime</label>
                            <input name="prezime" class="form-control" value="<%out.print(u.getPrezime());%>" >
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label>Role</label>
                            <input type="text" name="role" class="form-control" value="<%out.print(u.getRole());%>">
                            <span class="help-block"></span>
                        </div>
                        <input type="hidden" name="id" value=""/>
                        <input type="submit" class="btn btn-primary" value="Izmeni">
                        <buttom class="btn btn-warning"><a href="tabelUser.jsp">Odustani</a></buttom>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

\