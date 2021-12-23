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

        String sql = "SELECT * from " + targetTable;
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

                    switch (status) {
                        case "todo":
                            theTable.getTodo().getItemList().add(sequence, item);
                            break;
                        case "doing":
                            theTable.getDoing().getItemList().add(sequence, item);
                            break;
                        case "done":
                            theTable.getDone().getItemList().add(sequence, item);
                            break;
                        default:
                            System.out.println("unknown item read.");
                    }

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


    public int updateTodo(String targetTable, TodoTable newTable) {
        TodoTable theTable = new TodoTable(targetTable);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * from " + targetTable;
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

                    switch (status) {
                        case "todo":
                            theTable.getTodo().getItemList().add(sequence, item);
                            break;
                        case "doing":
                            theTable.getDoing().getItemList().add(sequence, item);
                            break;
                        case "done":
                            theTable.getDone().getItemList().add(sequence, item);
                            break;
                        default:
                            System.out.println("unknown item read.");
                    }

                    System.out.println(item);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 1;
    }
}
