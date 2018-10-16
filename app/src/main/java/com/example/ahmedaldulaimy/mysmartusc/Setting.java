package com.example.ahmedaldulaimy.mysmartusc;

import android.app.Notification;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Setting {

    public String[] favoriteKeywords;
    public String[] urgentKeywords;
    public String[] emailAddresses;

    public void makeUrgent(boolean c){};
    public void makeFavorite(boolean c){};

    public void addEmail(){};
    public void removeEmail(){};

    public void addKeyword(String keyword){};
    public void removeKeyword(){};


    public void logout(){

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .build();
//
//        GoogleSignInClient user = GoogleSignIn.getClient(this, gso);
//        user.signOut();

    };

    public Notification createMsg(){return null; };

}

