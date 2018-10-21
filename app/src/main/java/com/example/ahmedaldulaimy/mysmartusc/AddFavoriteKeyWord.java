package com.example.ahmedaldulaimy.mysmartusc;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class AddFavoriteKeyWord extends AppCompatActivity {

    public String[] favoriteKeywords;
    public String[] urgentKeywords;
    public String[] emailAddresses;

    public void makeFavorite(boolean c) {

    };

    // Add favorite keyword.
    public void addKeyword(String keyword) {
        favoriteKeywords = new String[favoriteKeywords.length + 1];
        favoriteKeywords[favoriteKeywords.length] = keyword;
    };

    // Remove favorite keyword.
    public void removeKeyword() {

    };


    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite_keyword);


        ListView listView = (ListView)findViewById(R.id.listOfFavoriteKeywords);


        myDB = new DatabaseHelper(this);


        //populate an ArrayList<String> from the database and then view it
       ArrayList<String> theList = new ArrayList<>();
       //theList.add("NULL");
        Cursor data = myDB.getFavoriteKeywords();
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


