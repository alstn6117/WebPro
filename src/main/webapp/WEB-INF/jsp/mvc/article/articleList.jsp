<%@ page import="kr.mjc.minsu.web.dao.Article" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<body>
<h3>기사 목록</h3>
<p>

<%
    List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    for (Article article : articleList) {
%>
    <a href="getArticle?articleId=<%= article.getArticleId()%>">
<%= article %>
    </a>
</p>
<%
    }
%>
</body>
</html>
