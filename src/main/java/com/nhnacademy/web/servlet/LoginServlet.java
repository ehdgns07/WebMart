package com.nhnacademy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginServlet", urlPatterns = "/login", initParams = {
    @WebInitParam(name = "id", value="liverbird"),
    @WebInitParam(name = "pwd", value="12345")
})
public class LoginServlet extends HttpServlet {
    String correspondId;
    String correspondPwd;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        correspondId = config.getInitParameter("id");
        correspondPwd = config.getInitParameter("pwd");
//        correspondId = getServletContext().getInitParameter("id");
//        correspondPwd = getServletContext().getInitParameter("pwd");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        try(PrintWriter out = resp.getWriter()) {
            if (Objects.isNull(session)) {
                resp.sendRedirect("/login.html");
            } else {
                out.println("Login Success!: " + session.getAttribute("id"));

                resp.setContentType("text/html");
                out.println("\n<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<button><a href=\"/logout\">logout</a>></button>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");

                String uri = (String)req.getAttribute("uri");
                if (Objects.nonNull(uri)) {
                    resp.sendRedirect(uri);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        if(Objects.equals(correspondId, id) && Objects.equals(correspondPwd, pwd)){
            HttpSession session = req.getSession(false);
            if(Objects.nonNull(session)){
                session.invalidate();
            }
            session = req.getSession();

            session.setAttribute("id", id);

            resp.sendRedirect("/login");

        }else{
            resp.sendRedirect("/loginFail.html");
        }
    }
}
