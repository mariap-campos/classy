          $(document).ready(function () {
            // cookie para exibir uma vez por dia
            var cookies = sessionStorage.getItem('acceptCookies');
            setTimeout(function () {
                if (cookies !== 'true') {
                    $('.popup-banner').addClass('active');
                }
            }, 3000);
    });
    
    $("#accept").click(function(){
            sessionStorage.setItem('acceptCookies', 'true');
            $('.popup-banner').removeClass('active');
    }) 
            
            
            