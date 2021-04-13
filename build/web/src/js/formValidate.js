
   
$(function() {
    $("form[name='FEntrada']").validate({
        rules: {

        campoUsuario: "required",
        campoNome: "required",
        campoClassy: "required",
        campoInsti: "required",
        campoInicio: "required",
        campoFinal: "required",
        campoMaterias: "required",
        txtNome: "required",
        txtNomeAluno: "required",
        txtRGM: "required",
        campoRGM:"required",
        txtDataProva: "required",
        txtNomeProva: "required",
        txtPost: "required",
        txtDesc: "required",
        txtProfessor: "required",
        txtMateria: "required",
        txtData: "required",
        campoEmail: {
            required: true,
            email: true
        },
        campoSenha: {
            required: true,      
        },
        campoSenha2: {
            equalTo: "#campoSenha"
        }
        },
        messages: {
        campoUsuario: "Por favor, informe um usuário.",
        campoNome: "Por favor, informe seu nome.",
        campoClassy: "Por favor, informe o nome da turma.",
        campoInsti: "Por favor, informe o nome da instituição.",
        campoInicio: "Por favor, informe a data de ínicio do semestre.",
        campoFinal: "Por favor, informe a data de término do semestre.",
        campoMaterias: "Por favor, as matérias de sua turma.",
        txtNome: "Por favor, informe o nome da atividade.",
        txtNomeAluno: "Por favor, informe o nome do aluno",
        txtRGM: "Por favor, informe o id do aluno",
        txtDesc: "Por favor, descreva a atividade",
        txtPost: "Você não pode postar uma mensagem vazia.",
        txtProfessor: "Por favor, informe o professor.",
        txtMateria: "Pro favor, informe a matéria",
        txtData: "Por favor, informe a data de entrega.",
        txtDataProva: "Por favor, informe a data da prova",
        txtNomeProva: "Por favor, dê um nome a sua prova.",
        campoRGM:"Por favor, informe o id.",
        campoSenha: {
            required: "Por favor, informe uma senha.",
        },
        campoSenha2: {
            equalTo: "Senhas não correpondem. Por favor, verifique."
        },
        campoEmail: "Por favor, informe um email válido."
        },

        submitHandler: function(form) {
        form.submit();
        }
    });
});