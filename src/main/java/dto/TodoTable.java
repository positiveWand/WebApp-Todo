package dto;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TodoTable {
    private String name;
    private TodoList todo;
    private TodoList doing;
    private TodoList done;

    public TodoTable() {
        this.name = "empty table";
        this.todo = new TodoList();
        this.doing = new TodoList();
        this.done = new TodoList();
    }
    public TodoTable(String _name) {
        this.name = _name;
        this.todo = new TodoList();
        this.doing = new TodoList();
        this.done = new TodoList();
    }
    public TodoTable(String _name, TodoList _todo, TodoList _doing, TodoList _done) {
        this.name = _name;
        this.todo = _todo;
        this.doing = _doing;
        this.done = _done;
    }
    public TodoTable(JSONObject tableJSON) {
        this.name = (String) tableJSON.get("name");
        this.todo = new TodoList();
        this.doing = new TodoList();
        this.done = new TodoList();

        for(int i = 0; i < ((JSONArray) tableJSON.get("todo")).size(); i++) {
            this.todo.getItemList().add(objectToItem((JSONObject) ((JSONArray) tableJSON.get("todo")).get(i)));
        }
        for(int i = 0; i < ((JSONArray) tableJSON.get("doing")).size(); i++) {
            this.doing.getItemList().add(objectToItem((JSONObject) ((JSONArray) tableJSON.get("doing")).get(i)));
        }
        for(int i = 0; i < ((JSONArray) tableJSON.get("done")).size(); i++) {
            this.done.getItemList().add(objectToItem((JSONObject) ((JSONArray) tableJSON.get("done")).get(i)));
        }
    }
    private TodoItem objectToItem(JSONObject theObject) {
        TodoItem theItem = new TodoItem(Long.valueOf((long) theObject.get("id")).intValue(), (String) theObject.get("title"), (String) theObject.get("description"), (Timestamp) Timestamp.valueOf((String) theObject.get("createTime")));
        return theItem;
    }

    public String getName() {
        return name;
    }
    public TodoList getTodo() {
        return todo;
    }
    public TodoList getDoing() {
        return doing;
    }
    public TodoList getDone() {
        return done;
    }

    public void setName(String _name) {
        this.name = _name;
    }
    public void setTodo(TodoList _todo) {
        this.todo = _todo;
    }
    public void setDoing(TodoList _doing) {
        this.doing = _doing;
    }
    public void setDone(TodoList _done) {
        this.done = _done;
    }

    @Override
    public String toString() {
        return "dto.TodoTable{" +
                "_name='" + name + '\'' +
                ", _todo=" + todo +
                ", _doing=" + doing +
                ", _done=" + done +
                '}';
    }

    public String toJson() {
        return "{ "+'"'+"name"+'"'+" : "+'"'+ this.name + '"' + ", " +
                '"'+"todo"+'"'+" : " + this.todo.toJson() + ", " +
                '"'+"doing"+'"'+" : " + this.doing.toJson() + ", " +
                '"'+"done"+'"'+" : " + this.done.toJson() +
                "}";
    }

    public String arrangeProperties() {
        String resultString = "";
        for(int i = 0; i < this.todo.getItemList().size(); i++) {
            String itemString = "(";
            itemString += this.todo.getItemList().get(i).getId() + ",";
            itemString += "'" + this.todo.getItemList().get(i).getTitle() + "'" + ",";
            itemString += "'" + this.todo.getItemList().get(i).getDescription() + "'" + ",";
            itemString += "'todo',";
            itemString += i + ",";
            itemString += "'" + this.todo.getItemList().get(i).getCreateTime() + "'";
            itemString += ")";
            resultString += itemString;

            if(i != this.todo.getItemList().size() - 1)
                resultString += ",";
        }
        if(this.todo.getItemList().size() != 0)
            resultString += ",";
        for(int i = 0; i < this.doing.getItemList().size(); i++) {
            String itemString = "(";
            itemString += this.doing.getItemList().get(i).getId() + ",";
            itemString += "'" + this.doing.getItemList().get(i).getTitle() + "'" + ",";
            itemString += "'" + this.doing.getItemList().get(i).getDescription() + "'" + ",";
            itemString += "'doing',";
            itemString += i + ",";
            itemString += "'" + this.doing.getItemList().get(i).getCreateTime() + "'";
            itemString += ")";
            resultString += itemString;

            if(i != this.doing.getItemList().size() - 1)
                resultString += ",";
        }
        if(this.todo.getItemList().size() + this.doing.getItemList().size() != 0 && this.done.getItemList().size() != 0)
            resultString += ",";
        for(int i = 0; i < this.done.getItemList().size(); i++) {
            String itemString = "(";
            itemString += this.done.getItemList().get(i).getId() + ",";
            itemString += "'" + this.done.getItemList().get(i).getTitle() + "'" + ",";
            itemString += "'" + this.done.getItemList().get(i).getDescription() + "'" + ",";
            itemString += "'done',";
            itemString += i + ",";
            itemString += "'" + this.done.getItemList().get(i).getCreateTime() + "'";
            itemString += ")";
            resultString += itemString;

            if(i != this.done.getItemList().size() - 1)
                resultString += ",";
        }

        System.out.println(resultString);
        return resultString;
    }
}
