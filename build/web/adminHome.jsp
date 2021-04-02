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
        <title>Classy.</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/classys.css">
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
                        <a href='/Classy/' class="register">Voltar</a>
                    </div>
                    <nav>
                        <button class="current">Home</button>
                        <button>Atividades</button>
                        <button>Provas</button>
                        <button>Alunos</button>
                        <button>Fórum</button>
                    </nav>
                </header>
                <div class="classy-box">
                    <h3>Bem vindo, <%= admin.getNome() %>!</h3>
                    <div class="line"></div>
                    <div class="classy-flex">
                        <div class="classy-header">
                            <p>O que deseja fazer?</p>
                            <img src="src/images/hero-img3.png" class="img" alt="Meeting">
                        </div>
                        <div class="classy-options">
                        <a href="novaAtividade.jsp">Nova Atividade</a>
                        <a>Nova Prova</a>
                        <a>Novo Aluno</a>
                        <a>Publicar no Fórum</a>
                    </div>
                </div>
                </div>
                <footer>
                    <p>O que é o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='/'>Termos de Uso</a></p>
                </footer>
        </div>
        <script src="" async defer></script>
    </body>
</html>
