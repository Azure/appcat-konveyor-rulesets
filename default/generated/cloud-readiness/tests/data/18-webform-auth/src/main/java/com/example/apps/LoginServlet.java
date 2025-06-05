import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ❌ Insecure: Manually fetching credentials from request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Example login logic (insecure: storing or validating credentials directly)
        if ("admin".equals(username) && "password123".equals(password)) {
            response.getWriter().println("Login successful!");
        } else {
            response.getWriter().println("Login failed!");
        }
    }
}
