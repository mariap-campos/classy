<%-- 
    Document   : novaAtividade
    Created on : 02/04/2021, 14:24:33
    Author     : Maria Paula
--%>

<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Nova Atividade</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/classys.css?v=1">
        <link rel="stylesheet" href="src/styles/form.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            String[] materias = (String[]) request.getAttribute("materias");
            Classy classy = (Classy) request.getAttribute("classy");
        %>
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <h2>Classy.</h2>
                            <h3><%= classy.getNome() %></h3>
                            <p><%= classy.getNome_instituicao() %></p>
                        </div>
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
                            <input type="submit" name="acao" value="Fórum" class="tabs">
                        </form>
                    </nav>
                </header>
                <div class="classy-box">
                    <h3>Atividades</h3>
                    <div class="line"></div>
                    <div class="classy-flex">
                        <div class="classy-header">
                            <img src="src/images/hero-img3.png" class="img" alt="Meeting">
                        </div>
                        <div class="classy-options">
                            <h3>Adicione uma nova atividade!</h3>
                            <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleAtividade" method="POST" class="form">
                                <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                                <input type="text" name="txtNome" placeholder="Nome da Atividade">
                                <textarea type="text" name="txtDesc" placeholder="Descrição"></textarea>
                                <input type="text" name="txtProfessor" placeholder="Professor">
                                <select id="materias" name="txtMateria" placeholder="Matérias">
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
                                    <input type="date" name="txtData" id="campoSenha" placeholder="Data de Entrega">
                                    <input type="submit" name="acao" value="Cadastrar" class="submit">
                                </div>
                            </form>
                            <a class="back">Voltar á home</a>
                        </div>
                    </div>
                </div>
                <footer>
                    <p>O que é o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='/'>Termos de Uso</a></p>
                </footer>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="src/js/jquery.password-validation.js?v=1"></script>
        <script src="src/js/formValidate.js?v=3"></script>
        <script>
            $(".back").click(function() {
                history.back();
              });
        </script>
        <script src="src/js/menuMobile.js"></script>
    </body>
</html>
