package com.cadle.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cadle.passwordmanager.database.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button generatorButton;
    private Button passwordButton;
    private ListView listView;
    private FloatingActionButton add;
    private List<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        elementController();
    }

    public void initializeViewElements() {
        generatorButton = (Button) findViewById(R.id.generator);
        passwordButton = (Button) findViewById(R.id.passwords);
        listView = (ListView) findViewById(R.id.listView);
        add = (FloatingActionButton) findViewById(R.id.add);
    }


    // only fill list with Servicename
    public void fillList() {
        services = getEntrys();
        List<String> namesOfServices = new ArrayList<>();
        for (Service service : services) {
            namesOfServices.add(service.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesOfServices);
        listView.setAdapter(arrayAdapter);
    }


    // returns the Service-object to the DataBase
    public List<Service> getEntrys() {
        List<Service> services = Service.listAll(Service.class);
        return services;
    }

    public void elementController() {
        initializeViewElements();
        fillList();
        generatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Generator.class));
            }
        });
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        // the ID gets attached to the Bundle and then pass the Bundle in the Intent
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long id = services.get(i).getId();
                Intent intent = new Intent(MainActivity.this, Edit.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Entry.class));

            }
        });
    }
}