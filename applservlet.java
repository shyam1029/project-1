import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class applservlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public applservlet() {
   }

   protected void doGet(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
      String var3 = "jdbc:mysql://localhost:3306/project1";
      String var4 = "root";
      String var5 = "root";
      PrintWriter var6 = var2.getWriter();
      String var7 = var1.getParameter("email");
      String var8 = var1.getParameter("fname");
      String var9 = var1.getParameter("roll");
      String var10 = var1.getParameter("branch");
      String var11 = var1.getParameter("acad");
      String var12 = var1.getParameter("gender");
      String var31 = var1.getParameter("address");
      String var32 = var1.getParameter("state");
      String var33 = var1.getParameter("Qual");
      String var34 = var1.getParameter("need");
      Connection var13 = null;
      PreparedStatement var14 = null;

      try {
         Class.forName("com.mysql.jdbc.Driver");
         var13 = DriverManager.getConnection(var3, var4, var5);
         String var15 = "INSERT INTO application(email,fname,roll,branch,acad,gender,address,state,Qual,need) VALUES (?,?,?,?,?,?,?,?,?,?)";
         var14 = var13.prepareStatement(var15);
         var14.setString(1, var7);
         var14.setString(2, var8);
         var14.setString(3, var9);
         var14.setString(4, var10);
         var14.setString(5, var11);
         var14.setString(6, var12);
         var14.setString(7, var31);
         var14.setString(8, var32);
         var14.setString(9, var33);
         var14.setString(10, var34);
         var14.executeUpdate();
         var2.sendRedirect("student_home.html");
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

