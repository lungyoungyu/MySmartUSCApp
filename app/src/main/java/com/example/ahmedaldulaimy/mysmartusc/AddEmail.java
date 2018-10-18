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
    }

    ;

    public void makeFavorite(boolean c) {
    }

    ;

    public void addEmail() {
    }

    ;

    public void removeEmail() {
    }

    ;

    public void addKeyword(String keyword) {
    }

    ;

    public void removeKeyword() {
    }

    ;

    // Array of strings...
    String[] listEmailAddress = {"11","22","33", "kababjya@usc.edu", "Test@usc.edu", "hello"};
    //String[] listEmailAddress;

    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_email);

//        mEdit = (EditText)findViewById(R.id.email_address);
//        String mEditString = AddEmail.this.mEdit.getText().toString();
//        Log.v("EditText", mEdit.getText().toString());
       // listEmailAddress = mEditString.split(" ");

//        Bundle extras = getIntent().getExtras();
//        listEmailAddress = extras.getStringArray("myEmail");
//
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.list_item, listEmailAddress);

        ListView listView = (ListView)findViewById(R.id.listOfEmailAddresses);

        myDB = new DatabaseHelper(this);


        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        MyCustomAdapter adapter = new MyCustomAdapter();
        System.out.println("this got called 11111111111111111");
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                //ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.list_item, theList);

                adapter.SetList(theList);
                adapter.SetContext(this);
                String removedItem = adapter.GetRemovedItem();
                //if(removedItem != ""){
//                System.out.println("deleting data ");
//                myDB.removeData(removedItem);

                listView.setAdapter(adapter);
            }


        }

        //listView.setAdapter(adapter);



    }

}
