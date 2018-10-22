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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Inbox extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<String> emailList = new ArrayList<>();
    ArrayList<String> urgentWordList = new ArrayList<>();
    ArrayList<String> favoriteWordList = new ArrayList<>();
    ArrayList<Email> allEmails = new ArrayList<>();
    HashMap<Integer, Boolean> favoriteMap = new HashMap<>(); // Map of email index to True/False if contains favorite keyword
    HashMap<Integer, Boolean> urgentMap = new HashMap<>(); // Map of email index to True/False if contains urgent keyword

    public void removeEmail(){
    };

    // Sort emails by date from most recent to oldest.
    public void sortEmails(){
    };

    public void notifyUser(){
    };

    // Check emails that are marked as favorite. (Marks them as true in favoriteMap)
    public void validateFavorite(){
        for(int i = 0; i < allEmails.size(); i++) {

            // Email Subject Line
            String subject = allEmails.get(i).getSubject();
            String[] subjectWords = subject.split("\\s+");
            // Take out punctuation
            for (int j = 0; j < subjectWords.length; j++) {
                subjectWords[j] = subjectWords[j].replaceAll("[^\\w]", "");
            }


            for (int k = 0; k < subjectWords.length; k++) {
                for (int l = 0; l < favoriteWordList.size(); l++) {
                    if (subjectWords[k].equals(favoriteWordList.get(l))) {
                        favoriteMap.put(i, true);
                        break;
                    }
                }
            }

            // Email Contents
            String content = allEmails.get(i).getContent();
            String[] contentWords = content.split("\\s+");
            // Take out punctuation
            for (int j = 0; j < contentWords.length; j++) {
                contentWords[j] = contentWords[j].replaceAll("[^\\w]", "");
            }

            for (int k = 0; k < contentWords.length; k++) {
                for (int l = 0; l < favoriteWordList.size(); l++) {
                    if (contentWords[k].equals(favoriteWordList.get(l))) {
                        favoriteMap.put(i, true);
                        break;
                    }
                }
            }
        }
    };

    // Highlight emails with urgent as red.
    public void validateUrgent(){
        for(int i = 0; i < allEmails.size(); i++) {

            // Email Subject Line
            String subject = allEmails.get(i).getSubject();
            String[] subjectWords = subject.split("\\s+");
            // Take out punctuation
            for (int j = 0; j < subjectWords.length; j++) {
                subjectWords[j] = subjectWords[j].replaceAll("[^\\w]", "");
            }


            for (int k = 0; k < subjectWords.length; k++) {
                for (int l = 0; l < urgentWordList.size(); l++) {
                    if (subjectWords[k].equals(urgentWordList.get(l))) {
                        urgentMap.put(i, true);
                        break;
                    }
                }
            }

            // Email Contents
            String content = allEmails.get(i).getContent();
            String[] contentWords = content.split("\\s+");
            // Take out punctuation
            for (int j = 0; j < contentWords.length; j++) {
                contentWords[j] = contentWords[j].replaceAll("[^\\w]", "");
            }

            for (int k = 0; k < contentWords.length; k++) {
                for (int l = 0; l < urgentWordList.size(); l++) {
                    if (contentWords[k].equals(urgentWordList.get(l))) {
                        urgentMap.put(i, true);
                        break;
                    }
                }
            }
        }
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

    public void populateInboxView() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        populateInboxView();

    }

    @Override
    public void onResume() {
        super.onResume();
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

        List<Email> allEmails = new ArrayList<Email>();

        for(int i = 0; i < adapter.getCount(); i++) {
            allEmails.add((Email)adapter.getItem(i));
        }


    }

//    public class ListAdapter extends ArrayAdapter<Email> {
//
//        private int resourceLayout;
//        private Context mContext;
//
//        public ListAdapter(Context context, int resource, List<Email> items) {
//            super(context, resource, items);
//            this.resourceLayout = resource;
//            this.mContext = context;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            View v = convertView;
//
//            if (v == null) {
//                LayoutInflater vi;
//                vi = LayoutInflater.from(mContext);
//                v = vi.inflate(resourceLayout, null);
//            }
//
//            Email p = getItem(position);
//
//            if (p != null) {
//                TextView tt1 = (TextView) v.findViewById(R.id.id);
//                TextView tt2 = (TextView) v.findViewById(R.id.categoryId);
//                TextView tt3 = (TextView) v.findViewById(R.id.description);
//
//                if (tt1 != null) {
//                    tt1.setText(p.getId());
//                }
//
//                if (tt2 != null) {
//                    tt2.setText(p.getCategory().getId());
//                }
//
//                if (tt3 != null) {
//                    tt3.setText(p.getDescription());
//                }
//            }
//
//            return v;
//        }
//
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
