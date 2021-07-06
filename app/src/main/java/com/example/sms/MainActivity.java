package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text_phone;
    EditText sms_text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_phone = findViewById(R.id.phone);
        sms_text = findViewById(R.id.text1);


    }



    public void sendsms(View view) {

        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
    if(permissioncheck == PackageManager.PERMISSION_GRANTED){
        Mymessage();
    }
    else{

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},0);
    }
    }

    private void Mymessage() {
        String phonenumber = text_phone.getText().toString().trim();
        String Textsms = sms_text.getText().toString().trim();

        if(!text_phone.getText().toString().equals("") || sms_text.getText().toString().equals("")){

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber, null,Textsms, null, null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();


        }
        else{

            Toast.makeText(this, "Please enter number or message", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Mymessage();
                }
                else {

                    Toast.makeText(this, "YOU DON'T HAVE REQUIRED PERMISSION TO MAKE THIS ACTION", Toast.LENGTH_SHORT).show();
                }
        }
    }
}