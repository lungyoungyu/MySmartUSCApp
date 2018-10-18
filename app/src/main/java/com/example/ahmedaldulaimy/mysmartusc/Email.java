package com.example.ahmedaldulaimy.mysmartusc;
import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {


    private String content;
    private String subject;
    private String sender;
    private Date date;


    public Email(String content, String subject, String sender) {
        this.content = content;
        this.subject = subject;
        this.sender = sender;
        this.date = date;
    }

    public String toString(){
        return this.subject + "\n" + this.sender + "\n" +  this.content;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }




}
