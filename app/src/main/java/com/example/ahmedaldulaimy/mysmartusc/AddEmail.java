package com.example.ahmedaldulaimy.mysmartusc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.EditText;
import android.util.Log;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListAdapter;
import android.graphics.Paint;

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
    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_email);

        final ListView listView = (ListView)findViewById(R.id.listOfEmailAddresses);

        final TextView textview = (TextView) findViewById(R.id.emailTextView);
        textview.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        myDB = new DatabaseHelper(this);


        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getImportantEmails();
        //MyCustomAdapter adapter = new MyCustomAdapter();
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
                    //Log.i("Item selected ", listItem);

                    //String pos = String.valueOf(position+1);
                    myDB.removeEmailData(listItem);

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
