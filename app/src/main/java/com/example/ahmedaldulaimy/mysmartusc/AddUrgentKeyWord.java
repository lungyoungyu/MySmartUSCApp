package com.example.ahmedaldulaimy.mysmartusc;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;



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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_urgent_keyword);

        Bundle extras = getIntent().getExtras();
        listUrgentKeyWords = extras.getStringArray("myUrgentWord");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, listUrgentKeyWords);

        ListView listView = (ListView)findViewById(R.id.listOfUrgentKeywords);
        listView.setAdapter(adapter);


    }

}

