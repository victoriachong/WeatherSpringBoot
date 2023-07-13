function triggerLogin(){
    const loginModal = document.getElementById('login-modal')
    let login = new bootstrap.Modal(loginModal, {})
    login.show();
}
function triggerSignup(){
    const signupModal = document.getElementById('signup-modal')
    let signup = new bootstrap.Modal(signupModal, {})
    signup.show();
}