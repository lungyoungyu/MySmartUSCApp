package com.example.ahmedaldulaimy.mysmartusc;

import android.widget.Button;
import android.app.Notification;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import android.os.Bundle;
import android.widget.EditText;

public class Setting extends AppCompatActivity {

    public String[] favoriteKeywords;
    public String[] urgentKeywords;
    public String[] emailAddresses;

    public void makeUrgent(boolean c){};
    public void makeFavorite(boolean c){};

    public void addEmail(){};
    public void removeEmail(){};

    public void addKeyword(String keyword){};
    public void removeKeyword(){};

    DatabaseHelper myDB;
    Button email_button;
    Button fav_keyWord_button;
    Button urgent_keyWord_button;
    EditText mEdit1;
    EditText mEdit2;
    EditText mEdit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // add an onclick Listener to the + sign icon
        email_button = (Button) findViewById(R.id.email_button);
        urgent_keyWord_button = (Button) findViewById(R.id.urgent_word_button);
        fav_keyWord_button = (Button) findViewById(R.id.favorite_word_button);
        myDB = new DatabaseHelper(this);
        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEdit1 = (EditText)findViewById(R.id.email_address);
                String mEditString = Setting.this.mEdit1.getText().toString();
                // Log.v("EditText", mEdit.getText().toString());
                emailAddresses = mEditString.split(" ");
                if(mEdit1.length() != 0){
                    for(int i = 0; i<emailAddresses.length; i++){
                        Log.i("EditText AS ARRAY ", emailAddresses[i]);
                        addImportantEmail(emailAddresses[i]);
                        //AddData(mEditString.toString());
                    }

                }
                else {
                    Toast.makeText(Setting.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }



               // Log.i("EditText AS ARRAY ", emailAddresses.toString() );
                // Start NewActivity.class
                Intent myIntent = new Intent(Setting.this, AddEmail.class);
//               // myIntent.putExtra("myEmail", emailAddresses);
                startActivity(myIntent);
            }
        });



        urgent_keyWord_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEdit2 = (EditText)findViewById(R.id.urgent_word);
                String mEditString = Setting.this.mEdit2.getText().toString();
                Log.v("EditText22/Urgent", mEdit2.getText().toString());
                urgentKeywords = mEditString.split(" ");

                if(mEdit2.length() != 0){
                    for(int i = 0; i<urgentKeywords.length; i++){

                        addUrgentKeyword(urgentKeywords[i]);
                        //AddData(mEditString.toString());
                    }

                }
                else {
                    Toast.makeText(Setting.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }


                // Start NewActivity.class
                Intent myIntent = new Intent(Setting.this, AddUrgentKeyWord.class);
                //myIntent.putExtra("myUrgentWord", urgentKeywords);
                startActivity(myIntent);
            }
        });



        fav_keyWord_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEdit3 = (EditText)findViewById(R.id.favorite_word);

                String mEditString = Setting.this.mEdit3.getText().toString();

                Log.v("EditText33/favorite", mEdit3.getText().toString());
                favoriteKeywords = mEditString.split(" ");


                if(mEdit3.length() != 0){
                    for(int i = 0; i<favoriteKeywords.length; i++){

                        addFavoriteKeyword(favoriteKeywords[i]);
                        //AddData(mEditString.toString());
                    }

                }
                else {
                    Toast.makeText(Setting.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }


                // Start NewActivity.class
                Intent myIntent = new Intent(Setting.this, AddFavoriteKeyWord.class);
                //myIntent.putExtra("myFavoriteWord", favoriteKeywords);
                startActivity(myIntent);
            }
        });


    }

    public void addImportantEmail(String emailAddress) {

        Log.v("Adding to Database ",  emailAddress);
        boolean insertData = myDB.addImportantEmailData(emailAddress);

        Log.v("thisShould return ",  emailAddress);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }

    public void addUrgentKeyword(String keyword) {
        Log.v("Adding to Database ",  keyword);
        boolean insertData = myDB.addUrgentKeywordData(keyword);

        Log.v("thisShould return ",  keyword);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }

    public void addFavoriteKeyword(String keyword) {
        Log.v("Adding to Database ",  keyword);
        boolean insertData = myDB.addFavoriteKeywordData(keyword);

        Log.v("thisShould return ",  keyword);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }

    public void logout(){

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .build();
//
//        GoogleSignInClient user = GoogleSignIn.getClient(this, gso);
//        user.signOut();


    };

    public Notification createMsg(){return null; };

}

