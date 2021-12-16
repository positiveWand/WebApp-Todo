package dto;


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
}
