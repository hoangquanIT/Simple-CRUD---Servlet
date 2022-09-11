package admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginAdmin
 */
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		if (EmpDao.login(email, pass)) {
			response.sendRedirect("home.html");
		} else {
			pw.print("Sorry email or password is invalid");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		
		pw.close();
		
	}

}
