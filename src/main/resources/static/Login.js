function triggerLogin(){
    const loginModal = document.getElementById('login-modal')
    let login = new bootstrap.Modal(loginModal, {})
    login.show();
}