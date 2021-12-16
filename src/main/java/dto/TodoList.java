package dto;

import dto.TodoItem;

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
}
