package dto;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<TodoItem> itemList;

    public TodoList() {
        this.itemList = new ArrayList<TodoItem>();
    }
    public TodoList(List<TodoItem> todoList) {
        this.itemList = todoList;
    }

    public List<TodoItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TodoItem> _itemList) {
        this.itemList = _itemList;
    }

    @Override
    public String toString() {
        return "dto.TodoList{" +
                "itemList=" + itemList +
                '}';
    }

    public String toJson() {
        String listJson = "";
        listJson += "[";
        for(int i = 0; i < itemList.size(); i++) {
            listJson += itemList.get(i).toJson();
            if(i != itemList.size() - 1)
                listJson += ", ";
        }
        listJson += "]";
        return listJson;
    }
}
