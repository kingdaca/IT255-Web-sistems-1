<%@include file="header.jsp" %> 

<%
  
    
if(loggedin != null){
  response.sendRedirect("index.jsp");
}

%>

<div class="container">
    <div class="col-lg-4"></div>
    <div id="reg" class="col-lg-4">
        <form  action="Register" method="POST" role="form" style="margin-top: 150px; border: 5px orange solid; background-color: white"
               <table class="table table-hover">
                <tr>
                    <td><h3 style="text-align: center; margin: 10px 0px; color: black" > Registruj se </h3></td>
                </tr>

                <div class="form-group">
                    <table class="table table-hover" >
                        <tr>
                            <td> Ime :</td>
                            <td><input type="text" name="ime" placeholder="Ime" required ></td>
                        </tr>
                        <td> Prezime :</td>
                        <td><input type="text" name="prezime" placeholder="Prezime" required </td>
                        </tr>
                         <td> Adresa :</td>
                        <td><input type="text" name="adresa" placeholder="Adresa" required </td>
                        </tr>
                        <tr>
                        <tr>
                            <td> Email :</td>
                            <td><input type="email" name="email" placeholder="Email" required </td>
                        </tr>
                        <tr>
                            <td> Korisnicko ime :</td>
                            <td><input type="text" name="username" placeholder="Korisnko ime" required> </td> 
                        </tr>
                        <tr>
                            <td> Sifra :</td>
                            <td><input type="password" name="pass" placeholder="Sifra" required> </td>     
                        </tr>
                        <tr>
                            <td> Ponovi Sifru :</td>
                            <td><input type="password" name="repass" placeholder="Ponevi sifru" required ></td>
                        </tr>

                    </table>
            </table>

            <button type="submit" class="btn btn-primary" name="reg" style="margin-left: 120px;">Registruj se</button>

    </div>

</div>

</form>
<div class="col-lg-4"></div>
</div>

