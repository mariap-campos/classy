<%-- 
    Document   : adminForum
    Created on : 07/04/2021, 11:30:04
    Author     : Maria Paula
--%>

<%@page import="modelo.Aluno"%>
<%@page import="modelo.Forum"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Fórum</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/form.css">
        <link rel="stylesheet" href="src/styles/classyForum.css">
        <link rel="stylesheet" href="src/styles/classys.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            Classy classy = (Classy) request.getAttribute("classy");
            ArrayList<Forum> listaPosts = (ArrayList<Forum>) request.getAttribute("posts");
            Aluno aluno = (Aluno) request.getAttribute("aluno");
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
                        <form name="adminAtividade" action="ControleTabsUser" method="POST" class="menu-panel">
                            <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                            <input type="number" name="id_aluno" value="<%= aluno.getId() %>" style="display: none;">
                            <input type="submit" name="acao" value="Home" class=" tabs">
                            <input type="submit" name="acao" value="Atividades" class="tabs">
                            <input type="submit" name="acao" value="Provas" class="tabs ">
                            <input type="submit" name="acao" value="Alunos" class="tabs">
                            <input type="submit" name="acao" value="Forum" class="tabs current">
                        </form>
                    </nav>
            </header>
            <form name="FEntrada" accept-charset="ISO-8859-1" class="post form" action="ControleForum" method="POST">
                    <h4>Faça uma postagem no fórum!</h4>
                    <input type="number" name="id_classy" value="<%= classy.getToken() %>" style="display: none;">
                    <input type="number" name="id" value="<%= aluno.getId() %>" style="display: none;">
                    <textarea name="txtPost" placeholder="Escreva sua mensagem aqui..."></textarea>
                    <div class="input-double">
                        <input type="text" name="txtAssunto" id="campoSenha" placeholder="Assunto">
                        <input type="submit" name="acao"  value="Publicar" class="submit">
                    </div>
            </form>
            <ul> 
                    <%
                        if (listaPosts.isEmpty()) {
                    %>
                    <li class="classy-forum empty">
                        <div class="ativ-top">
                            <h4>Ops! Parece que não há postagens no fórum ainda.</h4>
                            <p class="materia">:(</p>
                        </div>
                    </li>
                    <%
                        }
                    %>
                    <%
                        for (Forum f : listaPosts){
                    %>
                    <li class="classy-forum">
                        <div class="user">
                            <p><%= f.getNome() %></p>
                            <% if(f.getImagem() == null) { %>
                                    <img class="icon-perfil" width="80" src="src/icons/user.svg" alt="usuario">
                            <% } else { %>
                                    <img class="imagem-perfil" src="<%= f.getImagem() %>" alt="usuario">
                            <% }%>
                        </div>
                        <div class="message">
                            <h5><%= f.getAssunto() %></h5>
                            <p><%= f.getPostagem() %></p>
                            <p class="date"> Postado em: <%= f.getData_postagem() %></p>
                            <div class="icons-box">
                            <% 
                                 if(f.getUser_id() == aluno.getId()) {
                            %>
                            <form name="FApagar" action="ControleForum" method="POST">
                                    <input type="number" name="id" value="<%= f.getId() %>" style="display: none;">
                                    <input type="number" name="id_classy" value="<%= classy.getToken() %>" style="display: none;">
                                    <input type="number" name="id_aluno" value="<%= aluno.getId() %>" style="display: none;">
                                    <button class="actions" type="submit" name="acao" value="Apagar"><img class="icon" width="20" src="src/icons/trash-yellow.svg" alt="coração"></button>
                            </form>
                            <%               
                                }
                            %>
                            </div>
                        </div>
                    </li>
                    <%
                        }
                    %>
            </ul>
        </div>
        <footer>
            <p>O que é o <a href='/' class="classy">Classy.?</a></p>
            <p><a href='/'>Termos de Uso</a></p>
        </footer>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="src/js/formValidate.js?v=6"></script>
        <script src="src/js/menuMobile.js"></script>
    </body>
</html>
