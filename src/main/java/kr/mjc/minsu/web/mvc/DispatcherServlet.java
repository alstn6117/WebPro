package kr.mjc.minsu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mvc/*")
public class DispatcherServlet extends HttpServlet {

    @Autowired
    Controller controller;

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        switch (uri) {
            case "/mvc/article/userList" -> controller.userList(request, response);
            case "/mvc/article/userForm" -> controller.userForm(request, response);
            case "/mvc/article/userInfo" -> controller.userInfo(request, response);
            case "/mvc/article/addUser" -> controller.addUser(request, response);
            case "/mvc/article/login" -> controller.login(request, response);
            case "/mvc/article/articleList" -> controller.articleList(request, response);
            case "/mvc/article/articleForm" -> controller.articleForm(request, response);
            case "/mvc/article/addArticle" -> controller.addArticle(request, response);
            case "/mvc/article/updateArticle" -> controller.updateArticle(request, response);
            case "/mvc/article/getArticle" -> controller.getArticle(request, response);
            case "/mvc/article/loginForm" -> controller.loginForm(request, response);
            case "/mvc/article/deleteArticle" -> controller.deleteArticle(request, response);
            case "/mvc/article/updateForm" -> controller.updateForm(request, response);
            case "/mvc/article/deleteForm" -> controller.deleteForm(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
