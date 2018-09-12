<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="static/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            alert("init");
        })
    </script>
    <title>book_list - ${name}</title>
</head>
<body>
</body>
</html>
