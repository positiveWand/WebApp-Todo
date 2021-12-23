import dto.TodoItem;
import dto.TodoTable;

import java.sql.*;

public class TodoDao {
    private static String dbURL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
    private static String dbUser = "connectuser";
    private static String dbPassword = "connect123!@#";

    public int createTable() {
        return -1;
    }

    public TodoTable getTable(String targetTable) {
        TodoTable theTable = new TodoTable(targetTable);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * from " + targetTable + " where status=" + "'" + "todo" + "'" + " order by sequence";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    String description = rs.getString(3);
                    String status = rs.getString(4);
                    int sequence = rs.getInt(5);
                    Timestamp createTime = rs.getTimestamp(6);

                    TodoItem item = new TodoItem(id, title, description, createTime);

                    theTable.getTodo().getItemList().add(sequence, item);

                    System.out.println(item);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        sql = "SELECT * from " + targetTable + " where status=" + "'" + "doing" + "'" + " order by sequence";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    String description = rs.getString(3);
                    String status = rs.getString(4);
                    int sequence = rs.getInt(5);
                    Timestamp createTime = rs.getTimestamp(6);

                    TodoItem item = new TodoItem(id, title, description, createTime);

                    theTable.getDoing().getItemList().add(sequence, item);

                    System.out.println(item);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        sql = "SELECT * from " + targetTable + " where status=" + "'" + "done" + "'" + " order by sequence";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    String description = rs.getString(3);
                    String status = rs.getString(4);
                    int sequence = rs.getInt(5);
                    Timestamp createTime = rs.getTimestamp(6);

                    TodoItem item = new TodoItem(id, title, description, createTime);

                    theTable.getDone().getItemList().add(sequence, item);

                    System.out.println(item);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return theTable;
    }


    public int updateTodo(TodoTable newTable) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Table 삭제
        String sql = "DROP TABLE " + newTable.getName();
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Table 재생성
        sql = "CREATE TABLE " + newTable.getName() +
                "("+
                "id int NOT NULL DEFAULT 0," +
                "title varchar(255) NOT NULL DEFAULT '제목 없음'," +
                "description text," +
                "status varchar(10) NOT NULL DEFAULT 'todo'," +
                "sequence int NOT NULL DEFAULT 0," +
                "create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (id)" +
                ")";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Table 갱신
        sql = "INSERT INTO todo VALUES " + newTable.arrangeProperties();
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 1;
    }
}
