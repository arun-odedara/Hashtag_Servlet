package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HashtagFeed;

/**
 * Servlet implementation class ViewHashtagServlet
 */

public class ViewHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewHashtagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String enter = request.getParameter("enter"); //fetching value from the viewhashtag jsp page
		if(enter.equals("Post")){
			String value= request.getParameter("message"); //textarea name (content of db table hashtagfeed)
			String hashtag = request.getParameter("hashtag"); //name of hashtag (hashtag name of db table hashtagfeed)
			HashtagFeed hfeed= new HashtagFeed();
			hfeed.setContent(value);
			hfeed.setHashtag(hashtag);
			hfeed.setParentFeedID(0);	
			hfeed.storeValue();
			request.setAttribute("name", hashtag); // name of the hasgtag to the jsp
			hfeed.setHashtag(hashtag);
			Map m = hfeed.retrieveValue();
			request.setAttribute("postdetails", m);// all values related to the post to view hash tag jsp
			RequestDispatcher rdispatcher=request.getRequestDispatcher("viewhashtag.jsp");
			rdispatcher.forward(request, response);
		}
		
		else{
			String value= request.getParameter("reply"); //textarea input (content attribute of db table hashtagfeed)
			String hashtag = request.getParameter("hashtag"); //name of hashtag (hashtag attribute of db table hashtagfeed)
			String parentId = request.getParameter("parentiId");// parent id (parentfeedId attribute of db table hashtagfeed)
		
			HashtagFeed hfeed= new HashtagFeed();
			hfeed.setContent(value);
			hfeed.setHashtag(hashtag);
			hfeed.setParentFeedID(Integer.parseInt(parentId));
			hfeed.storeValue();
			
			request.setAttribute("name", hashtag); // name of the hasgtag to the jsp
			hfeed.setHashtag(hashtag);
			Map m = hfeed.retrieveValue();
			request.setAttribute("postdetails", m);// all values related to the post to view hashtag jsp
			RequestDispatcher rd=request.getRequestDispatcher("viewhashtag.jsp");
			rd.forward(request, response);
		}		
	}
}
