<%-- 
    Document   : atualizarAluno
    Created on : 10/04/2021, 11:50:08
    Author     : Maria Paula
--%>

<%@page import="modelo.Admin"%>
<%@page import="modelo.Aluno"%>
<%@page import="modelo.Atividade"%>
<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Editar Dados</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="src/images/favicon.png"/>
        <link rel="stylesheet" href="src/styles/global.css?v=2">
        <link rel="stylesheet" href="src/styles/form.css?v=2">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/perfil.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            HttpSession sessao = request.getSession();
            Aluno aluno = (Aluno)sessao.getAttribute("aluno");
        %>
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <p>Bem vindo ao</p>
                            <h2>Classy.</h2>
                        </div>
                        <a class="register back">Voltar</a>
                    </div>
                </header>
                <div class="content">
                    <h2>Editar Dados</h2>
                    <p>Atualize suas infromações abaixo</p>
                        <form accept-charset="ISO-8859-1" name="atualizar" action="ControleAluno" method="POST" class="form">
                            <input type="number" name="id" value="<%= aluno.getId() %>" style="display: none;">
                            <div class="perfil atualizar">
                            <div class="user">
                                <% if(aluno.getImagem() == null || aluno.getImagem().equals("")) { %>
                                    <img class="icon-perfil" width="80" src="src/icons/user.svg" alt="usuario">
                                <% } else { %>
                                    <img class="imagem-perfil" src="<%= aluno.getImagem() %>" alt="usuario">
                                <% }%>
                            </div>
                            <div class="input-imagem">
                                <% if(aluno.getImagem() == null  ) { %>
                                    <input type="text" name="campoImagem" id="campoImagem" placeholder="Link da sua imagem de perfil">
                                <% } else { %>
                                    <input type="text" name="campoImagem" id="campoImagem" placeholder="Link da sua imagem de perfil" value="<%= aluno.getImagem() %>">
                                <% }%>
                               
                            </div>
                            </div>
                            <input type="text" name="campoNome" placeholder="Nome" value="<%= aluno.getNome() %>">
                            <input type="submit" name="acao" value="Atualizar Dados" class="submit">
                        </form>
                    </div>
                <footer>
                    <p>O que é o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='/'>Termos de Uso</a></p>
                </footer>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="src/js/formValidate.js"></script>
        <script>
            $(".back").click(function() {
                history.back();
              });
        </script>
    </body>
</html>
