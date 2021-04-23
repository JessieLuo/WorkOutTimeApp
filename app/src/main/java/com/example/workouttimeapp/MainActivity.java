package com.example.workouttimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Chronometer timer;
    EditText enterWorkType;
    String timeRecord;
    String TIMER_RECORD, EDIT_TEXT_RECORD;
    TextView showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timeText);
        enterWorkType = findViewById(R.id.enterEditText);
        showText = findViewById(R.id.showText);
        if(savedInstanceState != null){
            timeRecord = savedInstanceState.getString(TIMER_RECORD);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TIMER_RECORD, timeRecord);
    }

    private String getChronometer(Chronometer chronometer) {
        String getTimeString = chronometer.getText().toString();
        return getTimeString;
    }

    public void onTimeClick(View view) {
        switch (view.getId()){
            case R.id.startButton:
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                break;
            case R.id.pauseButton:
                timer.stop();
                //send time data to showText
                /*Double temp = Double.parseDouble(timer.getText().toString().split(":")[1]) * 1000;
                timer.setBase((long) (SystemClock.elapsedRealtime() - temp));
                timer.start();*/
                break;
            case R.id.stopButton:
                timer.stop();
                //send time data show text and clear time data
                timeRecord = getChronometer(timer);
                showText.setText("You spent " + timeRecord + " " + enterWorkType.getText().toString()+ " last time");
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}