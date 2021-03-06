<%-- 
    Document   : userHome
    Created on : 11/04/2021, 17:25:55
    Author     : Maria Paula
--%>

<%@page import="modelo.Prova"%>
<%@page import="modelo.Atividade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Aluno"%>
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
        <link rel="icon" type="image/png" href="src/images/favicon.png"/>
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/classys.css?v=3">
        <link rel="stylesheet" href="src/styles/userHome.css">
        <link rel="stylesheet" href="src/styles/perfil.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            HttpSession sessao = request.getSession();
            Classy classy = (Classy)sessao.getAttribute("classy");
            Aluno aluno = (Aluno)sessao.getAttribute("aluno");
            ArrayList<Atividade> ativ = (ArrayList<Atividade>) request.getAttribute("atividades");
            ArrayList<Prova> prova = (ArrayList<Prova>) request.getAttribute("provas");
        %>
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <h2>Classy.</h2>
                            <h3><%= classy.getNome() %></h3>
                            <p><%= classy.getNome_instituicao() %></p>
                        </div>
                        <a class="register" href="/Classy/">Sair</a>
                          
                    </div>
                    <nav>
                       <img id="open-menu" class="icon menu-btn" width="20" src="src/icons/menu.svg" alt="cora????o">
                        <img id="open-menu" class="icon cross-btn" width="20" src="src/icons/cancel.svg" alt="cora????o">
                        <form name="adminAtividade" action="ControleTabsUser" method="POST" class="menu-panel">
                            <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                            <input type="number" name="id_aluno" value="<%= aluno.getId() %>" style="display: none;">
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
                                <% if(aluno.getImagem() == null || aluno.getImagem().equals("")) { %>
                                    <img class="icon-perfil" width="80" src="src/icons/user.svg" alt="usuario">
                                <% } else { %>
                                    <img class="imagem-perfil" src="<%= aluno.getImagem() %>" alt="usuario">
                                <% }%>
                            </div>
                        <form class="info"  name="edit" accept-charset="ISO-8859-1" name="FEntrada" action="ControleAluno" method="POST" >
                            <input type="number" name="id" value="<%= aluno.getId() %>" style="display: none;">
                            <p><%= aluno.getNome() %> (<%= aluno.getRgm() %>)</p>
                            <input class="entrar" type="submit" name="acao" value="Editar Dados">
                        </form>
                    </div>
                    <div class="line"></div>
                    <div class="classy-flex">
                        <div class="classy-header">
                            <p>O que deseja fazer?</p>
                            <img src="src/images/hero-img4.png" class="img" alt="Meeting">
                        </div>
                        <div class="classy-options">
                            <ul class="atividades">
                                <h3>Pr??ximas Atividades</h3>
                            <%
                                if (ativ.isEmpty()) {
                            %>
                            <li class="atividade-item empty">
                                <div class="ativ-top">
                                    <p class="materia">N??o h?? atividades ainda.</p>
                                </div>
                            </li>
                            <%
                                }
                            %>
                            <%
                                for (Atividade a : ativ){
                            %>
                                <li class="atividade-item">
                                    <div class="infos">
                                        <h3 class="title"><%= a.getNome() %></h3>
                                        <p class="subject"><%= a.getMateria() %></p>
                                    </div>
                                    <div class="data"> <%= a.getData_entrega() %></div>
                                </li>
                            <%
                                }
                            %>
                            <p style="text-align:end;font-size: 12px;" >Veja todas na aba 'Atividades'</p>
                            </ul>
                            <ul class="provas">
                                <h3>Pr??ximas Provas</h3>
                            <%
                                if (prova.isEmpty()) {
                            %>
                            <li class="atividade-item empty">
                                <div class="ativ-top">
                                    <p class="materia">N??o h?? provas marcadas.</p>
                                </div>
                            </li>
                            <%
                                }
                            %>
                            <%
                                for (Prova p : prova){
                            %>
                                <li class="atividade-item prova">
                                    <div class="infos">
                                        <h3 class="title"><%= p.getNome() %> </h3>
                                        <p class="subject"><%= p.getMateria() %></p>
                                    </div>
                                    <div class="data"><%= p.getData() %></div>
                                </li>
                                <%
                                }
                            %>
                            <p style="text-align:end;font-size: 12px;">Veja todas na aba 'Provas'</p>
                            </ul>
                    </div>
                </div>
                </div>
                <footer>
                    <p>O que ?? o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='/'>Termos de Uso</a></p>
                </footer>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="src/js/menuMobile.js"></script>
    </body>
</html>
