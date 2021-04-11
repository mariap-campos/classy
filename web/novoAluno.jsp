

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
        <link rel="stylesheet" href="src/styles/global.css">
        <link rel="stylesheet" href="src/styles/signup.css">
        <link rel="stylesheet" href="src/styles/classys.css?v=1">
        <link rel="stylesheet" href="src/styles/form.css">
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
                            <h2>Classy.</h2>
                            <h3><%= classy.getNome() %></h3>
                            <p><%= classy.getNome_instituicao() %></p>
                        </div>
                        <a href='/Classy/' id="goBack" class="register">Sair</a>
                    </div>
                    <nav>
                        <form name="adminAtividade" name="FEntrada" action="ControleTabs" method="POST" >
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
                    <h3>Alunos</h3>
                    <div class="line"></div>
                    <div class="classy-flex">
                        <div class="classy-header">
                            <img src="src/images/hero-img3.png" class="img" alt="Meeting">
                        </div>
                        <div class="classy-options">
                            <h3>Adicione um novo aluno!</h3>
                            <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleAluno" method="POST" class="form">
                                <input type="number" name="id" value="<%= classy.getToken() %>" style="display: none;">
                                <input type="text" name="txtNomeAluno" placeholder="Nome do Aluno">
                                <input type="text" name="txtRGM" placeholder="Id do Aluno">
                                <p style="font-weight: 500; color: var(--blue);">Psiu: Lembre-se de passar esse dado ao aluno para que ele possa acessar o classy!</p>
                                <input type="submit" name="acao" value="Cadastrar" class="submit">
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
        <script src="src/js/formValidate.js?v=4"></script>
        <script>
            $(".back").click(function() {
                history.back();
              });
        </script>
    </body>
</html>

