package PS47934_MaiThanhTai;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({
        "/login/form",
        "/login/check"
})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Login Form</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>LOGIN FORM</h1>");

        out.println("<form action='check' method='post'>");

        // Username
        out.println("Username:<br>");
        out.println("<input type='text' name='username'>");
        out.println("<br><br>");

        // Password
        out.println("Password:<br>");
        out.println("<input type='password' name='password'>");
        out.println("<br><br>");

        // Gender
        out.println("Gender:<br>");
        out.println("<input type='radio' name='gender' value='Male'> Male");

        out.println("<input type='radio' name='gender' value='Female'> Female");

        out.println("<br><br>");

        // Hobbies
        out.println("Hobbies:<br>");

        out.println("<input type='checkbox' name='hobbies' value='Game'> Game");

        out.println("<input type='checkbox' name='hobbies' value='Music'> Music");

        out.println("<input type='checkbox' name='hobbies' value='Sport'> Sport");

        out.println("<br><br>");

        // City
        out.println("City:<br>");

        out.println("<select name='city'>");

        out.println("<option value='HCM'>HCM</option>");

        out.println("<option value='HaNoi'>Ha Noi</option>");

        out.println("<option value='DaNang'>Da Nang</option>");

        out.println("</select>");

        out.println("<br><br>");

        // Submit
        out.println("<button type='submit'>Submit</button>");

        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        // Đọc dữ liệu
        String username = req.getParameter("username");

        String password = req.getParameter("password");

        String gender = req.getParameter("gender");

        String city = req.getParameter("city");

        // Đọc checkbox nhiều lựa chọn
        String[] hobbies = req.getParameterValues("hobbies");

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<title>Result</title>");

        out.println("</head>");

        out.println("<body>");

        // Kiểm tra password
        if (!password.equals("123")) {

            out.println("<h1 style='color:red'>Sai mật khẩu!</h1>");

        } else {

            out.println("<h1>THÔNG TIN ĐĂNG NHẬP</h1>");

            out.println("<table border='1' cellpadding='10'>");

            out.println("<tr>");

            out.println("<th>Field</th>");

            out.println("<th>Value</th>");

            out.println("</tr>");

            // Username
            out.println("<tr>");

            out.println("<td>Username</td>");

            out.println("<td>" + username + "</td>");

            out.println("</tr>");

            // Password
            out.println("<tr>");

            out.println("<td>Password</td>");

            out.println("<td>" + password + "</td>");

            out.println("</tr>");

            // Gender
            out.println("<tr>");

            out.println("<td>Gender</td>");

            out.println("<td>" + gender + "</td>");

            out.println("</tr>");

            // City
            out.println("<tr>");

            out.println("<td>City</td>");

            out.println("<td>" + city + "</td>");

            out.println("</tr>");

            // Hobbies
            out.println("<tr>");

            out.println("<td>Hobbies</td>");

            out.println("<td>");

            // Kiểm tra null
            if (hobbies != null) {

                for (String hobby : hobbies) {

                    out.println(hobby + " ");

                }

            } else {

                out.println("Không chọn");

            }

            out.println("</td>");

            out.println("</tr>");

            out.println("</table>");
        }

        out.println("</body>");

        out.println("</html>");
    }
}