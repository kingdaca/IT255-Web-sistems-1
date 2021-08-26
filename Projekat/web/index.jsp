<%@include file="header.jsp" %>

<html>
    <head>
        <link rel="stylesheet" href="style/slider.css"
    </head>
</html>
<!-- Slider -->
<header>
    <div class="slideshow-container">

        <div class="mySlides fade">
            <div class="numbertext">1 / 3</div>
            <img src="picture/p1.jpg" style="width:100%">
            <div class="text"></div>
        </div>
        <div class="mySlides fade">
            <div class="numbertext">2 / 3</div>
            <img src="picture/p2.jpg" style="width:100%">
            <div class="text"></div>
        </div>
        <div class="mySlides fade">
            <div class="numbertext">3 / 3</div>
            <img src="picture/p3.jpg" style="width:100%">
            <div class="text"></div>
        </div>
        <div class="mySlides fade">
            <div class="numbertext">4 / 4</div>
            <img src="picture/p4.jpg" style="width:100%">
            <div class="text"></div>
        </div>
    </div>
    <br>
    <div id="dot"  style="text-align:center">
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
    </div>
</header>
<script>
    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("dot");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {
            slideIndex = 1
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
        setTimeout(showSlides, 1500); // Change image every 2 seconds
    }
</script>

<!-- Modeli -->
<section id="servisi" class="bg-light-gray" >
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1> Svi modeli </h1>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-12" id="slika">
                <img src="https://www.bmw.rs/content/dam/bmw/common/home/teaser/bmw-2-series-gran-coupe-inspire-ag-home-teaser-xxl.jpg" height="500px">
            </div>

        </div>
        <div class="row" style="padding: 20px;">
            <div class="col-lg-4"></div>
            <div id="modelistyle" class="col-lg-4" style="margin-top: 10px; text-align: center" >
                <a href="model.jsp"><button type="button" class="btn btn-mg">Modeli</button></a>
            </div>
            <div class="col-lg-4"></div>
        </div>
    </div>
</section>


<!-- Partneri -->
<section  style="background: #262626;">
    <div class="container">
        <div class="row" style="text-align: center; color: white; margin: 2%">
            <h1>Kupovina/Osiguranje</h1>
        </div>
        <div id="partneri" class="row" style="margin: 3%">
            <div class="col-lg-6" >
                <a href="https://www.unicreditbank.rs/rs/leasing.html#Vozila" target="_blank"><img src="picture/un.png" style="margin-left: 10%; margin-top: 30px"></a>
            </div>
            <div class="col-lg-6">
                <a href="http://www.dunavauto.rs/" target="_blank"><img src="picture/dn.jpg" style="margin-left: 25%;"></a>
            </div>
        </div>
        <div class="row">

        </div>
    </div>
</section>