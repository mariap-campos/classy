<%-- 
    Document   : success
    Created on : 31/03/2021, 20:34:12
    Author     : Maria Paula
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy.</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/home.css">
        <link rel="stylesheet" href="src/styles/success.css?v=3">

    </head>
    <body>
        <div class="container" id="cont">
            <svg class="checkmark success" id="svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark_circle_success" cx="26" cy="26" r="25" fill="none"/><path class="checkmark_check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8" stroke-linecap="round"/></svg>
            <h2><%= request.getAttribute("title") %></h2>
            <p><%= request.getAttribute("mensagem") %></p>
        </div>
    </body>
    <script type="text/javascript">
        <% if (request.getAttribute("tipo") == "Cadastrar") { %>
        window.onload = function() {
        setTimeout(function(){ window.location.pathname = "/Classy/adminLogin.html"; }, 3000)
    }
    <% } else if (request.getAttribute("tipo") == "Listar") {%>
        window.onload = function() {
        setTimeout(function(){ history.back(-2) }, 3000)
    }
    <% } else if (request.getAttribute("tipo") == "Voltar") {%>
        window.onload = function() {
        setTimeout(function(){ history.back(); }, 3000)
    }
    <% }%>
    </script>
</html>