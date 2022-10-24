'use strict'

// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadUsers();
    $('#usersTable').DataTable();
    loadUserEmail();
});

const loadUserEmail = () => {
    const userEmail = localStorage.email;
    document.getElementById('userEmail').innerHTML = userEmail;
}

// Build headers when it needs
const getHeaders = () => {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}

/**
 * Function to load users from api
 */
const loadUsers = async () => {

    // Clean the users table's tbody
    let html = '';
    document.querySelector('#usersTable tbody').outerHTML = html;

    // Retrieve users info from api
    const response = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()
    });

    // Parse response to json format
    const users = await response.json();

    // Build html with the records retrieved
    html = users.reduce((html, user) => {
        const deleteButton = `
            <a href="#" onclick="deleteUser(${user.id})" class="btn btn-danger btn-circle">
                <i class="fas fa-trash"></i>
            </a>
        `
        const phone = user.phone == null ? '-': user.phone;

        return `
            ${html}
            <tr>
                <td>${user.id}</td>
                <td>${user.name} ${user.lastname}</td>
                <td>${user.email}</td>
                <td>${phone}</td>
                <td>
                    ${deleteButton}
                </td>
            </tr>
        `
    }, '');

    // Add the built html within users table's tbody
    document.querySelector('#usersTable tbody').outerHTML = html;

}

/**
 * Function to delete a user with his id
 */
const deleteUser = async (id) => {

    if (!confirm('Are you sure to delete this user?')) return;

    const response = await fetch(`api/user/${id}`, {
        method: 'DELETE',
        headers: getHeaders()
    });

    loadUsers();
}
