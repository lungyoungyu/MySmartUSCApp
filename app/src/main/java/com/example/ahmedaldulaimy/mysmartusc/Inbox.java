package com.example.ahmedaldulaimy.mysmartusc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Inbox extends AppCompatActivity {

    public Email[] emailList;
    public Setting settings;
    public Email[] listOfFavorites;

    public void removeEmail(){};
    public void goToSettings(){};
    public void sortEmails(){};
    public void fetchEmails(){};
    public void notifyUser(){};

    public void validateFavorite(){};
    public void validateUrgent(){};

    public boolean filterFavorite(){ return true; };
    public boolean filterUrgent(){ return true; };
    public boolean filterSender(){ return true; };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, message.split("\n"));

        // Capture the layout's TextView and set the string as its text
        ListView listOfEmails = (ListView) findViewById(R.id.listOfEmails);
        listOfEmails.setAdapter(adapter);

    }
}
