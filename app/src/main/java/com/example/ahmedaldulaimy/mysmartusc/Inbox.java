package com.example.ahmedaldulaimy.mysmartusc;

import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.view.View;


public class Inbox extends AppCompatActivity {

    public Email[] emailList;
    public Setting settings;
    public Email[] listOfFavorites;

    public void removeEmail(){
    };

    public void goToSettings(){
    };

    // Sort emails by date from most recent to oldest.
    public void sortEmails(){
    };

    public void fetchEmails(){

    };

    public void notifyUser(){
    };

    // Highlight emails with favorites as green.
    public void validateFavorite(){

        // Go through list of emails and find if they are favorites
        for(int i = 0; i < emailList.length; i++) {
            for(int j = 0; j < listOfFavorites.length; j++) {
                if(emailList[i].equals(listOfFavorites[j])) {
                    System.out.println(listOfFavorites[j] + " ");
                }
            }
        }
    };

    // Highlight emails with urgent as red.
    public void validateUrgent(){

    };

    // Filter emails based on only those marked as favorites (display only favorites).
    public boolean filterFavorite(){
        return true;
    };

    // Filter emails based on only those marked as urgent (display only urgents).
    public boolean filterUrgent(){
        return true;
    };

    // Filter emails based on only those sent by specified email addresses (display only specified email addresses).
    public boolean filterSender(){
        return true;
    };

    FloatingActionButton faButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);


        // add a onclick Listener to the + sign icon
        faButton = (FloatingActionButton)findViewById(R.id.plus_sign_icon);

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start NewActivity.class
                Intent myIntent = new Intent(Inbox.this,
                        Setting.class);
                startActivity(myIntent);
            }
        });

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        Email[] emails = (Email[]) intent.getSerializableExtra("emails");

        ArrayAdapter adapter = new ArrayAdapter<Email>(this, R.layout.list_item, emails);

        // Capture the layout's TextView and set the string as its text
        ListView listOfEmails = (ListView) findViewById(R.id.listOfEmails);
        listOfEmails.setAdapter(adapter);

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setContentView(R.layout.activity_inbox);
//
//
//
//        // validateFavorite();
//    }


}
