document.addEventListener('DOMContentLoaded', function() {
	const loginForm = document.querySelector('form');

	loginForm.addEventListener('submit', function(event) {
		event.preventDefault();

		// Get the username and password from the form
		const username = document.getElementById('username').value;
		const password = document.getElementById('password').value;

		// Perform any client-side validation if needed

		// Send a POST request to your login endpoint
		fetch('/login.html', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({ username, password }),
		})
			.then(response => {
				if (!response.ok) {
					throw new Error('Invalid credentials');
				}
				return response.json();
			})
			.then(data => {
				// Handle successful login, e.g., redirect to another page
				console.log('Login successful:', data);
			})
			.catch(error => {
				// Handle login failure, e.g., display an error message
				console.error('Login failed:', error.message);
			});
	});
});