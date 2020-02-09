package com.example.dr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseRegistrationcount;
    FirebaseAuth auth;
    ProgressBar progressBar;
    long maxid;
    TextView thirsty_coders,thugs_of_codistan,hackathon, bug_off, hack_the_mystery, beats, box_office, stranger_things, secret_room, fifa, cs, may_mayers, bulls_eye, food_tycon, twister, box_and_lies, spot_photo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LogIn.class));
                    finish();
                }
            }
        };

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar4);
        thirsty_coders = findViewById(R.id.count_thursty_coders);
        thugs_of_codistan= findViewById(R.id.count_thugs_of_codistan);
        hackathon= findViewById(R.id.count_hackathon);
        bug_off= findViewById(R.id.count_bug_off);
        hack_the_mystery= findViewById(R.id.count_hack_the_mystrery);
        beats= findViewById(R.id.count_beats);
        box_office= findViewById(R.id.count_box_office);
        stranger_things= findViewById(R.id.count_stranger_things);
        secret_room= findViewById(R.id.count_secret_room);
        fifa= findViewById(R.id.count_fifa);
        cs= findViewById(R.id.count_cs);
        may_mayers= findViewById(R.id.count_may_mayers);
        bulls_eye= findViewById(R.id.count_bulls_eye);
        food_tycon= findViewById(R.id.count_food_tycon);
        twister= findViewById(R.id.count_twister);
        box_and_lies= findViewById(R.id.count_box_lies);
        spot_photo= findViewById(R.id.code_spot_photo);

//         <array name="Events">
//        <item>Thirsty Coders</item>
//        <item>Thugs Of Codistan</item>
//        <item>Hackathon</item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item>Secret Room</item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item></item>
//        <item></item>
//    </array>

        //setCountText("",secret_room);
        setCountText("Secret Room",secret_room);
        setCountText("Thirsty Coders",thirsty_coders);
        setCountText("Thugs Of Codistan",thugs_of_codistan);
        setCountText("Hackathon",hackathon);
        setCountText("Bug Off",bug_off);
        setCountText("Hack The Mystery",hack_the_mystery);
        setCountText("Beats",beats);
        setCountText("Stranger Things",stranger_things);
        setCountText("CS",cs);
        setCountText("FIFA",fifa);
        setCountText("May Mayers",may_mayers);
        setCountText("Bulls Eye",bulls_eye);
        setCountText("Food Tycon",food_tycon);
        setCountText("Twister",twister);
        setCountText("Box and Lies",box_and_lies);
        setCountText("Spot Photo",spot_photo);
        setCountText("Box Office",box_office);
        //progressBar.setVisibility(View.GONE);

    }

    private void setCountText(String event,final TextView textView) {
        databaseRegistrationcount = FirebaseDatabase.getInstance().getReference(event);
        databaseRegistrationcount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxid = dataSnapshot.getChildrenCount();
                textView.setText(String.valueOf(maxid));
                textView.setTextColor(Color.GREEN);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50);
               progressBar.setVisibility(View.GONE);
               // dontTxt.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void launchList(View view){
        Intent intent = new Intent(MainActivity.this,RegistrationList.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }
    public void goRegistrationForm(View view){
        Intent intent = new Intent(MainActivity.this,RegistrationForm.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }
    public void logout(View v){

        new AlertDialog.Builder(this)
                .setTitle("Do You Want To LogOut ?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        auth.signOut();
                        startActivity(new Intent(MainActivity.this, LogIn.class));
                        finish();
                    }
                }).create().show();



    }
}
