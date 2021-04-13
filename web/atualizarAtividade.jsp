
<%@page import="modelo.Atividade"%>
<%@page import="modelo.Classy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy. | Editar Atividade</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css?v=2">
        <link rel="stylesheet" href="src/styles/form.css?v=2">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <% 
            Atividade ativ = (Atividade) request.getAttribute("atividade");
            String[] materias = (String[]) request.getAttribute("materias");
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
                    <p>Atualize as informações abaixo sobre a atividade:</p>
                        <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleAtividade" method="POST" class="form">
                            <input type="number" name="id" value="<%= ativ.getId() %>" style="display: none;">
                            <input type="number" name="id_classy" value="<%= ativ.getClassy_token() %>" style="display: none;">
                            <input type="text" name="txtNome" placeholder="Nome da Atividade" value="<%= ativ.getNome() %>">
                            <textarea type="text" name="txtDesc" placeholder="Descrição" value="<%= ativ.getDescricao() %>"><%= ativ.getDescricao() %></textarea>
                            <input type="text" name="txtProfessor" placeholder="Professor" value="<%= ativ.getProfessor() %>">
                            <select id="materias" name="txtMateria" placeholder="Matérias" value="<%= ativ.getMateria() %>">
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
                                <input type="date" name="txtData" id="campoSenha" placeholder="Data de Entrega" value="<%= ativ.getData_entrega() %>">
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
