<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>기사 수정</h3>
<form action="updateArticle" method="post">
    <p><input type="text" name="title" placeholder="제목" required/></p>
    <p><input type="text" name="content" placeholder="내용" required/></p>
    <p><input type="text" name="userId" placeholder="유저아이디" required/></p>
    <p><input type="text" name="articleId" placeholder="기사아이디" required/></p>
    <p>
        <button type="submit">저장</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>
