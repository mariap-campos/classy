
$("#campoSenha").passwordValidation({"confirmField":"#campoSenha2"},function(element, valid, match, failedCases) {
    console.log('gelo');
      $("#errors").html(failedCases.join("\n"));    
       if(!valid) $(element).addClass("password-rules");
       if(!valid || !match) $("#myConfirmPassword").addClass("error");
});