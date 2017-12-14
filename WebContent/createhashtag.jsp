<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CREATE NEW HASHTAG</title>
	</head>
	
	<body>
		<form action="CreateHashtagServlet" method=post>
			Create Hashtag: <br><input type="text" name="createhashtag">
			<button type="submit">Submit</button><br>
			<%String str=(String)request.getAttribute("error"); 
					if(str!=null)
						out.println(str);
			%>
		</form>
	</body>

</html>