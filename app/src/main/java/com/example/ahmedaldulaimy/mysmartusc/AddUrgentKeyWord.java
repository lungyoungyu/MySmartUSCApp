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


public class AddUrgentKeyWord extends AppCompatActivity {

    public String[] favoriteKeywords;
    public String[] urgentKeywords;
    public String[] emailAddresses;

    public void makeUrgent(boolean c) {
    };

    public void addKeyword(String keyword) {
    };

    public void removeKeyword() {
    };

    // Array of strings...
    String[] listUrgentKeyWords = {"11","22","33"};
    DatabaseHelper myDB;
    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_urgent_keyword);
//
////        Bundle extras = getIntent().getExtras();
////        listUrgentKeyWords = extras.getStringArray("myUrgentWord");
////
////        ArrayAdapter adapter = new ArrayAdapter<String>(this,
////                R.layout.list_item, listUrgentKeyWords);
//
//       final ListView listView = (ListView)findViewById(R.id.listOfUrgentKeywords);
//
//
//        final TextView textview = (TextView) findViewById(R.id.urgentWordTextView);
//        textview.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
////        listView.setAdapter(adapter);
//
//        myDB = new DatabaseHelper(this);
//        ArrayList<String> theList = new ArrayList<>();
//        //theList.add("NULL");
//        Cursor data = myDB.getUrgentKeywords();
//
//        //MyCustomAdapter adapter = new MyCustomAdapter();
//
//        //if database is empty
//        if(data.getCount() == 0){
//            //Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
//        }else{
//            //this extract data from the database and adds it to the list to display
//            while(data.moveToNext()){
//
//                theList.add(data.getString(1));
//
//            }
//        }
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                lastTouchTime = currentTouchTime;
//                currentTouchTime = System.currentTimeMillis();
//
//                if (currentTouchTime - lastTouchTime < 250) {
//                    Log.d("Duble","Click");
//                    lastTouchTime = 0;
//                    currentTouchTime = 0;
//
//                    String listItem = listView.getItemAtPosition(position).toString();
//                    //Log.i("Itom selected ", listItem);
//
//                    //String pos = String.valueOf(position+1);
//                    myDB.removeUrgentKeyWordData(listItem);
//
//                    finish();
//                    startActivity(getIntent());
//                }
//            }
//        });
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.list_item, theList);
//        listView.setAdapter(adapter);
    }

}

