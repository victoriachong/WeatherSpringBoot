function buttonchange() {
    var logIn = document.getElementById('login')
    var SignUp = document.getElementById('signup')
    var LogOut = document.getElementById('logout')


    if (userid != null) {
        login.style.visibility = "block";
        signup.style.visibility = "block";
        logout.style.visibility = "none";
    } else {
        login.style.visibility = "none";
        signup.style.visibility = "none";
        logout.style.visibility = "block";
    }
}




function triggerLogin(){
    const loginModal = document.getElementById('login-modal')
    let login = new bootstrap.Modal(loginModal, {})
    login.show();
}

function loginCheck(){
    var inputUsername = document.getElementById("Login-username").value
    var inputPassword = document.getElementById("Login-password").value

    console.log(inputUsername)
    fetch("/users/username/"+inputUsername).then(resp => resp.json())
        .then(function(data){
            console.log(data)
            if(data[0].password == inputPassword){
                sessionStorage.setItem('userid', data[0].id)
                sessionStorage.setItem('username', data[0].username)
                console.log(sessionStorage.getItem('userid'))
                location.reload()

            }else{
                alert("wrong password")
            }
        }).catch(function(error){
        console.error(error)
        alert("user does not exist")
    })

    const loginModal = document.getElementById('login-modal')
    let login = new bootstrap.Modal(loginModal, {})
    login.hide()
}
function triggerSignup(){
    const signupModal = document.getElementById('signup-modal')
    let signup = new bootstrap.Modal(signupModal, {})
    signup.show();
}

function newUserSignup(){
    var inputUsername = document.getElementById("Signup-username").value
    var inputPassword = document.getElementById("Signup-password").value
    var inputEmail = document.getElementById("email-input").value
    var defaultCity = document.getElementById("default-city").value

    fetch("/users", {method:"POST",
        headers:{'Accept': 'application/json', 'Content-Type':'application/json'}})
}

function triggerLogout() {

    var username = sessionStorage.getItem("username");
    console.log(username)
    document.getElementById("username").innerHTML = username
    window.sessionStorage.removeItem("username")
}
