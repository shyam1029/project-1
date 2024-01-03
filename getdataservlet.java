import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getdataservlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String jdbcURL = "jdbc:mysql://localhost:3306/project1";
        String dbUser = "root";
        String dbPassword = "root";

        String username = request.getParameter("email"); // Get the username from a previous input

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT * FROM data WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            out.println("<html><head><title>User Data</title></head><body>");
            out.println("<h1>Data for User: " + username + "</h1>");

            if (resultSet.next()) {
                String userData = resultSet.getString("data");
                out.println("<p>" + userData + "</p>");
            } else {
                out.println("<p>User not found.</p>");
            }

            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}