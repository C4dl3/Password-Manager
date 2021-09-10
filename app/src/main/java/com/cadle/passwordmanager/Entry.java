package com.cadle.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cadle.passwordmanager.database.Service;
import com.orm.SugarContext;

public class Entry extends AppCompatActivity {
    private Button passwordButton;
    private Button generatorButton;
    private Button saveButton;
    private Button cancelButton;
    private EditText username;
    private EditText password;
    private EditText editServicename;
    private TextView infoTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        SugarContext.init(this);
        elementController();
    }

    public void elementController() {
        initializeViewElements();

        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Entry.this, MainActivity.class));
            }
        });
        generatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Entry.this, Generator.class));
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Entry.this, MainActivity.class));
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // in case that the Servicename isn't empty, too many spaces get deleted and the Servicename can't be longer than 30 characters
                if (!String.valueOf(editServicename.getText()).equals("") && String.valueOf(editServicename.getText()).trim().length() > 0 && String.valueOf(editServicename.getText()).trim().replaceAll("\\s+", " ").length() <= 30) {
                    String editServicenameSanitized = String.valueOf(editServicename.getText()).trim().replaceAll("\\s+", " ");
                    Service service = new Service(String.valueOf(username.getText()), String.valueOf(password.getText()), editServicenameSanitized);
                    service.save();
                    startActivity(new Intent(Entry.this, MainActivity.class));
                } else {
                    // evaluate error
                    if (String.valueOf(editServicename.getText()).equals("")) {
                        infoTag.setText("Servicename can't be empty!");
                    } else if (String.valueOf(editServicename.getText()).trim().length() == 0) {
                        infoTag.setText("Servicename can't be all spaces!");
                    } else if (String.valueOf(editServicename.getText()).trim().replaceAll("\\s+", " ").length() > 30) {
                        infoTag.setText("Servicename can't be that long!");
                    }
                    // close keyboard, so the User can see the error message
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            }
        });
    }

    public void initializeViewElements() {
        passwordButton = (Button) findViewById(R.id.passwords);
        generatorButton = (Button) findViewById(R.id.generator);
        saveButton = (Button) findViewById(R.id.buttonSave);
        cancelButton = (Button) findViewById(R.id.buttonCancel);
        username = (EditText) findViewById(R.id.editUsernameNew);
        password = (EditText) findViewById(R.id.editPasswordNew);
        editServicename = (EditText) findViewById(R.id.editServicename);
        infoTag = (TextView) findViewById(R.id.infoLabel);
    }
}