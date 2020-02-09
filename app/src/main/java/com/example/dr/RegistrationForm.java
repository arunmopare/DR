package com.example.dr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegistrationForm extends AppCompatActivity {
    CheckBox thirsty_coders,thugs_of_codistan,hackathon, bug_off, hack_the_mystery, beats, box_office, stranger_things, secret_room, fifa, cs, may_mayers, bulls_eye, food_tycon, twister, box_and_lies, spot_photo;
    EditText my_name_text;
    EditText my_mobile_number;
    DatabaseReference databaseRegistration,databaseRegistrationcount;
    FirebaseAuth auth;

    String str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        auth = FirebaseAuth.getInstance();

        thirsty_coders = findViewById(R.id.thirsty_coders);
        thugs_of_codistan= findViewById(R.id.thugs_of_codistan);
        hackathon= findViewById(R.id.hackathon);
        bug_off= findViewById(R.id.bug_off);
        hack_the_mystery= findViewById(R.id.hack_the_mystery);
        beats= findViewById(R.id.beats);
        box_office= findViewById(R.id.box_office);
        stranger_things= findViewById(R.id.stranger_things);
        secret_room= findViewById(R.id.secret_room);
        fifa= findViewById(R.id.fifa);
        cs= findViewById(R.id.cs);
        may_mayers= findViewById(R.id.may_mayers);
        bulls_eye= findViewById(R.id.bulls_eye);
        food_tycon= findViewById(R.id.food_tycon);
        twister= findViewById(R.id.twister);
        box_and_lies= findViewById(R.id.box_and_lies);
        spot_photo= findViewById(R.id.spot_photo);
        my_name_text = findViewById(R.id.my_name);
        my_mobile_number = findViewById(R.id.my_mobile_number);

    }
    public void submit_registration(View view){
        final String stringName = my_name_text.getText().toString().trim();
        final String stringMobile = my_mobile_number.getText().toString().trim();
        boolean validation= checkValidDeta(stringName,stringMobile);
        int n=11;

         if (stringMobile.length()!=10){
            Toast.makeText(RegistrationForm.this, "Please Enter 10 digit Mobile Number", Toast.LENGTH_SHORT).show();
            my_mobile_number.setError("Please Enter 10 digit Mobile Number");
            if (stringName.length()==0){
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            }
            validation =false;
            return;
        }

        if(validation){
            if (thirsty_coders.isChecked()){
                addRegistration("Thirsty Coders",stringName,stringMobile);

            }

            if (thugs_of_codistan.isChecked()){

                addRegistration("Thugs Of Codistan",stringName,stringMobile);
            }

            if (hackathon.isChecked()){

                addRegistration("Hackathon",stringName,stringMobile);
            }

            if (bug_off.isChecked()){

                addRegistration("Bug Off",stringName,stringMobile);
            }

            if (hack_the_mystery.isChecked()) {

                addRegistration("Hack The Mystery",stringName,stringMobile);
            }
            if (beats.isChecked()){

                addRegistration("Beats",stringName,stringMobile);
            }

            if (stranger_things.isChecked()){

                addRegistration("Stranger Things",stringName,stringMobile);
            }

            if (secret_room.isChecked()){

                addRegistration("Secret Room",stringName,stringMobile);
            }

            if (fifa.isChecked()){

                addRegistration("FIFA",stringName,stringMobile);
            }

            if (cs.isChecked()){

                addRegistration("CS",stringName,stringMobile);
            }

            if (may_mayers.isChecked()){

                addRegistration("May Mayers",stringName,stringMobile);
            }

            if (bulls_eye.isChecked()){

                addRegistration("Bulls Eye",stringName,stringMobile);
            }

            if (food_tycon.isChecked()){

                addRegistration("Food Tycon",stringName,stringMobile);
            }

            if (twister.isChecked()){

                addRegistration("Twister",stringName,stringMobile);
            }

            if (box_and_lies.isChecked()){

                addRegistration("Box and Lies",stringName,stringMobile);
            }

            if (spot_photo.isChecked()){

                addRegistration("Spot Photo",stringName,stringMobile);
            }

            if (box_office.isChecked()){

                addRegistration("Box Office",stringName,stringMobile);
            }
        }
    }
    public void launchList(View view){
        Intent intent = new Intent(RegistrationForm.this,RegistrationList.class );
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }

    public void goMainActivity(View view){
        Intent intent = new Intent(RegistrationForm.this,MainActivity.class );
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }
    public Boolean checkValidDeta(String name_str, String mobile_numer){
        if(name_str.isEmpty() && mobile_numer.isEmpty()){
            my_name_text.setError("Cannot be Empty");
            my_mobile_number.setError("Cannot Be Empty");
            Toast.makeText(RegistrationForm.this, "Enter Name And Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        }

        else return true;
    }

    public void addRegistration(String eventName, String strName,String mobNo){
        databaseRegistration = FirebaseDatabase.getInstance().getReference(eventName);
        databaseRegistrationcount = FirebaseDatabase.getInstance().getReference("Counts");
        String id = databaseRegistration.push().getKey();
        MyRegistration registration = new MyRegistration(eventName,mobNo,strName);
        databaseRegistration.child(id).setValue(registration).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                my_name_text.getText().clear();
                my_mobile_number.getText().clear();
                Toast.makeText(RegistrationForm.this, "SuccessFully Registered", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationForm.this, "Check InterNet", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
