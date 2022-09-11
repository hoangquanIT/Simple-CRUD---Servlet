package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class GetEditEmp
 */
public class GetEditEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		pw.print("<a href='ViewEmp?page=1'>Back</a>");
		pw.print("<br><a href='home.html'>Home</a>");
		pw.print("<h1 style='text-align:center;'>Update employee</h1>");
		String id = request.getParameter("id");
		int eID = Integer.parseInt(id);
		
		Emp e = EmpDao.getEmployeeById(eID);
		
		pw.print("<form action='PostEditEmp' method='post'>");
		pw.print("<table style='margin:auto; padding:5x;'>");
		pw.print("<tr><td><input type='hidden' name='id' value='"+e.getId()+"'/></td></tr>");
		pw.print("<tr>"
				+ "<td><label for='name'>Name: </label></td>"
				+ "<td><input type='text' name='name' id='name' value='"+e.getName()+"'></td>"
				+ "</tr>");
		pw.print("<tr>"
				+ "<td><label for='pass'>Password: </label></td>"
				+ "<td><input type='password' name='pass' id='pass' value='"+e.getPassword()+"'></td>"
				+ "</tr>");
		pw.print("<tr>"
				+ "<td><label for='email'>Email: </label></td>"
				+ "<td><input type='text' name='email' id='email' value='"+e.getEmail()+"'></td>"
				+ "</tr>");
		pw.print("<tr>"
				+ "<td>Country: </td>"
				+ "<td>"
				+ "<select name='country'>"
				+ "<option value='Canada' "+(e.getCountry().equals("Canada") ? "selected" : "")+">Canada</option>"
				+ "<option value='France' "+(e.getCountry().equals("France") ? "selected" : "")+">France</option>"
				+ "<option value='German' "+(e.getCountry().equals("German") ? "selected" : "")+">German</option>"
				+ "<option value='USA' "+(e.getCountry().equals("USA") ? "selected" : "")+">USA</option>"
				+ "<option value='VietNam' "+(e.getCountry().equals("VietNam") ? "selected" : "")+">VietNam</option>"
				+ "</select>"
				+ "</td>"
				+ "</tr>");
		pw.print("<tr align='center'>"
				+ "<td colspan='2'><button type='submit'>Update</button></td>"
				+ "</tr>");
		pw.print("</table>");
		pw.print("</form>");
		
	}

}
