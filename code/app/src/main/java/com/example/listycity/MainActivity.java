package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityName;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        cityName = findViewById(R.id.city_name);
        String[] cities = {"Edmonton", "Vancouver" , "Moscow" , "Sydney" , "Berlin" , "Vienna" , "Tokyo" , "Beijing" , "Osaka" , "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        final Button addButton = findViewById(R.id.add_city);
        addButton.setOnClickListener(v -> addCity());

        final Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> addCity());

        final Button removeButton = findViewById(R.id.remove_city);
        removeButton.setOnClickListener(v -> removeCity());

    }


    private void removeCity() {
        String city = cityName.getText().toString();
        if (city.isEmpty()) return;

        boolean removed = dataList.remove(city);
        if (removed) {
            cityAdapter.notifyDataSetChanged();
            cityName.setText("");
        }
    }
    private void addCity() {
        String city = cityName.getText().toString();
        if (!city.isEmpty()) {
            dataList.add(city);
            cityAdapter.notifyDataSetChanged();
            cityName.setText("");
        }
    }
}
