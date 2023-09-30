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
@WebServlet("/EditUserBidServlet")
public class EditUserBidServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int bidId = Integer.parseInt(request.getParameter("bidId"));
       double newBidAmount = Double.parseDouble(request.getParameter("newBidAmount"));
       try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
           String updateBidSQL = "UPDATE bids SET max_bid = ? WHERE bid_id = ?";
           try (PreparedStatement preparedStatement = connection.prepareStatement(updateBidSQL)) {
               preparedStatement.setDouble(1, newBidAmount);
               preparedStatement.setInt(2, bidId);
               preparedStatement.executeUpdate();
           }
          
           // Redirect to a success page
           response.sendRedirect("repsuccess.jsp");
       } catch (Exception e) {
           // Handle database errors
           e.printStackTrace();
           response.sendRedirect("EditBid.jsp?error=1");
       }
   }
}

