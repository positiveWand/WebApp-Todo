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
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <header>
        <h1>ToDo list : ${requestScope.listName}</h1>
        <nav>
            <button id="addFormAppearButton">Todo item 추가하기</button>
        </nav>
    </header>
    <div id="container-top">
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
                    <li class="todo_item_blank"></li>
                    <li>
                        <div class="todo_item" id=<%= "item-"+todo.get(i).getId() %>>
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
                    <li class="todo_item_blank last_blank"></li>
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
                    <li class="todo_item_blank"></li>
                    <li>
                        <div class="todo_item" id=<%= "item-"+doing.get(i).getId() %>>
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
                    <li class="todo_item_blank last_blank"></li>
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
                    <li class="todo_item_blank"></li>
                    <li>
                        <div class="todo_item" id=<%= "item-"+done.get(i).getId() %>>
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
                    <li class="todo_item_blank last_blank"></li>
                </ul>
            </div>
        </div>
    </div>
</body>
<div id="overlay-container">
    <div id="shade_box"></div>
    <div id="form-container">
        <div id="form-controlbar">
            <img id="addFormCloseButton" src="../resources/close.png" alt="Close">
        </div>
        <div>
            <h3 id="popupTitle">Todo 추가하기</h3>
            <form action="http://localhost:8080/main" method="post">
                <div>
                    <label for="title">제목</label>
                    <input type="text" id="title" class="form-input">
                </div>
                <div>
                    <label for="create_time">생성 시간</label>
                    <input type="datetime-local" id="create_time" class="form-input" disabled>
                </div>
                <div>
                    <label for="status">상태</label>
                    <select name="status" id="status" class="form-input">
                        <option value="todo">TODO</option>
                        <option value="doing">DOING</option>
                        <option value="done">DONE</option>
                    </select>
                </div>
                <div>
                    <label for="description">내용</label>
                    <div id="description" class="form-input" contenteditable="true"></div>
                </div>
                <input type="submit" id="submit" class="form-input">
            </form>
        </div>
    </div>
</div>
<script src="./js/script.js"></script>
</html>
