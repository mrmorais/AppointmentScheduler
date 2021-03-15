package br.ufrn.imd.appointmentscheduler.ms.notification.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "url")
    private String url;

    @Column(name = "is_read")
    private boolean isRead;
    
    @Column(name = "id_user")
    private int userId;

//    @ManyToOne
//    @JoinColumn(name = "id_user")
//    private User user;

    public Notification() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user) {
        this.userId = user;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

}
