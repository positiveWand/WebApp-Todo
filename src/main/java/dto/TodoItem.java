package dto;

import java.util.Date;

public class TodoItem {
    private int id;
    private String title;
    private String description;
    private Date createDate;

    public TodoItem() {
        this.id = -1;
        this.title = "default name";
        this.description = "default description";
        this.createDate = new Date();
    }
    public TodoItem(int _id, String _title, String _description, Date _createDate) {
        this.id = _id;
        this.title = _title;
        this.description = _description;
        this.createDate = _createDate;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "dto.TodoItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
