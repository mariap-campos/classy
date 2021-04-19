<%-- 
    Document   : adminHome
    Created on : 01/04/2021, 22:01:39
    Author     : Maria Paula
--%>

<%@page import="modelo.Classy"%>
<%@page import="modelo.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Home</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/classys.css?v=7">
        <link rel="stylesheet" href="src/styles/perfil.css?v=1">
    <a href="adminLogin.html"></a>
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            Classy classy = (Classy) request.getAttribute("classy");
            Admin admin = (Admin) request.getAttribute("admin");
        %>
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <h2>Classy.</h2>
                            <h3><%= classy.getNome() %></h3>
                            <p><%= classy.getNome_instituicao() %></p>
                        </div>
                        <form name="voltar" action="ControleAdmin" method="POST" class="list-classy">
                            <input type="number" name="id" value="<%= admin.getId() %>" style="display: none;">
                            <input class="register" id="goBack" type="submit" name="acao" value="Voltar ao menu" class="tabs">
                        </form> 
                          
                    </div>
                    <nav>
                        <img id="open-menu" class="icon menu-btn" width="20" src="src/icons/menu.svg" alt="coração">
                        <img id="open-menu" class="icon cross-btn" width="20" src="src/icons/cancel.svg" alt="coração">
                        <form name="adminAtividade" action="ControleTabs" method="POST" class="menu-panel">
                            <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                            <input type="submit" name="acao" value="Home" class=" tabs current">
                            <input type="submit" name="acao" value="Atividades" class="tabs">
                            <input type="submit" name="acao" value="Provas" class="tabs">
                            <input type="submit" name="acao" value="Alunos" class="tabs">
                            <input type="submit" name="acao" value="Forum" class="tabs">
                        </form>
                    </nav>
                </header>
                <div class="classy-box">
                    <div class="perfil">
                        <div class="user">
                            <% if(admin.getImagem() == null) { %>
                                    <img class="icon-perfil" width="80" src="src/icons/user.svg" alt="usuario">
                            <% } else { %>
                                    <img class="imagem-perfil" src="<%= admin.getImagem() %>" alt="usuario">
                            <% }%>
                            </div>
                        <form class="info"  name="edit" accept-charset="ISO-8859-1" name="FEntrada" action="ControleAdmin" method="POST" >
                            <input type="number" name="id" value="<%= admin.getId() %>" style="display: none;">
                            <p><%= admin.getNome() %> (Administrador)</p>
                            <input class="entrar" type="submit" name="acao" value="Editar Dados">
                        </form>
                    </div>
                    <div class="line"></div>
                    <div class="classy-flex">
                        <div class="classy-header">
                            <p>O que deseja fazer?</p>
                            <img src="src/images/hero-img3.png" class="img" alt="Meeting">
                        </div>
                        <div class="classy-options">
                            <form name="adminAtividade" name="FEntrada" action="ControleClassy" method="POST" >
                                <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                                <input class="options" type="submit" name="acao" value="Nova Atividade">
                                <input class="options" type="submit" name="acao" value="Nova Prova">
                                <input class="options" type="submit" name="acao" value="Novo Aluno">
                                <input class="options" type="submit" name="acao" value="Publicar no Forum"> 
                            </form>
                    </div>
                </div>
                </div>
                <footer>
                    <p>O que é o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='/'>Termos de Uso</a></p>
                </footer>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="src/js/menuMobile.js"></script>
    </body>
</html>
