package com.example.android.studentcardscanner;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class edit_database_activity extends AppCompatActivity {

    private EditText studentnumber;
    private boolean nfcread = false;
    private String[] messagedata;
    String text = "";
    NfcAdapter mNfcAdapter;
    NfcAdapter mAdapter;
    PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database_activity);

        studentnumber = (EditText) findViewById(R.id.etxtstudenten_nummer);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            //nfc not support your device.
            return;
        }
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if(nfcread == true){
            GetDataFromTag(tag, intent);
        }
    }
    public void edit(View view) {
        if(studentnumber != null){
            if (nfcread == false){
                nfcread = true;
            } else {
                nfcread = false;
            }
        }
    }
    private void GetDataFromTag(Tag tag, Intent intent) {
        NfcA ndef = NfcA.get(tag);
        text = "";
        try {
            byte[] messages = ndef.getTag().getId();
            if (messages != null) {
                for (int i = 0; i < messages.length; i++) {
                    text += toHEXADECIMAL(messages[i]);
                }
                
                Log.e("tag", "vahid" + text);
                ndef.close();
                //mInfo.setText(text);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot Read From Tag.", Toast.LENGTH_LONG).show();
        }
    }
    private String toHEXADECIMAL(int x){
        String HEX = "";
        int getal1;
        int getal2;
        getal1 = x /16;
        getal2 = getal1;
        getal1 = x - (getal2 * 16);
        switch (getal2){
            case 10:
                HEX += "A";
                break;
            case 11:
                HEX += "B";
                break;
            case 12:
                HEX += "C";
                break;
            case 13:
                HEX += "D";
                break;
            case 14:
                HEX += "E";
                break;
            case 15:
                HEX += "F";
                break;
            default:
                HEX += getal2;
                break;
        }
        switch (getal1){
            case 10:
                HEX += "A";
                break;
            case 11:
                HEX += "B";
                break;
            case 12:
                HEX += "C";
                break;
            case 13:
                HEX += "D";
                break;
            case 14:
                HEX += "E";
                break;
            case 15:
                HEX += "F";
                break;
            default:
                HEX += getal1;
                break;
        }
        return HEX;
    }
}
