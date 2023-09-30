import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/resetpasswordservlet")
public class resetpasswordservlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "Taylorswift1322!";
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int userId = Integer.parseInt(request.getParameter("userId"));
       String newPassword = request.getParameter("newPassword");
       try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
           String updatePasswordSQL = "UPDATE 'user' SET password = ? WHERE id = ?";
           try (PreparedStatement preparedStatement = connection.prepareStatement(updatePasswordSQL)) {
               preparedStatement.setString(1, newPassword);
               preparedStatement.setInt(2, userId);
               preparedStatement.executeUpdate();
           }
          
           // Redirect to a success page
           response.sendRedirect("repsuccess.jsp");
       } catch (Exception e) {
           // Handle database errors
           e.printStackTrace();
           response.sendRedirect("resetpassword.jsp?error=1");
       }
   }
}

