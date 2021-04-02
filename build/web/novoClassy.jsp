<%-- 
    Document   : novoClassy
    Created on : 02/04/2021, 14:40:17
    Author     : Maria Paula
--%>

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
        <link rel="stylesheet" href="src/styles/global.css?v=2">
        <link rel="stylesheet" href="src/styles/form.css?v=2">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            Classy classy = (Classy) request.getAttribute("classy");
        %>
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <p>Bem vindo ao</p>
                            <h2>Classy.</h2>
                        </div>
                        <a href='/Classy/' class="register">Voltar</a>
                    </div>
                </header>
                <div class="content">
                    <h2>Vamos criar um novo Classy!</h2>
                    <p>Preencha as informações abaixo sobre a turma:</p>
                    <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleClassy" method="POST" class="signup">
                        <h3>Dados do Classy</h3>
                        <input type="number" name="id" value="<%= classy.getId_admin() %>" style="display: none;">
                        <input type="text" name="campoClassy" id="campoClassy" placeholder="Nome do Classy">
                        <input type="text" name="campoInsti" id="campoInsti" placeholder="Nome da Instituição">
                        <p>Separe o nome das matéria utilizando ponto e vírgula <span>(exemplo: "Matemática;Física")</span></p>
                        <textarea name="campoMaterias" id="campoMaterias" placeholder="Matérias"></textarea>
                        <div class="label-double">
                            <label>Ínicio do Semestre</label>
                            <label>Final do Semestre</label>
                        </div>
                        <div class="input-double">
                            <input type="date" name="campoInicio" id="campoInicio" placeholder="Inicio do Semestre">
                            <input type="date" name="campoFinal" id="campoFinal" placeholder="Termino do Semestre">
                        </div>
                        <input id="adicionar" type="submit" name="acao" value="Criar Classy">
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
    </body>
</html>