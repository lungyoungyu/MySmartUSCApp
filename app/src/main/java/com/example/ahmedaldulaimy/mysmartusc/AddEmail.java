package com.example.ahmedaldulaimy.mysmartusc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.util.Log;
import android.database.Cursor;
import android.widget.Toast;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;
public class AddEmail extends AppCompatActivity {

    public String[] favoriteKeywords;
    public String[] urgentKeywords;
    public String[] emailAddresses;

    public void makeUrgent(boolean c) {

    };

    public void makeFavorite(boolean c) {

    };

    public void addEmail() {
    };

    public void removeEmail() {
    };

    public void addKeyword(String keyword) {
    };

    public void removeKeyword() {
    };


    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_email);

        ListView listView = (ListView)findViewById(R.id.listOfEmailAddresses);

        myDB = new DatabaseHelper(this);


        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getImportantEmails();
        //MyCustomAdapter adapter = new MyCustomAdapter();
        System.out.println("this got called 11111111111111111");
        //if database is empty
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            //this extract data from the database and adds it to the list to display
            while(data.moveToNext()){

                theList.add(data.getString(1));

            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, theList);
        listView.setAdapter(adapter);
    }

}
