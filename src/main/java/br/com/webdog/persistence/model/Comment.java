package br.com.webdog.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String text;
    private Date date;
    private boolean status;
    private boolean isAnswer;

    @OneToOne
    private Comment reply;

    @ManyToOne
    private Host host;

    @ManyToOne
    private User user;

    public Comment() {
    }

    public Comment(String text, Date date, boolean status, boolean isAnswer, Comment reply, Host host, User user) {
        this.text = text;
        this.date = date;
        this.status = status;
        this.isAnswer = false;
        this.reply = reply;
        this.host = host;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public Comment getReply() {
        return reply;
    }

    public void setReply(Comment reply) {
        this.reply = reply;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
