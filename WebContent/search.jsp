<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>SEARCH HASHTAG</title>
	</head>

	<body>
		<form action="SearchHashtagServlet" method=post>
			Search Hashtag:<br><input type="text" name="searchhashtag">
			<button type="submit">Search</button><br>
			<%String str=(String)request.getAttribute("error"); 
					if(str!=null)
						out.println(str);
			%>
		</form>	
	</body>

</html>