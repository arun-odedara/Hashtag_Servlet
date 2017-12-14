<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.Map" %>
    <%@ page import = "java.util.List" %>
    <%@ page import = "java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>THE HASHTAG</title>
	</head>
	
	<body>
	
		<%String name = (String)request.getAttribute("name");
		Map m = (Map)request.getAttribute("postdetails");
		List id1 = (List)m.get("id");
		List post = (List)m.get("post");
		List reply =(List)m.get("reply");
		%>
	
		<form action="ViewHashtagServlet" method=post>
			<input type="hidden" name="hashtag" value="<%=name%>">
			<br><p><b>Hashtag:</b></p> <%=name%> <br>
			<br><textarea name="message" rows="10" cols="40"></textarea><br>
			<button type="submit" value="Post" name="enter">Post</button>
			<br><br>
		</form>
		<%
		Iterator itr0 = id1.iterator();
		Iterator itr1 = post.iterator();
		Iterator itr2 = reply.iterator();
	
		while(itr1.hasNext()){
		%>
		<b><u>
			<% out.println(itr1.next());%>
		</u></b>
		<% 
			int i = (int)itr0.next();
		%> </br>
		<% 
		try{
			List rep = (List)itr2.next();
			Iterator itr3 = rep.iterator();
			while(itr3.hasNext()){
				out.println(itr3.next());  %> </br>
		<% 	} 
		}	catch(Exception e){
	
		}
		%>
		<form action="ViewHashtagServlet" method="post">
			<input type="text" name="reply">
			<input type="hidden" name="hashtag" value="<%=name%>">
			<input type="hidden" name="parentiId" value="<%=""+i%>">
			<input type="submit" value="Reply" name="enter">
		</form>
		<% }
		%>
	</body>
	
</html>



