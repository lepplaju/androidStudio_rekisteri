package com.example.rekisteri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

//Images from <a href="https://www.freepik.com/free-vector/hand-drawn-flat-profile-icon_17539361.htm#query=avatar%20icon&position=26&from_view=keyword&track=ais">Freepik</a>
public class MainActivity extends AppCompatActivity {

    private UserStorage storage;
    private EditText inputFirstName;
    private EditText inputLastName;
    private EditText inputEmail;

    private RadioGroup radioButtons;

    private Spinner listOfIcons;
    private int selectedImageResourceId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFirstName = findViewById(R.id.editTextText);
        inputLastName = findViewById(R.id.editTextText2);
        inputEmail = findViewById(R.id.editTextText3);
        listOfIcons = findViewById(R.id.listOfIcons);
        ArrayList<String> imageNames = new ArrayList<>();
        imageNames.add("iconsinone__0_0_");
        imageNames.add("iconsinone__0_2_");
        imageNames.add("iconsinone__1_0_");
        imageNames.add("iconsinone__1_1_");
        imageNames.add("iconsinone__1_2_");
        imageNames.add("iconsinone__2_0_");
        imageNames.add("iconsinone__2_1_");
        imageNames.add("iconsinone__2_2_");
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, imageNames);
        listOfIcons.setAdapter(adapter);

        listOfIcons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedImageName = imageNames.get(position);
                selectedImageResourceId = getResources().getIdentifier(selectedImageName, "drawable", getPackageName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when no item is selected
            }
        });
    }

    public void buttonClickFunc(View view) {
        String firstName = inputFirstName.getText().toString();
        String lastName = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();

        radioButtons = findViewById(R.id.radioGroup);
        int id = radioButtons.getCheckedRadioButtonId();
        RadioButton rb = findViewById(id);

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) || id < 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Täytä kaikki kentät!");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            storage = UserStorage.getInstance();
            String major;
            major = rb.getText().toString();
            User student = new User(firstName, lastName, email, major, selectedImageResourceId);
            storage.addUser(student);
            clearTextFields();
        }


    }

    public void clearTextFields() {
        inputFirstName.getText().clear();
        inputLastName.getText().clear();
        inputEmail.getText().clear();
        radioButtons.clearCheck();

    }

    public void switchView(View view) {
        Intent intent = new Intent(this, UserListViewActivity.class);
        startActivity(intent);
    }
}