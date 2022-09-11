package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpDao {
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/firstproj","root","*Decade113*");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return con;
	}
	
	public static boolean login(String email, String pass) {
		boolean status = false;
		
		try {
			Connection con = getConnection();
			
			String query = "select * from employee where emp_email=? and emp_pass=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	public static ArrayList<Emp> getAllEmployee() {
		ArrayList<Emp> list = new ArrayList<>();
		
		try {
			Connection con = EmpDao.getConnection();
			
			String query = "select * from employee";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				
				list.add(e);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	public static Emp getEmployeeById(int id) {
		Emp e = new Emp();
		
		try {
			Connection con = EmpDao.getConnection();
			
			String query = "select * from employee where emp_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			
			con.close();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		return e;
	}
	
	public static ArrayList<Emp> getLimitEmps(int start, int total) {
		ArrayList<Emp> list = new ArrayList<>();
		
		try {
			Connection con = getConnection();
			
			String query = "select * from employee limit " + start + "," + total;
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Emp emp = new Emp();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setPassword(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setCountry(rs.getString(5));
				
				list.add(emp);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	public static int add(Emp e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			
			String query = "insert into employee(emp_name,emp_pass,emp_email,emp_country)"
					+ " values(?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4,  e.getCountry());
			
			status = ps.executeUpdate();
			
			con.close();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		return status;
	}
	
	public static int update(Emp e) {
		int status = 0;
		
		try {
			Connection con = EmpDao.getConnection();
			
			String query = "update employee set emp_name=?,emp_pass=?,emp_email=?,"
					+ "emp_country=? where emp_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			ps.setInt(5, e.getId());
			
			status = ps.executeUpdate();
			
			con.close();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		return status;
	}
	
	public static int delete(int id) {
		int status = 0;
		
		try {
			Connection con = EmpDao.getConnection();
			
			String query = "delete from employee where emp_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			status = ps.executeUpdate();
			
			con.close();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		return status;
	}

}
