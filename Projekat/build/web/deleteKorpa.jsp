<%@page import="java.util.List"%>
<%@page import="entity.Artikal"%>
<%
    
    try {
        String id = request.getParameter("id");
        
        List<Artikal> korpa = (List<Artikal>) session.getAttribute("korpa");
        for (Artikal ar : korpa) {
            if (ar.getId() == Integer.parseInt(id)) {
                korpa.remove(ar);
            }
            
            session.setAttribute("korpa", korpa);
            
            if (korpa.isEmpty() == true) {
                session.removeAttribute("korpa");
            }
            response.sendRedirect("model.jsp");
        }
       
    } catch (Exception ex) {
    }
%>
