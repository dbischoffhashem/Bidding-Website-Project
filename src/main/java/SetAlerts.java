import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetItemsServlet")
public class GetItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> inames = new ArrayList<>();
        
        // Get the user_id based on the username
        String username = request.getParameter("username");
        int userId = getUserIdByUsername(username);
        
        String selectedSubcategory = request.getParameter("subcategory");
       // String selectedCategory = request.getParameter("category");
        
        // Insert into Alerts table
        insertIntoAlerts(userId, selectedSubcategory);
        
        // Rest of your code...
    }
    
    private int getUserIdByUsername(String username) throws SQLException {
        int userId = -1;
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT id FROM user WHERE username = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt("id");
                    }
                }
            }
        }
        
        return userId;
    }
    
    private void insertIntoAlerts(int userId, String subcategory) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO Alerts (user_id, subcategory_for_alerts) VALUES (?, ?)";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setString(2, subcategory);
                
                statement.executeUpdate();
            }
        }
    }
}
