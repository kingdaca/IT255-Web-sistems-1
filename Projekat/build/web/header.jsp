

<!doctype html>
<html lang="">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


        <link rel="stylesheet" href="style/style.css">
        <title>Document</title>
    </head>
    <body>
        <div>
            <nav  class="navbar navbar-inverse navbar-fixed-top">
                <div class="container-fluid">
                    <ul  class="nav navbar-nav">
                        <li role="presentation">
                            <a href="index.jsp"> Pocetna </a>
                        </li>

                        <li role="presentation">
                            <a href="model.jsp"> Modeli </a>
                        </li>
                        <%
                            try {
                                String role = (String) session.getAttribute("role");
                                if (role.equals("admin") == true) {

                        %>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Dodaj
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="dodajMarku.jsp">Dodaj Marku</a></li>
                                <li><a href="dodajModel.jsp">Dodaj Model</a></li>
                                <li><a href="dodajArtikal.jsp">Dodaj Artikal</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Dashboard
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="tabelUser.jsp">Tabela users</a></li>
                                <li><a href="tabelNarudzbenica.jsp">Tabela narudzbenica</a></li>
                            </ul>
                        </li>
                        <%}
                            } catch (NullPointerException ex) {

                            }%>
                    </ul>
                    <ul class="nav navbar-nav navbar-right"> 
                        <%  String loggedin = (String) session.getAttribute("loggedin");
                            if (loggedin == null) {%>
                        <li> <a href="Register.jsp"> <span class="glyphicon glyphicon-user"> Registruj se</span></a></li>
                        <li>  <a href="login.jsp"> <span class="glyphicon glyphicon-log-in"> Login </span> </a></li>

                        <%} else {%>
                        <li>
                            <a>  <%=session.getAttribute("username")%> <span class="glyphicon glyphicon-user"></span></a>
                        </li> 
                        <li>
                            <a href="logout.jsp" >Log out <span class="glyphicon glyphicon-log-out"></span></a>
                                <% }%>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>