package cvut.fit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cvut.fit.service.DB;

/**
 * Servlet implementation class Book
 */
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		PrintWriter out = response.getWriter();
		out.println ("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
		          "<html> \n" +
		            "<head> \n" +
		              "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=ISO-8859-1\"> \n" +
		              "<title> Processing JSP </title> \n" +
		            "</head> \n" +
		            "<body> \n" +k
		              "<a href=\"/mdw01-static\">Back</a>" + 
		            "</body> \n" +
		          "</html>"
		        );
	}



}
