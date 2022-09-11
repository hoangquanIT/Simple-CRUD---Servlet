package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddEmp
 */
public class AddEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		
		Emp e = new Emp();
		e.setName(name);
		e.setPassword(pass);
		e.setEmail(email);
		e.setCountry(country);
		
		int status = EmpDao.add(e);
		
		if (status > 0) {
			pw.print("<p>Record saved successfully!!!</p>");
			request.getRequestDispatcher("addEmp.html").include(request, response);
		} else {
			pw.print("Sorry! unable to save record");
		}
		
		pw.close();
		
	}

}
