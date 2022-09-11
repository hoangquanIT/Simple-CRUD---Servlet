package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class DeleteEmp
 */
public class DeleteEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String id = request.getParameter("id");
		int eID = Integer.parseInt(id);
		
		int status = EmpDao.delete(eID);
		
		if (status > 0) {
			response.sendRedirect("ViewEmp?page=1");
		} else {
			pw.print("Sorry! Unable to delete record");
			request.getRequestDispatcher("ViewEmp?page=1").include(request, response);
		}
		
	}

}
