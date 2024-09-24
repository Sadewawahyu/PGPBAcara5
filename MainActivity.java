package com.example.countryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.countryapp.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Button btn_submit;
    private String[] presensi = {
            "Tepat Waktu",
            "Terlambat",
            "Sakit",
            "Izin",
            "Alpa",
    };
    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        presensi = getResources().getStringArray(R.array.Presensi);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, presensi);
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPresensi.setAdapter(adapterCountry);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        selectedDate = day + "/" + (month + 1) + "/" + year;
        selectedTime = String.format("%02d:%02d", hour, minute);

        binding.datePicker.init(
                year,
                month,
                day,
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int
                            monthOfYear, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/"
                                + year;
                    }
                }
        );

        binding.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectedTime = String.format("%02d:%02d", hourOfDay, minute);
            }
        });
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String presensiTerpilih = binding.spinnerPresensi.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "Presensi Berhasil " + selectedDate + " pukul " + selectedTime + " dengan status " + presensiTerpilih, Toast.LENGTH_SHORT).show();
            }

        });
    }
}