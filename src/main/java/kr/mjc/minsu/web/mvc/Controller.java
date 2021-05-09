package kr.mjc.minsu.web.mvc;

import kr.mjc.minsu.web.dao.Article;
import kr.mjc.minsu.web.dao.ArticleDao;
import kr.mjc.minsu.web.dao.User;
import kr.mjc.minsu.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class Controller {
    private final ArticleDao articleDao;
    private final UserDao userDao;

    @Autowired
    public Controller(ArticleDao articleDao, UserDao userDao) {
        this.articleDao = articleDao;
        this.userDao = userDao;
    }

    public void userList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("userList", userDao.listUsers(0, 100));

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/userList.jsp")
                .forward(request, response);
    }

   
    public void userForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/userForm.jsp")
                .forward(request, response);
    }

   
    public void userInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/userInfo.jsp")
                .forward(request, response);
    }

   
    public void addUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));

        try {
            userDao.addUser(user);
            response.sendRedirect(request.getContextPath() + "/mvc/article/userList");
        } catch (DuplicateKeyException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/userForm?msg=Duplicate email");
        }
    }

    
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDao.login(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("USER", user);
            response.sendRedirect(request.getContextPath() + "/mvc/article/userInfo");
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/user/loginForm?msg=Wrong email or password");
        }
    }

    public void articleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("articleList", articleDao.listArticles(0, 100));

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleList.jsp")
                .forward(request, response);
    }


  
    public void loginForm(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/loginForm.jsp")
                .forward(request, response);
    }

  
    public void getArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Article article = articleDao.getArticle(Integer.parseInt(request.getParameter("articleId")));
        HttpSession session = request.getSession();
        session.setAttribute("ARTICLE", article);

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/getArticle.jsp")
                .forward(request, response);
    }
   
    public void addArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        article.setName(request.getParameter("name"));

        try {
            articleDao.addArticle(article);
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
        } catch (DuplicateKeyException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleForm?msg=Duplicate article");
        }
    }

    public void updateArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));

        try {
            articleDao.updateArticle(article);
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
        } catch (DuplicateKeyException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/updateArticle?msg=Duplicate article");
        }
    }

    public void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Article article = new Article();
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));

        try {
            articleDao.deleteArticle(Integer.parseInt(request.getParameter("articleId")), Integer.parseInt(request.getParameter("userId")));
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
        } catch (DuplicateKeyException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/updateArticle?msg=Duplicate article");
        }
    }

    
    public void articleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleForm.jsp")
                .forward(request, response);
    }

    public void updateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/updateForm.jsp")
                .forward(request, response);
    }

    public void deleteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/deleteForm.jsp")
                .forward(request, response);
    }
}
