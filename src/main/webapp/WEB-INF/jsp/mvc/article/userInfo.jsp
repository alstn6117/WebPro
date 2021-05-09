<!DOCTYPE html>
<html>
<body>
<h3>사용자 정보</h3>
<a href="./userForm">회원가입</a></p> <a href="./articleForm">글 쓰기</a></p> <a href="./updateForm">글 수정</a> </p> <a href="./deleteForm">글 삭제</a>
<p><%= session.getAttribute("USER") %>
</p>
</body>
</html>
