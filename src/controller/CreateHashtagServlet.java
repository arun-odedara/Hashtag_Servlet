package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hashtag;

/**
 * Servlet implementation class CreateHashtagServlet
 */

public class CreateHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateHashtagServlet() {
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
		String value = request.getParameter("createhashtag"); //fetching value from the createhashtag jsp page
		//validation for the hashtag creation
		char[] spl={'~','!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '{', '}', ':', ';', '"', '\'', '<', '>', '?', '/', '|', '+', '-', '_', '=', '.', ',', ' ','\\'};
		int count = 0;
		for (int i=0;i<value.length();i++){
			char c = value.charAt(i);			
			for(int j=0; j<spl.length;j++){
				if(c == spl[j]){
					count++;
				}
			}
		}
		if(count>0){
			request.setAttribute("error","Invalid character");
			RequestDispatcher rd=request.getRequestDispatcher("createhashtag.jsp");
			rd.forward(request, response);
		}else{
				Hashtag h=new Hashtag();
				h.setNew_hashtag(value);
				boolean b=h.storeValue();
				
				if(b){
					RequestDispatcher rd=request.getRequestDispatcher("search.jsp");
					rd.forward(request, response);
				}		
		else{
				request.setAttribute("error","The hashtag already exists, please choose a different name");
				RequestDispatcher rd=request.getRequestDispatcher("createhashtag.jsp");
				rd.forward(request, response);
			}		
		}			
	}
	
	

}
