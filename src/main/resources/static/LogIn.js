function triggerLogin(){
    const loginModal = document.getElementById('login-modal')
    let login = new bootstrap.Modal(loginModal, {})
    login.show();
}

function loginCheck(){
    var inputEmail = document.getElementById("Login-email").value
    var inputPassword = document.getElementById("Login-password").value

    fetch("/users/email/"+inputEmail).then(resp => resp.json())
        .then(function(data){
            console.log(data)
            if(data[0].password == inputPassword){
                sessionStorage.setItem('userid', data[0].id)
                sessionStorage.setItem('username', data[0].username)
                sessionStorage.setItem('search_location', data[0].favourites[0])
                console.log(sessionStorage.getItem('userid'))
                location.reload()

            }else{
                alert("wrong password")
            }
        }).catch(function(error){
        console.error(error)
        alert("user does not exist")
    })
}


function triggerSignup(){
    const signupModal = document.getElementById('signup-modal')
    let signup = new bootstrap.Modal(signupModal, {})
    signup.show();
}


function newUserSignup(){
    var favourites = []
    favourites.push(document.getElementById("default-city").value)

    var user={
        "username" : document.getElementById("Signup-username").value,
        "password" : document.getElementById("Signup-password").value,
        "email" : document.getElementById("email-input").value,
        "localtzoffset": 2,
        "favourites" :favourites
    }

    fetch("/users", {method:"POST",
        headers:{'Accept': 'application/json',
            'Content-Type':'application/json'},
        body: JSON.stringify(user)})
        .then(function (resp) {
            console.log(resp)
            if(resp.status == 201){
                alert("user successfully created please sign in")
            }else{alert("user not created")}
        })
        .catch(function (error) {
            console.error(error);
        });

}

function triggerLogout(){
    sessionStorage.removeItem('userid')
    sessionStorage.removeItem('username')
    location.reload()
}

function triggerEditProfile(){
    var user = fetch("/users/"+sessionStorage.getItem("userid")).then(resp => resp.json())
        .then(function(data){
            console.log(data)
            document.getElementById('edit-username').value = data.username
            document.getElementById('edit-password').value = data.password
            document.getElementById('edit-default-city').value = data.favourites[0]
            document.getElementById('email-edit').value = data.email
            const editProfileModal = document.getElementById('editprofile-modal')
            editProfileModal.value = data
            return data
        }).catch(function(error){
        console.error(error)
    })

    const editProfileModal = document.getElementById('editprofile-modal')
    let editprofile = new bootstrap.Modal(editProfileModal, {})
    console.log(user)
    editProfileModal.value = user
    editprofile.show();
}

function patchProfile(){
    var favourites = []
    favourites.push(document.getElementById("edit-default-city").value)

    var data = {
        "username" : document.getElementById('edit-username').value ,
        "password" : document.getElementById('edit-password').value ,
        "favourites": favourites,
        "email": document.getElementById('email-edit').value
    }

    fetch('/users/'+sessionStorage.getItem('userid'), {method: "PATCH", headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: JSON.stringify(data)})
        .then(function (resp) {
            console.log(resp)
        })
        .catch(function (error) {
            console.error(error);
        });

}

function deleteProfile(){
    var user =document.getElementById('editprofile-modal').value
    fetch('/users/'+user.id, {method: "DELETE"})
        .then(function (resp) {
            console.log(resp)
        })
        .catch(function (error) {
            console.error(error);
        });
    sessionStorage.removeItem('userid')
    sessionStorage.removeItem('username')

    location.reload()

}