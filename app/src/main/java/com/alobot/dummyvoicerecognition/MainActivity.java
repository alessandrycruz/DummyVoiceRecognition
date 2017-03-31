package com.alobot.dummyvoicerecognition;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    private TextView txtviewSpeech2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtviewSpeech2Text = (TextView) findViewById(R.id.txt_view_speech_to_text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {
                    // Sets the Speech in an Array List
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    // The most accurate Speech is in the Posision 0
                    String strSpeech2Text = speech.get(0);

                    txtviewSpeech2Text.setText(strSpeech2Text);
                }

                break;
            default:
                break;
        }
    }

    public void onClickImgBtnHablar(View v) {
        // Voice Recognition Speech
        Intent intentActionRecognizeSpeech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Sets the Language
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "us-US");

        // Starts the Voice Recognition Activity
        try {
            startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "¡Opps! Your device does not support Voice Recognition.", Toast.LENGTH_SHORT).show();
        }
    }
}
