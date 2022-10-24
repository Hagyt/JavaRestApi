'use strict'

// Call the dataTables jQuery plugin
$(document).ready(function() {
    // On ready
});

/**
 * Function to register an user 
 */
const registerUser = async () => {

    const data = {};
    data.name = document.getElementById('firstName').value;
    data.lastname = document.getElementById('lastName').value;
    data.email = document.getElementById('email').value;
    data.password = document.getElementById('password').value;
    const repeatPassword = document.getElementById('repeatPassword').value;

    if (data.password != repeatPassword) {
        alert('The password does not match, type it again.');
        return;
    };

    // Retrieve users info from api
    const response = await fetch('api/users', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.status === 200) {
        alert("Account created successful!");
        document.location.href = 'login.html';
    }

}