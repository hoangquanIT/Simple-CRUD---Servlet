package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class ViewEmp
 */
public class ViewEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		pw.print("<a href='addEmp.html'>Add new Employee</a>");
		pw.print("<br><a href='home.html'>Home</a>");
		pw.print("<h1 style='text-align:center;'>Employees List</h1>");
		
		//Lay ra trang so ...
		String page = request.getParameter("page");
		int pageid = Integer.parseInt(page);
		int total = 3;
		if (pageid != 1) {
			pageid = (pageid-1) * total;
		} else {
			pageid = 0;
		}
		
		//Lay toan bo nhan vien
		//ArrayList<Emp> list = EmpDao.getAllEmployee();
		
		//Lay gioi han nhan vien tren 1 trang
		ArrayList<Emp> list = EmpDao.getLimitEmps(pageid, total);
		
		pw.print("<table border='1' width='100%' style='border-collapse:collapse; text-align:center;'>");
		
		pw.print("<tr>"
				+ "<th>ID</th>"
				+ "<th>Name</th>"
				+ "<th>Password</th>"
				+ "<th>Email</th>"
				+ "<th>Country</th>"
				+ "<th>Edit</th>"
				+ "<th>Delete</th>"
				+ "</tr>");
		
		for(Emp e : list) {
			pw.print("<tr>"
					+ "<td>" + e.getId() + "</td>"
					+ "<td>" + e.getName() + "</td>"
					+ "<td>" + e.getPassword() + "</td>"
					+ "<td>" + e.getEmail() + "</td>"
					+ "<td>" + e.getCountry() + "</td>"
					+ "<td><a href='GetEditEmp?id=" + e.getId() + "'>edit</a></td>"
					+ "<td><a href='DeleteEmp?id=" + e.getId() + "' "
							+ "onclick='return confirm(\"Are you sure to delete this record?\")'>delete</a></td>"
					+ "</tr>");
		}
		
		pw.print("</table>");
		
		pw.print("<a href='ViewEmp?page=1'>1</a>&emsp;");
		pw.print("<a href='ViewEmp?page=2'>2</a>&emsp;");
		
		pw.close();
		
	}

}
