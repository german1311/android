package com.example.davidruiz.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.example.davidruiz.myapplication.lib.BirthdayPickerPopWin;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvw = (TextView) findViewById(R.id.tvw);
        tvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.EsikaPickersDialogFragment)
                        .setYearOptional(true);
                dpb.show();
            }
        });

        TextView tvwClick = (TextView) findViewById(R.id.tvwClick);

        final BirthdayPickerPopWin pickerPopWin = new BirthdayPickerPopWin.Builder(MainActivity.this, new BirthdayPickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                Toast.makeText(MainActivity.this, dateDesc, Toast.LENGTH_SHORT).show();
            }
        }).textConfirm("CONFIRMAR") //text of confirm button
                .textCancel("CANCELAR") //text of cancel button
                .btnTextSize(17) // button text size
                .viewTextSize(20) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#E81C36"))//color of confirm button
                .showDayMonth(true) // shows like dd mm yyyy (default is false)
                .dateChose("2016-11-11") // date chose when init popwindow
                .build();

        tvwClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                //startActivity(intent);

                pickerPopWin.showPopWin(MainActivity.this);
            }
        });

    }

    private void display(String toDisplay) {
        Toast.makeText(this, toDisplay, Toast.LENGTH_SHORT).show();
    }
}
