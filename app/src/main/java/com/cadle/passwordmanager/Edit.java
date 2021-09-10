package com.cadle.passwordmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cadle.passwordmanager.database.Service;
import com.orm.SugarContext;

import java.util.List;

public class Edit extends AppCompatActivity {
    private Button passwordButton;
    private Button generatorButton;
    private EditText entryUsername;
    private EditText entryPassword;
    private Button saveButton;
    private Button buttonDelete;
    private TextView entryLabel;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        SugarContext.init(this);
        elementController();
    }

    public void elementController() {
        initializeViewElements();
        fillFields();
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Edit.this, MainActivity.class));
            }
        });
        generatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Edit.this, Generator.class));
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service service = Service.findById(Service.class, id);
                service.setUsername(String.valueOf(entryUsername.getText()));
                service.setPassword(String.valueOf(entryPassword.getText()));
                service.save();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // only delete if object doesn't exists
                if (Service.findById(Service.class, id) != null) {
                    new AlertDialog.Builder(Edit.this)
                            .setTitle("Delete " + "\"" + Service.findById(Service.class, id).getName() + "\"")
                            .setMessage("Are you sure you want to delete this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Service service = Service.findById(Service.class, id);
                                    service.delete();
                                    startActivity(new Intent(Edit.this, MainActivity.class));
                                }
                            })

                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }


            }
        });
    }


    // the transfered ID of the Service-Object receiving with the Intent, then getting the Serviceobject from the DataBase and fill the space
    public void fillFields() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");
        List<Service> services = Service.find(Service.class, "id = ?", String.valueOf(id));
        entryUsername.setText(services.get(0).getUsername());
        entryPassword.setText(services.get(0).getPassword());
        entryLabel.setText(services.get(0).getName() + ":");
    }

    public void initializeViewElements() {
        passwordButton = (Button) findViewById(R.id.passwords);
        generatorButton = (Button) findViewById(R.id.generator);
        entryUsername = (EditText) findViewById(R.id.editUsername);
        entryPassword = (EditText) findViewById(R.id.editPassword);
        saveButton = (Button) findViewById(R.id.buttonSave);
        buttonDelete = (Button) findViewById(R.id.buttonCancel);
        entryLabel = (TextView) findViewById(R.id.entryLabel);
    }

}