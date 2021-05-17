<%-- 
    Document   : atualizarProva
    Created on : 11/04/2021, 11:18:37
    Author     : Maria Paula
--%>

<%@page import="modelo.Prova"%>
<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Editar Prova</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css?v=2">
        <link rel="stylesheet" href="src/styles/form.css?v=2">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            Prova prova = (Prova) request.getAttribute("prova");
            HttpSession sessao = request.getSession();
            String[] materias = (String[])sessao.getAttribute("materias");
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
                    <h2>Editar Prova</h2>
                    <p>Atualize as informações abaixo sobre a prova</p>
                        <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleProva" method="POST" class="form">
                            <input type="number" name="id" value="<%= prova.getId() %>" style="display: none;">
                            <input type="number" name="id_classy" value="<%= prova.getToken_classy() %>" style="display: none;">
                            <input type="text" name="txtNomeProva" placeholder="Nome da Atividade" value="<%= prova.getNome() %>">
                            <select id="materias" name="txtMateria" placeholder="Matérias" value="<%= prova.getMateria() %>">
                            <option selected disabled hidden>Matérias</option>
                                    <%
                                        for (String materia : materias){
                                    %>
                            <option value="<%= materia %>"><%= materia %></option>
                                    <%
                                        }
                                    %>
                             <option value="Outros">Outros</option>
                            </select>
                            <div class="label-double">
                                <label>Data de Entrega</label>
                            </div>
                            <div class="input-double">
                                <input type="date" name="txtDataProva" id="campoSenha" placeholder="Data de Entrega" value="<%= prova.getData() %>">
                                <input type="submit" name="acao" value="Atualizar" class="submit">
                            </div>
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
