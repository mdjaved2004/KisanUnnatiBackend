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
<header>
			<jsp:include page="../navigation_bar.jsp" />
	</header>
	<main id=main>
        <form action="addNewCategory" method="post">
        	<p class="heading">Add New Category</p>
			<div class="form-group">
				<label for="category">Category Name:</label> <input
					type="text" id="category" name="category"
					pattern="[A-Za-z0-9 ()]+"
					title="Only letters (A-Z, a-z, ()) and numbers (0-9) allowed."
					placeholder="enter new category name" maxlength="30" autofocus required>
			</div>
			<input type="submit" value="Submit">
        </form>
        </main>
</body>
</html>