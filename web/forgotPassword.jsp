<%-- 
    Document   : forgotPassword
    Created on : 12/04/2021, 16:53:18
    Author     : Maria Paula
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Classy.</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="src/styles/global.css?v=1">
        <link rel="stylesheet" href="src/styles/home.css?v=2">
        <link rel="stylesheet" href="src/styles/form.css">
        <link href="https://fonts.googleapis.com/css2?family=Long+Cang&family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="login-page">
                <header>
                    <div class="logo">
                        <p>Bem vindo ao</p>
                        <h2>Classy.</h2>
                    </div>
                    <a href='/Classy/' class="register">Voltar</a>
                </header>
                <div class="content">
                    <h2>Informe a senha enviada ao seu email</h2>
                    <form accept-charset="ISO-8859-1" name="FEntrada" action="ControleAdmin" method="POST" class="form">
                        <input type="text" name="campoEmailSenha" id="campoUsuario" placeholder="Senha">
                        <input name="acao" type="submit" value="Enviar" class="submit">
                    </form>
                    <p><a href="forgotPassword.jsp" >Esqueci minha senha</a></p>
                    <p>N�o possui uma conta? <a href="signUp.html" >Cadastre-se!</a></p>
                </div>
                <footer>
                    <p>O que � o <a href='/' class="classy">Classy.?</a></p>
                    <p><a href='signUp.html'>Termos de Uso</a></p>
                </footer>
            </div>
            <div class="hero-img">
                <img src="src/images/hero-img2.png" alt="Meeting">
                <h2>A solu��o simples para </br> suas turmas.</h2>
                <p>Desenvolvido com <img class="icon" src="src/icons/heart.svg" alt="cora��o"> pelo time <span>classy.</span></p>
            </div>
        </div>
        <script src="" async defer></script>
    </body>
</html>