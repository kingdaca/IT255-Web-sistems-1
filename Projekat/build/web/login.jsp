<%
    if(session.getAttribute("loggedin")!= null){
    response.sendRedirect("index.jsp");
}

%>
<body>
<%@include file="header.jsp" %>
<div class="container" >
    <div class="col-md-4"></div>
    <div class="col-md-4" style="margin-top: 150px; border: 5px orange solid; background-color: white">
        <form action="Login" method="POST" role="form">
        <table class="table table-hover">
            <tr>
                <tr> <h3 style="text-align: center; margin: 10px 0px"> Prijavi se </h3> </tr>
            </tr>
            <div class="form-group">
                <table class="table table-hover">
                    <tr >
                        <td> Korisnicko ime : </td><td> <input type="text" name="username" placeholder="Korisnicko ime"></td>
                      
                    </tr>
                    <tr >
                        <td> Sifra : </td><td> <input type="password" name="password" placeholder="Sifra"></td>
                       
                    </tr>
                </table>
        </table>
            <button type="submit" class="btn btn-primary" style="margin-left: 150px">Prijavi se</button>
        </form>
        <div class="col-md-4"> </div>
    </div>
</div>
</body>