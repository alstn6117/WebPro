<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>기사 삭제</h3>
<form action="deleteArticle" method="post">
    <p><input type="text" name="articleId" placeholder="기사아이디" required/></p>
    <p><input type="text" name="userId" placeholder="유저아이디" required/></p>
    <p>
        <button type="submit">저장</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>
