<%@ page import="dto.TodoItem" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: kbShin
  Date: 2021-12-12
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>ToDo List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>ToDo list : ${requestScope.listName}</h1>
        <nav>
            <button>새로운 ToDo 추가</button>
        </nav>
    </header>
    <div class="container">
        <div id="todo" class="section-container">
            <div class="section-title">
                <h2>TODO</h2>
            </div>
            <div class="section-list">
                <ul>
                    <%
                        List<TodoItem> todo = (List<TodoItem>) request.getAttribute("todo");
                        for(int i = 0; i < todo.size(); i++) {
                    %>
                    <li>
                        <div class="todo_item">
                            <h3>
                                <%= todo.get(i).getTitle() %>
                            </h3>
                            <p>
                                <%= todo.get(i).getCreateDate() %>
                            </p>
                        </div>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
        <div id="doing" class="section-container">
            <div class="section-title">
                <h2>DOING</h2>
            </div>
            <div class="section-list">
                <ul>
                    <%
                        List<TodoItem> doing = (List<TodoItem>) request.getAttribute("doing");
                        for(int i = 0; i < doing.size(); i++) {
                    %>
                    <li>
                        <div class="doing_item">
                            <h3>
                                <%= doing.get(i).getTitle() %>
                            </h3>
                            <p>
                                <%= doing.get(i).getCreateDate() %>
                            </p>
                        </div>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
        <div id="done" class="section-container">
            <div class="section-title">
                <h2>DONE</h2>
            </div>
            <div class="section-list">
                <ul>
                    <%
                        List<TodoItem> done = (List<TodoItem>) request.getAttribute("done");
                        for(int i = 0; i < done.size(); i++) {
                    %>
                    <li>
                        <div class="done_item">
                            <h3>
                                <%= done.get(i).getTitle() %>
                            </h3>
                            <p>
                                <%= done.get(i).getCreateDate() %>
                            </p>
                        </div>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
    <footer>
        FOOTER
    </footer>
</body>
</html>
