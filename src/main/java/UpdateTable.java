import dto.TodoTable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateTable", urlPatterns = "/updateTable")
public class UpdateTable extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UpdateTable Request Received.");

        TodoDao dao = new TodoDao();
        TodoTable receivedTable = null;

        // 수신된 JSON파일을 TodoTable로 변환
        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        String content = "";

        try {
            bufferedReader =  request.getReader();
            char[] charBuffer = new char[128];
            int bytesRead;
            while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
                sb.append(charBuffer, 0, bytesRead);
            }

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        content = sb.toString();
        System.out.println(content);
        JSONParser parser = new JSONParser();
        JSONObject theTableObject = null;
        try {
            theTableObject = (JSONObject) parser.parse(content);
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(content);
        receivedTable = new TodoTable(theTableObject);
        System.out.println(receivedTable.toJson());

        // 변환된 TodoTable을 데이터베이스에 저장
        if(dao.updateTodo(receivedTable) == 1) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("Success");
            out.flush();
        };

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
