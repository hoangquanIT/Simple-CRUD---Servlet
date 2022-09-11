package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class PostEditEmp
 */
public class PostEditEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String id = request.getParameter("id");
		int eID = Integer.parseInt(id);
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		
		Emp e = new Emp();
		e.setId(eID);
		e.setName(name);
		e.setPassword(pass);
		e.setEmail(email);
		e.setCountry(country);
		
		int status = EmpDao.update(e);
		
		if (status > 0) {
			response.sendRedirect("ViewEmp?page=1");
		} else {
			pw.print("Sorry! Unable to update record");
		}
		
		pw.close();
		
	}

}
