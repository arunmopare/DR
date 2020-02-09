package com.example.dr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.sax.RootElement;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationList extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView_events;
    List<MyRegistration> registrationList;
    Spinner spinner_events_show_db;
    String path;
    private  String filename = "sample.txt";
    private String filePath = "MyFileStorage";
    File myExternalStorage;
    String myData = "";
    ProgressBar progressBar2;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list);
        progressBar2 = findViewById(R.id.progressBar6);

        spinner_events_show_db = (Spinner) findViewById(R.id.spinner_showdb);
        listView_events = (ListView) findViewById(R.id.listview_events_cs);
        registrationList = new ArrayList<>();

        spinner_events_show_db.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                path = spinner_events_show_db.getSelectedItem().toString().trim();
                databaseReference = FirebaseDatabase.getInstance().getReference(path);
                setPath();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                path="Thirsty Coders";
                setPath();
            }

        });
    }


    public void createFile (View view){
            String state;
            state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)){

               // File Root = Environment;
                File Dir = new File( getExternalFilesDir(null),"DR");
                if(!Dir.exists()){
                    Dir.mkdir();
                    Toast.makeText(this, "Directory Created"+Dir.getAbsolutePath(), Toast.LENGTH_LONG).show();
                }
                File file = new File(Dir,spinner_events_show_db.getSelectedItem().toString()+".txt");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    MyRegistration myRegistration = new MyRegistration();
                    int count=0;
                    for (MyRegistration a:registrationList){

                        count = count+1;
                        String temp = String.valueOf(count);
                        fileOutputStream.write(temp.getBytes());
                        fileOutputStream.write(".".getBytes());
                        fileOutputStream.write("\nName :-".getBytes());
                        fileOutputStream.write(a.getNames().getBytes());
                        fileOutputStream.write("\nPhone no :-".getBytes());
                        fileOutputStream.write(a.getMobile_Number().getBytes());
                        fileOutputStream.write("\n=======================\n\n".getBytes());
                    }

                    fileOutputStream.close();
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    Uri mydir = Uri.parse("file://"+Dir.getAbsolutePath());
//                    intent.setDataAndType(mydir,"application/*");    // or use */*
//                    startActivity(intent);
                    new AlertDialog.Builder(this)
                            .setTitle("SAVED at  "+Dir.getAbsolutePath())
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).create().show();
                    Toast.makeText(this, "Saved At"+Dir.getAbsolutePath(), Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "No Storage Available", Toast.LENGTH_SHORT).show();
            }
    }

    public void setPath(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                registrationList.clear();
                for (DataSnapshot eventSnapshot:dataSnapshot.getChildren()){
                    MyRegistration registration=eventSnapshot.getValue(MyRegistration.class);
                    registrationList.add(registration);
                }
                Events_list adapter=new Events_list(RegistrationList.this,registrationList);
                listView_events.setAdapter(adapter);
               progressBar2.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void goMainActivity(View view){
        Intent intent = new Intent(RegistrationList.this,MainActivity.class)   ;
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }
    public void goRegistrationForm(View view){
        Intent intent = new Intent(RegistrationList.this,RegistrationForm.class)   ;
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }
}
