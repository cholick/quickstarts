<%@ page import="java.util.Date" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Hello</title>
    <style>
        body {
            font-family: sans-serif
        }
    </style>
</head>

<body>

<ul>
    <li>Now <%= new Date() %></li>
    <li><a href="hello">Hello servlet</a></li>
</ul>

</body>

</html>
