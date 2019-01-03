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
import android.widget.TextView;
import android.widget.Toast;

public class add_database extends AppCompatActivity {

    NfcAdapter mNfcAdapter;
    PendingIntent mPendingIntent;
    private TextView mserialnr;
    NfcAdapter mAdapter;
    String serialnr = "";
    String firtsname = "";
    String lastname = "";
    String klas = "";
    String studentennr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_database);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        mserialnr = (TextView) findViewById(R.id.txtstudenten_serialnr);
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
        GetDataFromTag(tag, intent);
    }

    private void GetDataFromTag(Tag tag, Intent intent) {
        NfcA ndef = NfcA.get(tag);
        serialnr = "";
        try {
            //ndef.connect();
//            txtType.setText(ndef.getType().toString());
//            txtSize.setText(String.valueOf(ndef.getMaxSize()));EXTRA_NDEF_MESSAGES
//            txtWrite.setText(ndef.isWritable() ? "True" : "False");TAG_DISCOVERED
            //Parcelable[] messages = intent.getParcelableArrayExtra(NfcAdapter.EX);
            byte[] messages = ndef.getTag().getId();
            if (messages != null) {

                //NdefMessage[] ndefMessages = new NdefMessage[messages.length];
                for (int i = 0; i < messages.length; i++) {
                    //ndefMessages[i] = (NdefMessage) messages[i];
                    serialnr += toHEXADECIMAL(messages[i]);
                }
                //NdefRecord record = ndefMessages[0].getRecords()[0];

                //byte[] payload = record.getPayload();
                Log.e("tag", "vahid" + serialnr);
                ndef.close();
                mserialnr.setText(serialnr);
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

    public void add(View view) {

    }
}
