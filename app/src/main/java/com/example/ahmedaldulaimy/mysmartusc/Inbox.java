package com.example.ahmedaldulaimy.mysmartusc;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class Inbox extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<String> emailList = new ArrayList<>();
    ArrayList<String> urgentWordList = new ArrayList<>();
    ArrayList<String> favoriteWordList = new ArrayList<>();

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
        for(int i = 0; i < emailList.size(); i++) {
            for(int j = 0; j < favoriteWordList.size(); j++) {
                if(emailList.get(i).equals(favoriteWordList.get(j))) {
                    System.out.println(favoriteWordList.get(j) + " ");
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

         FetchEmailsFromDB();
        FetchFavoriteKeyWordsFromDB();
        FetchUrgentKeyWordsFromDB();

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


    public void FetchEmailsFromDB(){
        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getImportantEmails();
        //if database is empty
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            //this extract data from the database and adds it to the list to display
            while(data.moveToNext()){

                emailList.add(data.getString(1));

            }
        }

        //check if succesfully got all the eamil adressess from DB
        for(int i= 0; i<emailList.size(); i++){
            Log.v("Emails from DB ",  emailList.get(i));
        }

    }

    public void FetchFavoriteKeyWordsFromDB(){
        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getFavoriteKeywords();
        //if database is empty
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            //this extract data from the database and adds it to the list to display
            while(data.moveToNext()){

                favoriteWordList.add(data.getString(1));

            }
        }

        //check if succesfully got all the eamil adressess from DB
        for(int i= 0; i<favoriteWordList.size(); i++){
            Log.v("fav words from DB ",  favoriteWordList.get(i));
        }

    }

    public void FetchUrgentKeyWordsFromDB(){

        myDB = new DatabaseHelper(this);
        Cursor data = myDB.getUrgentKeywords();
        //if database is empty
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            //this extract data from the database and adds it to the list to display
            while(data.moveToNext()){

                urgentWordList.add(data.getString(1));

            }
        }

        //check if succesfully got all the eamil adressess from DB
        for(int i= 0; i<urgentWordList.size(); i++){
            Log.v("Urgent words from DB ",  urgentWordList.get(i));
        }

    }

}
