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
        <% 
            Integer classy = (Integer) request.getAttribute("classy");
        %>
        <div class="container" id="cont">
            <svg class="checkmark success" id="svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark_circle_success" cx="26" cy="26" r="25" fill="none"/><path class="checkmark_check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8" stroke-linecap="round"/></svg>
            <h2><%= request.getAttribute("title") %></h2>
            <p><%= request.getAttribute("mensagem") %></p>
            <button id="voltar" onclick="javascript:history.go(-2)" style="display:none;"></button>
            <form name="FEntrada" action="ControleAtividade" method="POST" style="display: none;">
                <input type="number" name="id" value="<%= classy %>">
                <input id="listar" type="submit" name="acao" value="Todos">
            </form>
            <form name="FEntrada" action="ControleTabs" method="POST" style="display: none;">
                <input type="number" name="id" value="<%= classy %>">
                <input id="listarForum" type="submit" name="acao" value="Forum">
            </form>
                <form name="FEntrada" action="ControleTabsUser" method="POST" style="display: none;">
                    <input type="number" name="id_aluno" value="<%= request.getAttribute("aluno") %>" style="display: none;">
                    <input type="number" name="id" value="<%= classy%>">
                    <input id="listarForumUser" type="submit" name="acao" value="Forum">
                </form>
            <form name="FEntrada" action="ControleTabsUser" method="POST" style="display: none;">
                    <input type="number" name="id_aluno" value="<%= request.getAttribute("aluno") %>" style="display: none;">
                    <input type="number" name="id" value="<%= classy%>">
                    <input id="home" type="submit" name="acao" value="Home">
            </form>
            <form name="FEntrada" action="ControleTabs" method="POST" style="display: none;">
                <input type="number" name="id" value="<%= classy %>">
                <input id="listarAluno" type="submit" name="acao" value="Alunos">
            </form>
            <form name="FEntrada" action="ControleTabs" method="POST" style="display: none;">
                <input type="number" name="id" value="<%= classy %>">
                <input id="listarProva" type="submit" name="acao" value="Provas">
            </form>
        </div>
    </body>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://smtpjs.com/v3/smtp.js"></script>
    <script type="text/javascript">
    <% if (request.getAttribute("tipo") == "Cadastrar") { %>
        window.onload = function() {
        setTimeout(function(){ window.location.pathname = "/Classy/adminLogin.html"; }, 4000)
    }
    <% } else if (request.getAttribute("tipo") == "Listar") {%>
        window.onload = function() {
        setTimeout(function(){ $('#voltar').click(); }, 4000)
    }
    <% } else if (request.getAttribute("tipo") == "Atividade") {%>
        window.onload = function() {
        setTimeout(function(){ $('#listar').click(); }, 4000)
    }
    <% } else if (request.getAttribute("tipo") == "Forum") {%>
        window.onload = function() {
        setTimeout(function(){ $('#listarForum').click(); }, 4000)
    }
        <% } else if (request.getAttribute("tipo") == "ForumUser") {%>
        window.onload = function() {
        setTimeout(function(){ $('#listarForumUser').click(); }, 4000)
    }
    <% } else if (request.getAttribute("tipo") == "Prova") {%>
        window.onload = function() {
        setTimeout(function(){ $('#listarProva').click(); }, 4000)
    }
    <% } else if (request.getAttribute("tipo") == "Home") {%>
        window.onload = function() {
        setTimeout(function(){ $('#home').click(); }, 4000)
    }
     <% } else if (request.getAttribute("tipo") == "Aluno") {%>
        window.onload = function() {
        setTimeout(function(){ $('#listarAluno').click(); }, 4000)
    }
    <% } else if (request.getAttribute("tipo") == "Voltar") {%>
        window.onload = function() {
        setTimeout(function(){ history.back(); }, 4000)
    }     
    <% } else if (request.getAttribute("tipo") == "Email") {%>
        window.onload = function() {
        Email.send({
	Host: "smtp.gmail.com",
	Username : "classy.suporte@gmail.com",
	Password : "Classy1234-",
	To : '<%= request.getAttribute("email") %>',
	From : "classy.suporte@gmail.com",
	Subject : "Classy - Recuperação de Senha",
	Body : "<h2 style='color:#56cad8'>Olá! Ficamos sabendo que você esqueceu sua senha :( </h2><p style='color:#3B3941;font-weight:600;'>Não se preocupe! Use esta nova que te enviamos para acessar o portal!</p><h1 style='color:#56cad8'><%= request.getAttribute("senha") %></h1><p style='color:#3B3941;font-weight:600;'>Com amor, time Classy <3</p>",
	})
        setTimeout(function(){ window.location.pathname = '/Classy/adminLogin.html'; }, 4000)
    }
    <% }%>
    </script>
</html>