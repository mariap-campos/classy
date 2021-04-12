
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
                        <a class="register back">Voltar</a>
                    </div>
                </header>
                <div class="content">
                    <h2>Editar Classy</h2>
                    <p>Atualize as informações abaixo sobre a turma:</p>
                    <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleClassy" method="POST" class="signup form">
                        <h3>Dados do Classy</h3>
                        <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                        <input type="text" name="campoClassy" id="campoClassy" placeholder="Nome do Classy" value="<%= classy.getNome() %>">
                        <input type="text" name="campoInsti" id="campoInsti" placeholder="Nome da Instituição" value="<%= classy.getNome_instituicao() %>" >
                        <p>Separe o nome das matéria utilizando ponto e vírgula <span>(exemplo: "Matemática;Física")</span></p>
                        <textarea name="campoMaterias" id="campoMaterias" placeholder="Matérias" value="<%= classy.getMaterias() %>"><%= classy.getMaterias() %></textarea>
                        <div class="label-double">
                            <label>Ínicio do Semestre</label>
                            <label>Final do Semestre</label>
                        </div>
                        <div class="input-double">
                            <input type="date" name="campoInicio" id="campoInicio" placeholder="Inicio do Semestre" value="<%= classy.getData_inicio() %>">
                            <input type="date" name="campoFinal" id="campoFinal" placeholder="Termino do Semestre" value="<%= classy.getData_final() %>">
                        </div>
                        <input id="adicionar" type="submit" name="acao" value="Atualizar" class="submit">
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
