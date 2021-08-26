<%@page import="entity.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entity.Marka"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@include file="header.jsp" %>    

<%
    String role = (String) session.getAttribute("role");
    if(role.equals("admin") == false || loggedin.equals("true") == false){
        response.sendRedirect("index.jsp");
    }
    
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekat");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();

    String markaId = request.getParameter("marka");
    String modelNaziv = request.getParameter("model");
    List<Marka> marke = new ArrayList<Marka>();

    marke = em.createNamedQuery("Marka.findAll",Marka.class).getResultList();

%>

                
    <div class="container">
        <div class="row">
            <div class="col-lg-12"
                 style="color:black; background-color: white; border: 5px orange solid; margin-top: 150px;">
               <label> Marka : </label>
                <select id="marka" name="marka" style="margin-bottom: 5px; margin-top: 10px; ">
                    <option value=""> Izaberite marku</option>
                    <%for (Marka m : marke) {%>
                    <option value="<%out.print(m.getIdMarka());%>"><% out.print(m.getNaziv());%> </option>    
                    <%}%>
                </select>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
                <!-- Value of marka -->
                <script type="text/javascript">
                    $(function () {
                        $('#marka').change(function (e) {
                            var marka_id = $(this).val();
                            $.ajax({
                                type: 'GET',
                                url: 'funkcije.jsp',
                                data: {marka_id: marka_id},
                                success: function (data) {
                                    $('#model').html(data);
                                }
                            });
                            console.log(marka_id);
                        });
                    });

                </script>

                <form action="DodajArtikal" method="POST" role="form">
                    <label for="model"> Model : </label>
                    <select id="model" style="margin-bottom: 5px;" name="model">
                        <option> Izaberite model</option>
                    </select>
                    <br>
                    <label for="kubikaza"> Kubikaza : </label>
                    <input id="kubikaza" type="text" class="form-control" name="kubikaza" placeholder="Kubikaza"
                           required>
                    <label for="konjaza"> Snaga u KW : </label>
                    <input id="snaga" type="text" class="form-control" name="snaga" placeholder="Sanaga u KW"
                           required>

                    <label for="cena"> Cena : </label>
                    <input id="cena" type="text" class="form-control" name="cena" placeholder="Cena" required>
                    <label for="opis"> Opis : </label>
                    <textarea id="opis" class="form-control" rows="5" id="comment" name="opis" placeholder="Opis"
                              required></textarea>

                    <label for="image"> Url slike : </label>
                    <input id="image" type="text" class="form-control" name="image" placeholder="Url slike" required>

                    <button type="submit" class="btn btn-primary" name="upisi" style="margin-left: 45%;">Dodaj model
                    </button>

                </form>

            </div>
        </div>
    </div>