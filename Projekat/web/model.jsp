<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
"{+:
<\%@page import="servlet.korpa"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Artikal"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@include file="header.jsp" %>

<%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    List<Artikal> artikli = new ArrayList<Artikal>();
    et.begin();
    artikli = em.createNativeQuery("SELECT * FROM artikal JOIN model ON artikal.model_id = model.id_model JOIN marka ON marka.id_marka = model.marka_id", Artikal.class)
            .getResultList();
    et.commit();
%>

<style>


    .modeli{
        border: 5px orange solid;
        background: white;
        color: #333333;
        margin-bottom: 2%;
        font-weight: 900;
        background-color: lightgray;
    }

    .modeli input{
        background-color: #141414;
        color: #9d9d9d;
        border: 2px orange solid;
    }

    .modeli input:hover{
        background-color: #141414;
        color: orange;
    }

    .image {
        display: block;
        width: 100%;
        height: 185px;
        margin-left: -16px;
        margin-right: -5000px;
    }

    .table{
        color: #333333;
        background-color: white;
        font-weight: bold;
        border: 5px orange solid;
    }

    .table tbody{
        border: 3px orange solid;
    }

    .table tbody button{
        border: 0px;
        background-color: white;
    }
</style>



<%    Integer ukupnaCena = 0;
    try {
        if (session.getAttribute("korpa") != null) {
            List<Artikal> korpa = (List<Artikal>) session.getAttribute("korpa");
%>
<form action="kupi" method="post">
    <div class="container">
        <div class="row">
            <h3 style="text-align: center; color: white; margin-top: 5%;"> Korpa </h3>
            <table class="table table-hover">


                <thead>
                    <tr>
                        <th>Marka</th>
                        <th>Model</th>
                        <th> Cena</th>
                        <th> Obrisi </th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        for (Artikal ar : korpa) {%>
                    <tr>
                <input type="hidden" name="id" value="<%out.print(ar.getId());%>">
                <td><%out.print(ar.getModelId().getMarkaId().getNaziv());%></td>
                <td><%out.print(ar.getModelId().getNazivModela());%></td>
                <td><%out.print(ar.getCena());%> $</td>

                <th> <a href='deleteKorpa.jsp?id=<%out.print(ar.getId());%>'><span class="text-danger"> Obrisi </span></a></th>
                    <% ukupnaCena += Integer.parseInt(ar.getCena());%>
                </tr>

                <%}%>

                </tbody>
            </table>
            <button type="submit" name="kupi" class="btn btn-success" style="margin-left: 85%;">Kupi</button>
            <h3 style="color:white; margin-left: 78%;"> Ukupna cena : <%out.print(ukupnaCena);%></h3>
        </div>
    </div>
</form>
<%
        }
    } catch (Exception ex) {

    }
%>

<%    String role = (String) session.getAttribute("role");

    try {
        if (role.equals("admin") == true) {

%>
<div id="dodajmodel" class="container" style="margin-top: 50px; color: white; text-align: center">
    <div class="row">
        <div class=col-lg-12">
            <h1> Dodaj Novi artikal</h1>
        </div>
    </div>
    <div class="row">
        <div class="row" style="padding: 20px;">
            <div class="col-lg-4"></div>
            <div id="modelistyle" class="col-lg-4" style="margin-top: 10px; text-align: center">
                <a href="dodajArtikal.jsp">
                    <button type="button" class="btn btn-mg">Dodaj novi artikal</button>
                </a>
            </div>
            <div class="col-lg-4"></div>
        </div>
    </div>

    <%}
        } catch (Exception ex) {

        }
    %>


    <!-- Modeli -->
</div>
<h2 style="text-align: center; color: white; margin-top: 100px;">Ponuda modela</h2>
<div id="model" class="container" style="margin-top: 100px; margin-bottom: 5%;">

    <%for (Artikal a : artikli) {%>
    <form method="POST" action="korpa">
        <div  class="modeli row">
            <div class="col-lg-4" style="margin-right: -31px;">
                <img src="<%out.print(a.getImage());%>" class="image">
                <div class="overlay">
                    <div class="text"></div>
                </div>
            </div>
            <div class="col-lg-8" style="background-color: lightgray;">
                <div class="col-lg-4">
                    <h5 > Marka : <% out.print(a.getModelId().getMarkaId().getNaziv());%></h5>
                    <h5 value="<%out.print(a.getModelId());%>"> Model : <%out.print(a.getModelId().getNazivModela());%></h5>
                    <h5 value="<%out.print(a.getKubikaza());%>">  Kubikaza : <%out.print(a.getKubikaza());%> cm3</h5>
                    <h5 value="<%out.print(a.getSnaga());%>">  Sanga : <%out.print(a.getSnaga());%> KW</h5>
                    <h5 value="<%out.print(a.getCena());%>">  Cena : <%out.print(a.getCena());%> $</h5>
                    <input type="hidden" name="naziv" value="<%a.getModelId().getMarkaId().getNaziv();%>">
                    <input type="hidden" name="naziv_modela" value="<%a.getModelId().getNazivModela();%>">
                    <input type="hidden" name="kubikaza" value="<%a.getKubikaza();%>">
                    <input type="hidden" name="cena" value="<%a.getCena();%>">
                    <input type="hidden" name="idArtikla" value="<%out.print(a.getId());%>">
                </div>
                <div class="col-lg-8">
                    <h5 >  Opis : <%out.print(a.getOpis());%></h5>
                    <%
                        try {
                            if (session.getAttribute("loggedin") != null) {
                    %>
                    <input name="dodaj" value="Dodaj u korpu" type="submit" class="btn btn-primary" style="margin-left: 40%; margin-bottom: 10px;">
                    <%    } else {%>
                    <div class="alert alert-warning" role="alert">
                        Morate biti logovani da bi kupili proizvod
                    </div>
                    <%}%>
                    <%
                        } catch (Exception ex) {

                        }%>
                </div>
            </div>
        </div>
    </form>
    <%
        }
    %>
