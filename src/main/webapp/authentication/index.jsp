<!DOCTYPE html>
<html>
<body>
<head>
    <link href="/1/css/app.css" rel="stylesheet">
</head>

<div class="flex items-center justify-center w-full mt-12 p-8 bg-blue shadow">
    <h2 class="text-white">Authenticatie</h2>
</div>

<div id="app">
    <router-view></router-view>
</div>

<script src="/1/js/app.js?version=1"></script>

<script>
    // This is called with the results from from FB.getLoginStatus().
    function statusChangeCallback(response) {
        if (response.status === 'connected') {
            axios.get('/1/api/callback/login?access_token='+response.authResponse.accessToken).then(({data}) => {
                //Login was successful et the token.
                localStorage.setItem('token', data);

                //Redirect to home page
                window.location.href = '/1/home';
            });
        } else {
           alert('Geen geldige FB login!')
        }
    }
    // This function is called when someone finishes with the Login
    // Button. See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }
    window.fbAsyncInit = function() {
        FB.init({
            appId : '2285783981741199',
            cookie : true, // enable cookies to allow the server to access
            // the session
            xfbml : true, // parse social plugins on this page
            version : 'v2.8' // use graph api version 2.8
        });
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    };
    // Load the SDK asynchronously
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "https://connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
    // Here we run a very simple test of the Graph API after login is
    // successful. See statusChangeCallback() for when this call is made.
</script>

<div class="w-full h-full h-screen flex items-center justify-center">
    <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
    </fb:login-button>
</div>

<div id="status"></div>
</body>
</html>
