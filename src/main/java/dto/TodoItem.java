package dto;

import java.util.Date;

public class TodoItem {
    private String title;
    private String description;
    private Date createDate;

    public TodoItem() {
        this.title = "default name";
        this.description = "default description";
        this.createDate = new Date();
    }
    public TodoItem(String _title, String _description, Date _createDate) {
        this.title = _title;
        this.description = _description;
        this.createDate = _createDate;
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
