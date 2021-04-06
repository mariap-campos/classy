<%-- 
    Document   : adminAtividade
    Created on : 06/04/2021, 10:14:00
    Author     : Maria Paula
--%>

<%@page import="modelo.Atividade"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="src/styles/form.css">
        <link rel="stylesheet" href="src/styles/classysAtividade.css">
        <link rel="stylesheet" href="src/styles/classys.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
         <% 
            ArrayList<Atividade> listaAtiv = (ArrayList<Atividade>) request.getAttribute("atividades");
            Integer classy_token = (Integer) request.getAttribute("classy");
        %>
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <h2>Classy.</h2>
                            <h3>Analise e Desenvolvimento de Sistemas - 4º</h3>
                            <p>Fatec</p>
                        </div>
                        <a href='home.html' class="register">Voltar</a>
                    </div>
                    <nav>
                        <form name="adminAtividade" name="FEntrada" action="ControleTabs" method="POST" >
                            <input type="number" name="id" value="<%= classy_token %>" style="display: none;">
                            <input type="submit" name="acao" value="Home" class="tabs ">
                            <input type="submit" name="acao" value="Atividades" class="tabs current">
                            <input type="submit" name="acao" value="Provas" class="tabs">
                            <input type="submit" name="acao" value="Alunos" class="tabs">
                            <input type="submit" name="acao" value="Fórum" class="tabs">
                        </form>
                    </nav>
                </header>
                <ul>
                    <%
                        if (listaAtiv.isEmpty()) {
                    %>
                    <li class="classy-atividade empty">
                        <div class="ativ-top">
                            <h4>Ops! Parece que não há atividades marcadas nesse classy ainda.</h4>
                            <p class="materia">Aproveite o descanso ;)</p>
                        </div>
                    </li>
                    <%
                        }
                    %>
                    <%
                        for (Atividade a : listaAtiv){
                    %>
                    <form name="adminHome" accept-charset="ISO-8859-1" name="FEntrada" action="ControleClassy" method="POST" >
                    <li class="classy-atividade">
                        <div class="ativ-top">
                            <h4><%= a.getNome() %></h4>
                            <p class="materia"><%= a.getMateria() %></p>
                        </div>
                        <p class="descricao"><span>Descrição: </span><%= a.getDescricao() %></p>
                        <div class="footer">
                            <p>Data de Entrega: <span><%= a.getData_entrega() %></span></p>
                            <div class="icons-box">
                                <form name="FEntrada" action="ControleCompras" method="POST">
                                    <input type="number" name="id" value="<%= a.getId() %>" style="display: none;">
                                    <button class="actions" type="submit" name="acao" value="Apagar"><img class="icon" width="20" src="src/icons/trash-yellow.svg" alt="coração"></button>
                                    <button class="actions" type="submit" name="acao" value="abrirAtualizar"><img class="icon" width="20" src="src/icons/edit-yellow.svg" alt="coração"></button>
                                </form>
                            </div>
                        </div>
                    </li>
                    </form>
                    <%
                        }
                    %>
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
