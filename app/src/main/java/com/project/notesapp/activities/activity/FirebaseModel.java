package com.project.notesapp.activities.activity;

public class FirebaseModel {

    public   String title;

    public   String content;


    public  String  dateTime;
    public FirebaseModel()
    {

    }
    public FirebaseModel(String title,String subTitle,String content)
    {
        this.title= title;
        this.content = content;

    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public  String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

