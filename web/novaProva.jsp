<%-- 
    Document   : novaProva
    Created on : 08/04/2021, 14:21:44
    Author     : Maria Paula
--%>


<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Nova Prova</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="src/images/favicon.png"/>
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/classys.css?v=1">
        <link rel="stylesheet" href="src/styles/form.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            HttpSession sessao = request.getSession();
            Classy classy = (Classy)sessao.getAttribute("classy");
            String[] materias = (String[])sessao.getAttribute("materias");
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
                            <input type="submit" name="acao" value="Forum" class="tabs">
                        </form>
                    </nav>
                </header>
                <div class="classy-box">
                    <h3>Provas</h3>
                    <div class="line"></div>
                    <div class="classy-flex">
                        <div class="classy-header">
                            <img src="src/images/hero-img3.png" class="img" alt="Meeting">
                        </div>
                        <div class="classy-options">
                            <h3>Adicione uma nova prova!</h3>
                            <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleProva" method="POST" class="form">
                                <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                                <input type="text" name="txtNomeProva" placeholder="Nome da Prova">
                                <select id="materias" name="txtMateria" placeholder="Matérias">
                                    <option selected disabled hidden>Matéria</option>
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
                                    <label>Data</label>
                                </div>
                                <div class="input-double">
                                    <input type="date" name="txtDataProva" id="campoSenha" placeholder="Data de Entrega">
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
        <script src="src/js/jquery.password-validation.js?v=2"></script>
        <script src="src/js/formValidate.js?v=3"></script>
        <script>
            $(".back").click(function() {
                history.back();
              });
        </script>
                <script src="src/js/menuMobile.js"></script>
    </body>
</html>
