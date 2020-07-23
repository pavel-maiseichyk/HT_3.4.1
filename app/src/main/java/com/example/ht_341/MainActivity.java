package com.example.ht_341;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String language;
    TextView text;
    private static @StyleRes int theme = R.style.AppTheme_BlackTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(theme);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        changeText(theme);
        init();
    }

    public void init() {
        Button changeButton = findViewById(R.id.changeButton);
        initLanguageSpinner();
        initThemeSpinner();

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale(language);
                Configuration configuration = new Configuration();
                configuration.setLocale(locale);
                getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }
        });
    }

    public void initLanguageSpinner() {
        Spinner languageSpinner = findViewById(R.id.languageSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.languages);
                language = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initThemeSpinner() {
        Spinner themeSpinner = findViewById(R.id.themeSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.themes, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(arrayAdapter);
        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        theme = R.style.AppTheme_BlackTheme;
                        break;
                    case 1:
                        theme = R.style.AppTheme_GreenTheme;
                        break;
                    case 2:
                        theme = R.style.AppTheme_BlueTheme;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void changeText(int theme) {
        switch (theme) {
            case R.style.AppTheme_BlackTheme:
                text.setTextColor(getResources().getColor(R.color.colorPrimaryBlack));
                break;
            case R.style.AppTheme_GreenTheme:
                text.setTextColor(getResources().getColor(R.color.colorAccentGreen));
                break;
            case R.style.AppTheme_BlueTheme:
                text.setTextColor(getResources().getColor(R.color.colorPrimaryBlue));
                break;
        }
    }
}