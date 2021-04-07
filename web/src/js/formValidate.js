
   
$(function() {
    console.log('ol');
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
        txtDesc: "Por favor, descreva a atividade",
        txtProfessor: "Por favor, informe o professor.",
        txtMateria: "Pro favor, informe a matéria",
        txtData: "Por favor, informe a data de entrega.",
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