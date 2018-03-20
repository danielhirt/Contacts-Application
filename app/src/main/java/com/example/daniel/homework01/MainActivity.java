/* Homework 01
Daniel Christopher Hirt
ITCS 4180
 */
package com.example.daniel.homework01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int CONTACT_CODE = 100;
    static final int DELETE_CODE = 999;
    static final int EDIT_CODE = 888;

    static final String CONTACT_KEY = "NEW_CONTACT";
    static final String UPDATE = "UPDATE";

    private static ArrayList<Contact> people = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts");

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.default_image);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        people.add(new Contact(byteArray,
                "Daniel",
                "Hirt",
                "UNCC",
                "919-780-7827",
                "dhirt@uncc.edu",
                "http://www.google.com",
                "9204 University Blvd",
                "Feb 4, 1998",
                "dshirt",
                "http://www.facebook.com",
                "http://www.twitter.com",
                "http://www.skype.com",
                "http://www.youtube.com"));

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == CONTACT_CODE){
            if(resultCode == RESULT_OK){

                Contact person = (Contact) data.getExtras().get(CONTACT_KEY);
                people.add(person);


            }
        }

        if(requestCode == EDIT_CODE || requestCode == DELETE_CODE){
            if(resultCode == RESULT_OK){
                people = (ArrayList<Contact>) data.getExtras().get(UPDATE);
            }
        }


    }


    public void onCreateClick(View view){

        Intent intent = new Intent(MainActivity.this, CreateNewContact.class);
        startActivityForResult(intent, CONTACT_CODE);
    }


    public void onEditClick(View view){

        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);
        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", false);
            intent.putExtra("EDIT", true);
            intent.putExtra("DELETE", false);
            startActivityForResult(intent, EDIT_CODE);
        }else
            Toast.makeText(this, "There are no contacts to edit", Toast.LENGTH_SHORT).show();

    }
    public void onDisplayClick(View view){

        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);

        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", true);
            intent.putExtra("EDIT", false);
            intent.putExtra("DELETE", false);
            startActivity(intent);
        }else
            Toast.makeText(this, "There are no contacts to show", Toast.LENGTH_SHORT).show();

    }


    public void onDeleteClick(View view){

        Intent intent = new Intent(MainActivity.this, DisplayContacts.class);

        if(people.size() > 0) {
            intent.putExtra("CONTACTS", people);
            intent.putExtra("VIEW", false);
            intent.putExtra("EDIT", false);
            intent.putExtra("DELETE", true);
            startActivityForResult(intent, DELETE_CODE);
        }else
            Toast.makeText(this, "There are no contacts to Delete", Toast.LENGTH_SHORT).show();


    }



    public void onFinishClick(View view){
        finish();
    }

}