<%-- 
    Document   : atualizarAluno
    Created on : 10/04/2021, 11:50:08
    Author     : Maria Paula
--%>

<%@page import="modelo.Aluno"%>
<%@page import="modelo.Atividade"%>
<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Atualizar Atividade</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css?v=2">
        <link rel="stylesheet" href="src/styles/form.css?v=2">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            Aluno aluno = (Aluno) request.getAttribute("aluno");
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
                    <h2>Editar Atividade</h2>
                    <p>Atualize as informações abaixo sobre o aluno</p>
                        <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleAluno" method="POST" class="form">
                            <input type="number" name="id" value="<%= aluno.getId() %>" style="display: none;">
                            <input type="number" name="id_classy" value="<%= aluno.getClassy_token() %>" style="display: none;">
                            <input type="text" name="txtNomeAluno" placeholder="Nome do Aluno" value="<%= aluno.getNome() %>">
                            <input type="text" name="txtRgm" placeholder="RGM" value="<%= aluno.getRgm() %>">
                            <select id="materias" name="txtSituacao" placeholder="Situação" value="<%= aluno.getSituacao() %>">
                            <option selected value="Regular">Regular</option>
                             <option value="Inativo">Inativo</option>
                            </select>
                             <input type="submit" name="acao" value="Atualizar" class="submit">
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
