package com.example.ahmedaldulaimy.mysmartusc;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class AddFavoriteKeyWord extends AppCompatActivity {

    public String[] favoriteKeywords;
    public String[] urgentKeywords;
    public String[] emailAddresses;

    public void makeFavorite(boolean c) {
    }

    ;

    public void addKeyword(String keyword) {
        favoriteKeywords = new String[favoriteKeywords.length + 1];
        favoriteKeywords[favoriteKeywords.length] = keyword;
    }

    public void removeKeyword() {

    }

    ;

    // Array of strings...
    String[] listFavoriteKeyWords = {"11","22","33", "TestKeyword"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite_keyword);


        Bundle extras = getIntent().getExtras();
        listFavoriteKeyWords = extras.getStringArray("myFavoriteWord");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, listFavoriteKeyWords);

        ListView listView = (ListView)findViewById(R.id.listOfFavoriteKeywords);
        listView.setAdapter(adapter);

    }

}


