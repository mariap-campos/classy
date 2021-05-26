          $(document).ready(function () {
            // cookie para exibir uma vez por dia
            setTimeout(function () {
                $('.popup-banner').addClass('active');
            }, 3000);
    });
    
    $("#accept").click(function(){
            $('.popup-banner').removeClass('active');
    }) 
            
            
            