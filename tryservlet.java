import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class tryservlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public tryservlet() {
   }

   protected void doGet(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
      String var3 = "jdbc:mysql://localhost:3306/project1";
      String var4 = "root";
      String var5 = "root";
      PrintWriter var6 = var2.getWriter();
      //String var7 = var1.getParameter("fname");
      //String var8 = var1.getParameter("lname");
      //String var9 = var1.getParameter("email");
      //String var10 = var1.getParameter("pass");
      //String var11 = var1.getParameter("Contact");
      String var12 = var1.getParameter("Roll");
      Connection var13 = null;
      PreparedStatement var14 = null;

      try {
         Class.forName("com.mysql.jdbc.Driver");
         var13 = DriverManager.getConnection(var3, var4, var5);
         String var15 = "UPDATE application SET approval=true where Roll=?";
         var14 = var13.prepareStatement(var15);
         //var14.setString(1, var7);
         //var14.setString(2, var8);
         //var14.setString(3, var9);
         //var14.setString(4, var10);
         //var14.setString(5, var11);
         var14.setString(1, var12);
         var14.executeUpdate();
         var6.println("Granted successfully get back to admin profile ");
      } catch (Exception var24) {
         var24.printStackTrace();
         var6.println("JDBC Connection failed with error: " + var24.getMessage());
      } finally {
         try {
            if (var14 != null) {
               var14.close();
            }

            if (var13 != null) {
               var13.close();
            }
         } catch (Exception var23) {
            var23.printStackTrace();
         }

      }

   }
}

