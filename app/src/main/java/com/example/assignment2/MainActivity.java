package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxTerms;
    private RadioGroup radioGroupGender;
    private RatingBar ratingBar;
    private SeekBar seekBar;
    private TextView seekBarValue;
    private Switch switchNotifications;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        checkBoxTerms = findViewById(R.id.checkboxTerms);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        ratingBar = findViewById(R.id.ratingBar);
        seekBar = findViewById(R.id.seekBar);
        seekBarValue = findViewById(R.id.seekBarValue);
        switchNotifications = findViewById(R.id.switchNotifications);
        submitButton = findViewById(R.id.submitButton);

        // SeekBar Listener to update TextView with SeekBar value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText("SeekBar Value: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something if needed when tracking starts
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do something if needed when tracking stops
            }
        });

        // Button onClickListener to validate form
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    // Method to validate form
    private void validateForm() {
        // Validate CheckBox (Terms & Conditions)
        if (!checkBoxTerms.isChecked()) {
            Toast.makeText(this, "Please agree to the Terms and Conditions.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate RadioButton (Gender)
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select your gender.", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton.getText().toString();

        // Get RatingBar value
        float rating = ratingBar.getRating();

        // Get SeekBar value
        int seekBarValueInt = seekBar.getProgress();

        // Get Switch value (Notifications)
        boolean notificationsEnabled = switchNotifications.isChecked();

        // Show a summary of the input
        String summary = "Gender: " + gender +
                "\nRating: " + rating +
                "\nSeekBar Value: " + seekBarValueInt +
                "\nNotifications: " + (notificationsEnabled ? "Enabled" : "Disabled");

        // Show result in Toast
        Toast.makeText(this, summary, Toast.LENGTH_LONG).show();
    }
}