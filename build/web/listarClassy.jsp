<%-- 
    Document   : classyList
    Created on : 01/04/2021, 19:32:23
    Author     : Maria Paula
--%>

<%@page import="modelo.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy.</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css?v=1">
        <link rel="stylesheet" href="src/styles/signup.css?v=2">
        <link rel="stylesheet" href="src/styles/adminClassys.css?v=1">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            ArrayList<Classy> listaClassy = (ArrayList<Classy>) request.getAttribute("classys");
            Admin admin = (Admin) request.getAttribute("admin");
        %>
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <h2>Classy.</h2>
                            <h3>Bem vindo, <%= admin.getNome() %>!</h3>
                            <p>Estamos felizes em te ver.</p>
                        </div>
                        <a href='/Classy/' class="register">Sair</a>
                    </div>
                </header>
                <div class="content">
                    <h2>Aqui estão todos os <span>Classys</span> que você administra</h2>
                    <p>Entre em um para começar.</p>
                    <ul class="classy-items">
                        <%
                            for (Classy c : listaClassy){
                        %>
                        <form name="adminHome" accept-charset="ISO-8859-1" name="FEntrada" action="ControleClassy" method="POST" >
                        <li class="classy-item">
                            <div class="box">
                                <div class="info-box">
                                    <h3><%= c.getNome() %></h3>
                                    <p><%= c.getNome_instituicao() %></p>
                                </div>
                            </div>
                            <div class="icons-form">
                                <input type="number" name="id" value="<%= c.getToken() %>" style="display: none;">
                                <button class="actions" type="submit" name="acao" value="Apagar"><img class="icon" width="20" src="src/icons/trash.svg" alt="coração"></button>
                                <button class="actions" type="submit" name="acao" value="abrirAtualizar"><img class="icon" width="20" src="src/icons/pencil.svg" alt="coração"></button>
                            </div>
                                <input class="entrar" type="submit" name="acao" value="Entrar">
                            </li>
                        </form>
                    <%
                        }
                    %>
                    <form name="adminHome" name="FEntrada" action="ControleClassy">
                        <input type="number" name="id" value="<%= admin.getId() %>" style="display: none;">
                        <input class="new-classy" type="submit" name="acao" value="Novo Classy +">
                    </form>
                        
                    </ul>
                </div>
                <footer>
                    <p>O que é o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='/'>Termos de Uso</a></p>
                </footer>
        </div>
        <script src="" async defer></script>
    </body>
</html>
