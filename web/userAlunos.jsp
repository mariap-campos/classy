<%-- 
    Document   : adminAluno
    Created on : 08/04/2021, 09:44:53
    Author     : Maria Paula
--%>

<%@page import="modelo.Aluno"%>
<%@page import="modelo.Classy"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Alunos</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/form.css">
        <link rel="stylesheet" href="src/styles/classysAluno.css?v=4">
        <link rel="stylesheet" href="src/styles/classys.css">
        <link rel="stylesheet" href="src/styles/filters.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
         <% 
            ArrayList<Aluno> listaAlunos = (ArrayList<Aluno>) request.getAttribute("alunos");
            Classy classy = (Classy) request.getAttribute("classy");
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
                        <form name="alunoAtividade" action="ControleTabsUser" method="POST" >
                            <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                            <input type="number" name="id_aluno" value="<%= aluno.getId() %>" style="display: none;">
                            <input type="submit" name="acao" value="Home" class=" tabs">
                            <input type="submit" name="acao" value="Atividades" class="tabs">
                            <input type="submit" name="acao" value="Provas" class="tabs ">
                            <input type="submit" name="acao" value="Alunos" class="tabs current">
                            <input type="submit" name="acao" value="Forum" class="tabs">
                        </form>
                    </nav>
                </header>
                <div class="alunos-container">
<!--                    <p class="number">Mostrando 20 alunos</p>-->
                    <%
                        if (listaAlunos.isEmpty()) {
                    %>
                    <div class="empty">
                        Ops parece que não há alunos cadastrados nessa turma ainda!
                    </div>
                    <%
                        } else {
                    %>
                    <table>
                        <tr>
                          <th>Aluno</th>
                          <th>Situação</th>
                        </tr>
                        <%
                         for (Aluno a : listaAlunos){
                        %>   
                        <tr>
                            <td><%= a.getNome() %></td>
                            <td><%= a.getSituacao() %></td>
                        </tr>
                        <%
                        }
                    %>
                      </table>
                      <%
                        } 
                    %>
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

