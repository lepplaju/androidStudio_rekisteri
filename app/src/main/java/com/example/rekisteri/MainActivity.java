package com.example.rekisteri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
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

    private TextView tutkinnotText;

    private RadioGroup radioButtons;
    private CheckBox kandi;
    private CheckBox dippa;
    private CheckBox tekniikanTohtori;
    private CheckBox uimaMaisteri;
    private Spinner listOfIcons;
    private int selectedImageResourceId = 0;
    private ArrayList<String> checkedTutkinnot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        storage = UserStorage.getInstance();

        // Tiedoston lataus heti sovelluksen käynnistyessä
        storage.loadUsers(MainActivity.this);

        tutkinnotText = findViewById(R.id.tutkinnotTextView);
        tutkinnotText.setPaintFlags(tutkinnotText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

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


        kandi = findViewById(R.id.kandiCB);
        dippa = findViewById(R.id.dippaCB);
        tekniikanTohtori = findViewById(R.id.tekniikanTohtoriCB);
        uimaMaisteri = findViewById(R.id.uimaCB);

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

        // Checkbox osuus. Kaikki checkboxit täysin erillisiä olioita
        checkedTutkinnot = new ArrayList<>();
        if (kandi.isChecked()) {
            checkedTutkinnot.add(kandi.getText().toString());
        }
        if (dippa.isChecked()) {
            checkedTutkinnot.add(dippa.getText().toString());
        }
        if(tekniikanTohtori.isChecked()){
            checkedTutkinnot.add(tekniikanTohtori.getText().toString());
        }
        if(uimaMaisteri.isChecked()){
            checkedTutkinnot.add(uimaMaisteri.getText().toString());
        }

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) || id < 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Täytä kaikki kentät!");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            storage = UserStorage.getInstance();
            String major;
            major = rb.getText().toString();
            User student;
            if(checkedTutkinnot.size()>0){
                Log.i("info",String.valueOf(checkedTutkinnot.size()));
                student = new User(firstName, lastName, email, major, selectedImageResourceId,checkedTutkinnot);
            }else{
                student = new User(firstName, lastName, email, major, selectedImageResourceId);
            }
            storage.addUser(student);
            clearTextFields();

            // Tallennus heti uuden käyttäjän lisäämisen jälkeen
            storage.saveUsers(MainActivity.this);
        }


    }

    public void clearTextFields() {
        inputFirstName.getText().clear();
        inputLastName.getText().clear();
        inputEmail.getText().clear();
        radioButtons.clearCheck();
        kandi.setChecked(false);
        dippa.setChecked(false);
        tekniikanTohtori.setChecked(false);
        uimaMaisteri.setChecked(false);
    }

    public void switchView(View view) {
        Intent intent = new Intent(this, UserListViewActivity.class);
        startActivity(intent);
    }
}