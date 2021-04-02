<%-- 
    Document   : novaAtividade
    Created on : 02/04/2021, 14:24:33
    Author     : Maria Paula
--%>

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
        <div class="container">
                <header class="header">
                    <div class="header-itens">
                        <div class="logo">
                            <h2>Classy.</h2>
                            <h3>Analise e Desenvolvimento de Sistemas - 4º</h3>
                            <p>Fatec</p>
                        </div>
                        <a href='home.html' class="register">Sair</a>
                    </div>
                    <nav>
                        <button class="current">Home</button>
                        <button>Atividades</button>
                        <button>Provas</button>
                        <button>Alunos</button>
                        <button>Fórum</button>
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
                            <form>
                                <input type="text" name="txtNome" placeholder="Nome da Atividade">
                                <textarea type="text" name="txtDesc" placeholder="Descrição"></textarea>
                                <input type="text" name="txtProfessor" placeholder="Professor">
                                <select id="materias" placeholder="Matérias">
                                    <option selected disabled hidden>Matérias</option>
                                    <option value="Aplicativos para Web">Aplicativos para Web</option>
                                    <option value="Banco de Dados">Banco de Dados</option>
                                </select>
                                <div class="label-double">
                                    <label>Data de Entrega</label>
                                </div>
                                <div class="input-double">
                                    <input type="date" name="txtData" id="campoSenha" placeholder="Data de Entrega">
                                    <input type="submit" value="Cadastrar">
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
        <script>
            $(".back").click(function() {
                history.back();
              });
        </script>
    </body>
</html>
