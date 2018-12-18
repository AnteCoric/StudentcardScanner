package com.example.android.studentcardscanner;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class secondActivity extends AppCompatActivity {

    NfcAdapter mNfcAdapter;
    private String DataPayload;
    private TextView mInfo;
    private String[] messagedata;
    String text = "";
    NfcAdapter mAdapter;
    PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        /*NfcAdapter mAdapter;
        PendingIntent mPendingIntent;*/
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            //nfc not support your device.
            return;
        }
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        mInfo = (TextView) findViewById(R.id.info);
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

        //getTagInfo(intent);
    }

    private void getTagInfo(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        String[] techList = tag.getTechList();
        for (int i = 0; i < techList.length; i++) {
            if (techList[i].equals(MifareClassic.class.getName())) {

                MifareClassic mifareClassicTag = MifareClassic.get(tag);
                switch (mifareClassicTag.getType()) {
                    case MifareClassic.TYPE_CLASSIC:
                        //Type Clssic
                        break;
                    case MifareClassic.TYPE_PLUS:
                        //Type Plus
                        break;
                    case MifareClassic.TYPE_PRO:
                        //Type Pro
                        break;
                }
            } else if (techList[i].equals(MifareUltralight.class.getName())) {
                //For Mifare Ultralight
                MifareUltralight mifareUlTag = MifareUltralight.get(tag);
                switch (mifareUlTag.getType()) {
                    case MifareUltralight.TYPE_ULTRALIGHT:
                        break;
                    case MifareUltralight.TYPE_ULTRALIGHT_C:

                        break;
                }
            } else if (techList[i].equals(IsoDep.class.getName())) {
                // info[1] = "IsoDep";
                IsoDep isoDepTag = IsoDep.get(tag);

            } else if (techList[i].equals(Ndef.class.getName())) {
                Ndef.get(tag);

            } else if (techList[i].equals(NdefFormatable.class.getName())) {

                NdefFormatable ndefFormatableTag = NdefFormatable.get(tag);

            }
        }
    }

    private void GetDataFromTag(Tag tag, Intent intent) {
        NfcA ndef = NfcA.get(tag);
        text = "";
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
                    text += toHEXADECIMAL(messages[i]);
                }
                //NdefRecord record = ndefMessages[0].getRecords()[0];

                //byte[] payload = record.getPayload();
                Log.e("tag", "vahid" + text);
                ndef.close();
                mInfo.setText(text);
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
    /*
    public void nfcStart(View view) {
        mInfo.setText("geting data");
        //onNewIntent(testintent);//nog een intent toevoegen

        /*if (rawMessages != null) {
            NdefMessage[] messages = new NdefMessage[rawMessages.length];
            for (int i = 0; i < rawMessages.length; i++) {
                messages[i] = (NdefMessage) rawMessages[i];
                DataPayload += messages[i];
            }
            // Process the messages array.
            //...
        } else {
            DataPayload = "nfc fail";
        }*

        mInfo.setText(DataPayload);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    //...
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                    DataPayload += messages[i];
                }
                // Process the messages array.
            //...
            } else {
                DataPayload = "nfc fail";
            }
        }
    }*/
}
