import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/repservlet")

public class repservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "";
		String password = "";
		username = request.getParameter("txtName");
		password = request.getParameter("txtPwd");
		if (isValidUser(username, password)) {
           // Successful login
			response.sendRedirect("rephome.jsp?username=" + username);
       } else {
           // Failed login
           response.sendRedirect("replogin.jsp?error=1"); // Redirect back to the login page with an error notice
           }
	}
		
	private boolean isValidUser(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
	        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM representative WHERE username=? AND password=?");
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	       System.out.println(username);
	       System.out.println(password);

		    boolean isValid = rs.next();
		    rs.close();
		    pstmt.close();
		    conn.close();
		    return isValid;
		 } catch (Exception e) {
		    e.printStackTrace();
		    return false;   
		 }
	}
	
}
