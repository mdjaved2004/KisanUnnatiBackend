<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/form.css" rel="stylesheet" type="text/css">
</head>
<body>
	

	<form action="adminAdd" method="post" onsubmit="return validateForm()">
 		<p class="heading">Admin Details Form</p>
		<!-- Admin Name -->
		<div class="form-group">
			<label for="name">Admin Name:</label><br>
			<input type="text" id="name" name="name" pattern="[A-Za-z0-9+]{3,40}"
			       title="Name must be between 3 and 40 characters"  minlength="3" maxlength="40" required><br><br>
		</div>
		
		<div class="form-group">	 
			<!-- Admin Email -->
			<label for="email">Admin Email:</label><br>
			<input type="email" id="email" name="email" minlength="6" maxlength="35"
			       title="Email must be between 6 and 35 characters" required><br><br>
		</div>
		
		<div class="form-group">
			<!-- Admin Mobile Number -->
			<label for="mobileNumber">Admin Mobile Number:</label><br>
			<input type="text" id="mobileNumber" name="mobileNumber" pattern="[0-9]{10,12}"
			       title="Enter a valid mobile number (10 to 12 digits)" minlength="10" maxlength="12"
			       required><br><br>
		</div>
		
		<div class="form-group">
			<!-- Admin Password -->
			<label for="password">Admin Password:</label><br>
			<input type="password" id="password" name="password"
			       pattern=".{6,35}"
			       title="Password must be between 6 and 35 characters"
			       minlength="6" maxlength="35" required><br><br>
		</div>
		
		<div class="form-group">
			<!-- Confirm Password -->
			<label for="confirmAdminPassword">Confirm Password:</label><br>
			<input type="password" id="confirmPassword" name="confirmPassword"
			       pattern=".{6,35}"
			       title="Password must be between 6 and 35 characters"
			       minlength="6" maxlength="35" required><br><br>
		</div>
		<input type="submit" value="Submit">

	</form>

	<script>
	  function validateForm() {
	    const password = document.getElementById("adminPassword").value;
	    const confirmPassword = document.getElementById("confirmAdminPassword").value;
	    if (password !== confirmPassword) {
	      alert("Passwords do not match!");
	      return false; // Prevent form submission
	    }
	    return true;
	  }
	</script>

</body>
</html>