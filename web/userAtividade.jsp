<%-- 
    Document   : adminAtividade
    Created on : 06/04/2021, 10:14:00
    Author     : Maria Paula
--%>

<%@page import="modelo.Aluno"%>
<%@page import="util.SeparateSubject"%>
<%@page import="java.sql.Date"%>
<%@page import="util.ConverteDate"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.Classy"%>
<%@page import="modelo.Atividade"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Atividades</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="src/images/favicon.png"/>
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/form.css">
        <link rel="stylesheet" href="src/styles/classysAtividade.css?v=1">
        <link rel="stylesheet" href="src/styles/classys.css">
        <link rel="stylesheet" href="src/styles/filters.css?v=1">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
         <% 
            ArrayList<Atividade> listaAtiv = (ArrayList<Atividade>) request.getAttribute("atividades");
            HttpSession sessao = request.getSession();
            Classy classy = (Classy)sessao.getAttribute("classy");
            Aluno aluno = (Aluno)sessao.getAttribute("aluno");
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
                       <img id="open-menu" class="icon menu-btn" width="20" src="src/icons/menu.svg" alt="cora????o">
                        <img id="open-menu" class="icon cross-btn" width="20" src="src/icons/cancel.svg" alt="cora????o">
                        <form name="adminAtividade" action="ControleTabsUser" method="POST" class="menu-panel">
                            <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                            <input type="number" name="id_aluno" value="<%= aluno.getId() %>" style="display: none;">
                            <input type="submit" name="acao" value="Home" class=" tabs">
                            <input type="submit" name="acao" value="Atividades" class="tabs current">
                            <input type="submit" name="acao" value="Provas" class="tabs">
                            <input type="submit" name="acao" value="Alunos" class="tabs">
                            <input type="submit" name="acao" value="Forum" class="tabs">
                        </form>
                    </nav>
                </header>
                <div class="filters">
                    <button class="filter-btn">Filtrar</button>
                    <form name="FEntrada" action="ControleFiltros" method="POST" class="filter-panel">
                        <input type="number" name="id_aluno" value="<%= aluno.getId() %>" style="display: none;">
                        <p>Filtrar atividades:</p>
                        <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                        <input type="submit" name="acao" value="10 dias seguintes">
                        <input type="submit" name="acao" value="Atrasadas">
                        <p>Por Mat??ria:</p>
                        <select id="materias" name="txtMateria" placeholder="Mat??rias">
                            <option selected disabled hidden>Mat??rias</option>
                                <%
                                        for (String materia : materias){
                                %>
                            <option value="<%= materia %>"><%= materia %></option>
                                <%
                                        }
                                %>
                        </select>
                        <input type="submit" name="acao" value="Filtrar">
                        <input type="submit" name="acao" value="Todos" class="todos">
                    </form>
                </div>
                <ul>
                    <%
                        if (listaAtiv.isEmpty()) {
                    %>
                    <li class="classy-atividade empty">
                        <div class="ativ-top">
                            <h4>Ops! Parece que n??o h?? atividades marcadas nesse classy ainda.</h4>
                            <p class="materia">Aproveite o descanso ;)</p>
                        </div>
                    </li>
                    <%
                        }
                    %>
                    <%
                        for (Atividade a : listaAtiv){
                    %>
                    <li class="classy-atividade">
                        <div class="ativ-top">
                            <h4><%= a.getNome() %></h4>
                            <p class="materia"><%= a.getMateria() %> - <span>Prof <%= a.getProfessor() %></span></p>
                        </div>
                        <p class="descricao"><span>Descri????o: </span><%= a.getDescricao() %></p>
                        <div class="footer">
                            <% 
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                                LocalDateTime now = LocalDateTime.now(); 
                                ConverteDate conversor = new ConverteDate();
                                Date agora = conversor.getDate(dtf.format(now));

                                if (agora.after(a.getData_entrega())) {
                            
                            %>
                            <p>Data de Entrega: <span class="atrasada"><%= a.getData_entrega() %></span><strong>Atrasada!</strong></p>
                            <%} else {%>
                            <p>Data de Entrega: <span><%= a.getData_entrega() %></span></p>
                            <% } %>
                        </div>
                    </li>
                    <%
                        }
                    %>
                </ul>
                </div>
                <footer>
                    <p>O que ?? o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='/'>Termos de Uso</a></p>
                </footer>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="src/js/menuMobile.js"></script>
            <script>
         $(".filter-btn").click(
            function() {
              $('.filter-panel').toggleClass('showPanel');
            }
          );
    </script>
    </body>
</html>
