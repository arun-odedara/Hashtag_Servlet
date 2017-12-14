package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hashtag;
import model.HashtagFeed;

/**
 * Servlet implementation class SearchHashtagServlet
 */

public class SearchHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchHashtagServlet() {
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
		String value = request.getParameter("searchhashtag"); //fetching value from the search jsp page
		Hashtag h=new Hashtag();
		h.setNew_hashtag(value);
		boolean b=h.searchValue();
		
		if(b){
			HashtagFeed hfeed= new HashtagFeed();
			request.setAttribute("name", value); // name of the hashtag to the jsp
			hfeed.setHashtag(value);
			Map m = hfeed.retrieveValue();
			request.setAttribute("postdetails", m);// all values related to the post to view hash tag jsp
			RequestDispatcher rd=request.getRequestDispatcher("viewhashtag.jsp");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error","Sorry this hashtag does not exist");
			RequestDispatcher rd=request.getRequestDispatcher("search.jsp");
			rd.forward(request, response);
		}
	}

}
