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
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/auction";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "Nina2002";
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int userId = Integer.parseInt(request.getParameter("userId"));
       try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
           String deleteUserSQL = "DELETE FROM user WHERE id = ?";
           try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
               preparedStatement.setInt(1, userId);
               preparedStatement.executeUpdate();
           }
          
           // Redirect to a success page
           response.sendRedirect("repsuccess.jsp");
       } catch (Exception e) {
           // Handle database errors
           e.printStackTrace();
           response.sendRedirect("deleteaccount.jsp?error=1");
       }
   }
}

