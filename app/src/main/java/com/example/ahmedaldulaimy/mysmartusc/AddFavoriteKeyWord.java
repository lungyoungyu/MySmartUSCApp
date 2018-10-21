package com.example.ahmedaldulaimy.mysmartusc;


import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite_keyword);


        final ListView listView = (ListView)findViewById(R.id.listOfFavoriteKeywords);

        final TextView textview = (TextView) findViewById(R.id.favWordTextView);
        textview.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        myDB = new DatabaseHelper(this);


        //populate an ArrayList<String> from the database and then view it
       ArrayList<String> theList = new ArrayList<>();
       //theList.add("NULL");
        Cursor data = myDB.getFavoriteKeywords();
        //MyCustomAdapter adapter = new MyCustomAdapter();
        //System.out.println("this got called 11111111111111111");
        //if database is empty
        if(data.getCount() == 0){
            //Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            //this extract data from the database and adds it to the list to display
            while(data.moveToNext()){

                theList.add(data.getString(1));

            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                if (currentTouchTime - lastTouchTime < 250) {
                    Log.d("Duble","Click");
                    lastTouchTime = 0;
                    currentTouchTime = 0;

                    String listItem = listView.getItemAtPosition(position).toString();
                    //Log.i("Itom selected ", listItem);

                    //String pos = String.valueOf(position+1);
                    myDB.removeFavoriteKeyWordData(listItem);

                    finish();
                    startActivity(getIntent());
                }

            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, theList);
        listView.setAdapter(adapter);

    }

}


