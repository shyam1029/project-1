import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/statusservlet")
public class statusservlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            // Database connection settings
            String jdbcURL = "jdbc:mysql://localhost:3306/project1";
            String dbUser = "root";
            String dbPassword = "root";

            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM application";
            ResultSet resultSet = statement.executeQuery(query);

            PrintWriter out = response.getWriter();
            out.println("<table border='1'>");
            out.println("<tr><th>Email</th><th>Father name</th><th>Roll Number</th><th>Branch</th><th>Academic Year</th><th>Gender</th><th>Address</th><th>State</th><th>Qualification</th><th>Form need</th><th>status</th></tr>");

            while (resultSet.next()) {
                out.println("<tr>");
                for (int i = 1; i <= 11; i++) {
                    out.println("<td>" + resultSet.getString(i) + "</td>");
                }
                out.println("</tr>");
            }

            out.println("</table>");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
