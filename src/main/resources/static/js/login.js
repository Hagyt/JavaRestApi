'use strict'

// Call the dataTables jQuery plugin
$(document).ready(function() {
    // On ready
});

/**
 * Function to register an user 
 */
const login = async () => {

    const data = {};
    data.email = document.getElementById('email').value;
    data.password = document.getElementById('password').value;

    // Retrieve users info from api
    const response = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    const responseData = await response.text();
    if (responseData !== 'FAIL') {
        localStorage.token = responseData;
        localStorage.email = data.email;
        alert("Its really you!");
        document.location.href = 'users.html';
    }

}