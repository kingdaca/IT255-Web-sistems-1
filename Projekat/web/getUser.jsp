<%-- 
    Document   : getUser
    Created on : Apr 14, 2021, 4:47:01 PM
    Author     : david
--%>

<%@page import="javax.persistence.NoResultException"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="servlet.MyAdapterUsers"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="entity.Users"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.io.PrintWriter"%>
<%

    try {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        String username = request.getParameter("username");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        Users user = new Users();

        et.begin();
        user = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
        et.commit();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.disableHtmlEscaping();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.serializeNulls();
        gsonBuilder.registerTypeAdapter(Users.class, new MyAdapterUsers());
        Gson gson = gsonBuilder.create();

        String json = gson.toJson(user);

        out.print(json);

        out.close();
    } catch (NoResultException ex) {

    }

%>
