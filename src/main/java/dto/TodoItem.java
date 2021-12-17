package dto;

import java.sql.Timestamp;
import java.util.Date;

public class TodoItem {
    private int id;
    private String title;
    private String description;
    private Timestamp createTime;

    public TodoItem() {
        this.id = -1;
        this.title = "default name";
        this.description = "default description";
        this.createTime = new Timestamp(0);
    }
    public TodoItem(int _id, String _title, String _description, Timestamp _createDate) {
        this.id = _id;
        this.title = _title;
        this.description = _description;
        this.createTime = _createDate;
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
    public Date getCreateTime() {
        return createTime;
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
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "dto.TodoItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String toJson() {
        return "{" +
                '"'+"id"+'"'+" : " + this.id + ", " +
                '"'+"title"+'"'+" : "+ '"' + this.title + '"' + ", " +
                '"'+"description"+'"'+" : "+ '"' + this.description + '"' + ", " +
                '"'+"createTime"+'"'+" : "+ '"' + this.createTime + '"' +
                "}";
    }
}
