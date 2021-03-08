<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form name="uploadForm" method="post" action="uploadFile" enctype="multipart/form-data">
    <input type="file" name="imgFile">
    <input type="submit" value="업로드">
</form>

<form name="downloadForm" method="post" action="downloadFile" enctype="multipart/form-data">

    <input type="submit" value="다운로드">
</form>
</body>
</html>