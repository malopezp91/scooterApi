package com.miguel.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="SCOOTER_ID")
    private Scooter scooter;
    private Action action;
    private String user;
    private String comment;
    private Long time;

    public Event() {}

    public Event(Action action, String user, String comment) {
        this.action = action;
        this.user = user;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }
}
