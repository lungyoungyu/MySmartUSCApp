package com.example.ahmedaldulaimy.mysmartusc;

import android.content.Intent;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.sql.DriverManager.println;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    public static final String EXTRA_MESSAGE = "com.example.ahmedaldulaimy.mysmartusc.MESSAGE";
    String CLIENT_ID = "897461513804-labvf1qmpspkn40ud3c33cphkrr2rajf.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(CLIENT_ID)
                .requestServerAuthCode(CLIENT_ID)
                .requestScopes(new Scope("https://mail.google.com/"))
                .requestEmail()
                .build();


        //        GoogleSignInClient signInClient = GoogleSignIn.getClient(this, gso);
//        signInClient.silentSignIn();

        mGoogleSignInClient =  GoogleSignIn.getClient(this, gso);
//        mGoogleSignInClient.signOut();

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);


    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            println("good");
            Log.w("tag", "signInResult:good login");
//

            GoogleTokenResponse tokenResponse =
                    new GoogleAuthorizationCodeTokenRequest(
                            new NetHttpTransport(),
                            JacksonFactory.getDefaultInstance(),
                            "https://www.googleapis.com/oauth2/v4/token",
                            "897461513804-labvf1qmpspkn40ud3c33cphkrr2rajf.apps.googleusercontent.com",
                            "sn9CcYqGZvtZbzj1PJhjxhgA",
                            account.getServerAuthCode(),
                            "")
                            .execute();

            String accessToken = tokenResponse.getAccessToken();

            GoogleTokenResponse response = new GoogleTokenResponse();
            response.setAccessToken(accessToken);

            GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);

            Gmail service = new Gmail.Builder(
                    AndroidHttp.newCompatibleTransport(), JacksonFactory.getDefaultInstance(), credential)
                    .setApplicationName("MySmartUSC")
                    .build();


            String user = "me";

            ListThreadsResponse listResponse = service.users().threads().list(user).execute();
            List<Thread> threads = listResponse.getThreads();
            String snippets = "";

            if (threads.isEmpty()) {
                System.out.println("No labels found.");
            } else {
                System.out.println("Labels:");
                for (Thread label : threads) {
                    System.out.printf("- %s\n", label.getSnippet());
                    snippets = snippets + label.getSnippet() + " \n";
                }
            }


            Intent intent = new Intent(this, Inbox.class);
            intent.putExtra(EXTRA_MESSAGE, snippets);
            startActivity(intent);

            // Signed in successfully, show authenticated UI.
//            updateUI(account);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("tag", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
            println("bad");

        }
        catch (IOException e) {
            Log.w("tag", "signInResult:failed message="+e.getMessage());

        }


    }



    @Override
    public void onClick(View v) {
        System.out.print("something was clicked");

        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                System.out.print("clicked on signIn");
                break;
            // ...
        }

    }

    private void signIn() {
        System.out.print("signing in...");

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


}
