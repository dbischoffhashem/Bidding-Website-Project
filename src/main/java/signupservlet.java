import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signupservlet")
public class signupservlet extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String pass = request.getParameter("pass");
		String confirm = request.getParameter("confirm");
		String choice = request.getParameter("choice");
		
		System.out.println("fname:" + fname);
		System.out.println("lname:" + fname);
		System.out.println("pass:" + fname);
		System.out.println("confirm:" + fname);
		System.out.println("choice:" + fname);

		
		if (passwordsMatch(pass, confirm)) {
           // can create account and add to database!
			//set to true or false
			if (choice.equals("yes")) {
			    choice = "TRUE";
			} else {
			    choice="FALSE";
			}
	       
	        //start
	       
	        try {
	            // Load the JDBC driver
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
	         
	            String sql = "INSERT INTO user (username, password, seller) VALUES (?, ?, ?)";
	            // Prepare the statement
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	           
	            preparedStatement.setString(1, lname + fname.charAt(0));
	            preparedStatement.setString(2, pass);
	            preparedStatement.setString(3,choice);
	            
	            System.out.println("sql");
	            // Execute the statement
	            preparedStatement.executeUpdate();
	           
	            // Close resources
	            preparedStatement.close();
	            conn.close();
	           
	        
	        } catch (Exception e) {
	            // Handle exceptions
	        
	            e.printStackTrace();
	        
	        }
	       
	       
	  
			response.sendRedirect("confirmsignup.jsp");
       } else {
           // Failed login
           response.sendRedirect("signup.jsp?error=1"); // Redirect back to the login page with an error notice
           }
	}
		
	private boolean passwordsMatch(String passOne, String passTwo)
	{
		if (passOne != null && passTwo != null && passOne.equals(passTwo)) {
		    // Passwords match, continue with registration
			return true;
		} else {
		    // Passwords don't match, redirect back to the signup page with an error message
		    return false;
		}
	}
}
		
