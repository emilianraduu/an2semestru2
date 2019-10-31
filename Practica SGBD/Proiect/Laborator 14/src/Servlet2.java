import javax.servlet.ServletConfig;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "Servlet2")
public class Servlet2 extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ServletConfig config = getServletConfig();
        String message = config.getInitParameter("message"); out.println(message);
    }
}
