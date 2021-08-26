<%
if(session.getAttribute("loggedin").equals("true")){
    session.invalidate();
    response.sendRedirect("login.jsp");
}


%>