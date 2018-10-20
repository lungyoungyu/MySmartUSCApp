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

    // Array of strings...
    String[] listFavoriteKeyWords = {"11","22","33", "TestKeyword"};
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite_keyword);


//        Bundle extras = getIntent().getExtras();
//        listFavoriteKeyWords = extras.getStringArray("myFavoriteWord");
//
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.list_item, listFavoriteKeyWords);
//
        ListView listView = (ListView)findViewById(R.id.listOfFavoriteKeywords);
//        listView.setAdapter(adapter);



        myDB = new DatabaseHelper(this);


        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getFavoriteKeywords();
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

    }

}


