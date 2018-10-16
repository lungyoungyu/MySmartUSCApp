package com.example.ahmedaldulaimy.mysmartusc;
import java.util.Date;

public class Email {


    private String content;
    private String subject;
    private String sender;
    private Date date;


    public Email(String content, String subject, String sender, Date date) {
        this.content = content;
        this.subject = subject;
        this.sender = sender;
        this.date = date;
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
