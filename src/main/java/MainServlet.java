import dto.TodoTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post Request received!!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* [TEST]
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>MainServlet 정상 도달</h1>");
        out.close();
         */

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TodoDao dao = new TodoDao();
        TodoTable theTable = dao.getTable("todo");
        System.out.println(theTable);
        //System.out.println("한글이 왜 안될까요? 우리나라말이 이런 취급을 받는다니 너무 슬프네요 ㅠ");

        req.setAttribute("listName", theTable.getName());
        req.setAttribute("todo", theTable.getTodo().getItemList());
        req.setAttribute("doing", theTable.getDoing().getItemList());
        req.setAttribute("done", theTable.getDone().getItemList());


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main.jsp");
        requestDispatcher.forward(req, resp);
    }
}
