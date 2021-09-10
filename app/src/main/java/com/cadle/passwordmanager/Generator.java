package com.cadle.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator extends AppCompatActivity {
    private Button passwordButton;
    private Button generatorButton;
    private Button generateButton;
    private TextView password;
    private FloatingActionButton copyButton;
    private String generatedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        SugarContext.init(this);
        elementController();
    }

    public void initializeViewElements() {
        passwordButton = (Button) findViewById(R.id.passwords_Gen);
        generatorButton = (Button) findViewById(R.id.generator_gen);
        generateButton = (Button) findViewById(R.id.generate);
        password = (TextView) findViewById(R.id.password);
        copyButton = (FloatingActionButton) findViewById(R.id.copyButton);
    }

    public void elementController() {
        initializeViewElements();

        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Generator.this, MainActivity.class));
            }
        });
        generatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Generator.this, Generator.class));
            }
        });
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewPassword();
            }
        });
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // copy password to clipboard
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("password", generatedPassword);
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    public void createNewPassword() {
        Random rand = new Random();
        String password = "";
        boolean passwordOK = false;
        boolean hasCapitals = false;
        boolean hasSmalls = false;
        boolean hasNums = false;
        boolean hasExtras = false;
        List<String> capitals = new ArrayList<>();
        List<String> small = new ArrayList<>();
        List<String> nums = new ArrayList<>();
        List<String> extra = new ArrayList<>();
        // Datagroup
        capitals.add("A");
        capitals.add("B");
        capitals.add("C");
        capitals.add("D");
        capitals.add("E");
        capitals.add("F");
        capitals.add("G");
        capitals.add("H");
        capitals.add("I");
        capitals.add("J");
        capitals.add("K");
        capitals.add("L");
        capitals.add("M");
        capitals.add("N");
        capitals.add("O");
        capitals.add("P");
        capitals.add("Q");
        capitals.add("R");
        capitals.add("S");
        capitals.add("T");
        capitals.add("U");
        capitals.add("V");
        capitals.add("W");
        capitals.add("X");
        capitals.add("Y");
        capitals.add("Z");
        small.add("a");
        small.add("b");
        small.add("c");
        small.add("d");
        small.add("e");
        small.add("f");
        small.add("g");
        small.add("h");
        small.add("i");
        small.add("j");
        small.add("k");
        small.add("l");
        small.add("m");
        small.add("n");
        small.add("o");
        small.add("p");
        small.add("q");
        small.add("r");
        small.add("s");
        small.add("t");
        small.add("u");
        small.add("v");
        small.add("w");
        small.add("x");
        small.add("y");
        small.add("z");
        nums.add("1");
        nums.add("2");
        nums.add("3");
        nums.add("4");
        nums.add("5");
        nums.add("5");
        nums.add("6");
        nums.add("7");
        nums.add("8");
        nums.add("9");
        nums.add("0");
        extra.add("*");
        extra.add("&");
        extra.add("?");
        extra.add("!");
        extra.add("_");
        extra.add(".");
        extra.add(",");
        extra.add("-");
        extra.add("$");
        extra.add("=");
        extra.add("@");
        extra.add("+");
        while (!passwordOK) {
            for (int i = 0; i < 18; i++) {
                switch (rand.nextInt(4)) {
                    case 0:
                        password = password + capitals.get(rand.nextInt(capitals.size()));
                        break;
                    case 1:
                        password = password + small.get(rand.nextInt(small.size()));
                        break;
                    case 2:
                        password = password + nums.get(rand.nextInt(nums.size()));
                        break;
                    case 3:
                        password = password + extra.get(rand.nextInt(extra.size()));
                        break;
                }
            }
            // check if password has at least one of each type
            for (String capital : capitals) {
                if (password.contains(capital)) {
                    hasCapitals = true;
                }
            }
            for (String smal : small) {
                if (password.contains(smal)) {
                    hasSmalls = true;
                }
            }
            for (String num : nums) {
                if (password.contains(num)) {
                    hasNums = true;
                }
            }
            for (String extr : extra) {
                if (password.contains(extr)) {
                    hasExtras = true;
                }
            }
            // again if one of the types was never used
            if (hasCapitals && hasSmalls && hasNums && hasExtras) {
                passwordOK = true;
            } else {
                password = "";
                hasCapitals = false;
                hasSmalls = false;
                hasNums = false;
                hasExtras = false;
            }
        }
        // set Password
        this.password.setText(password);
        this.generatedPassword = password;
    }
}