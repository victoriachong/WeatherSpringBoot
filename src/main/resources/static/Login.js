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
    login.show()
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

//
//
// function postEvent(){
//
//     var data = {
//         "title": document.getElementById('Event-title').value,
//         "description": document.getElementById('description-text').value,
//         "startTime": Math.floor(new Date(document.getElementById('start-datetime').value+":00.000Z").getTime()/1000),
//         "endTime": Math.floor(new Date(document.getElementById('end-datetime').value+":00.000Z").getTime()/1000),
//         "userid": 1
//     }
//
//
//     fetch('/events', {method: "POST", headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json'
//         }, body: JSON.stringify(data)})
//         .then(function (resp) {
//             console.log(resp)
//
//         })
//         .catch(function (error) {
//             console.error(error);
//         });
//
//     // getEventsfromDB()
//     const eventcreator = document.getElementById('event-creator')
//     let myModal = new bootstrap.Modal(eventcreator, {})
//
//     document.getElementById('Event-title').value = ""
//     document.getElementById('description-text').value = ""
//     myModal.hide();
//
//     calender.refetchEvents()
// }
//
//
//
//
//
//
// function logout(){
//     sessionStorage.setItem('userid', null)
// }
//
// function editUser(){
//
//}