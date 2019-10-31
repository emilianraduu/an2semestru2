package com.example.laborator13;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText date;
    EditText details;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Button button = (Button) findViewById(R.id.insert);
        BufferedWriter bw = null;
        PrintWriter pw = null;
        FileWriter fw = null;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = (EditText) findViewById(R.id.name);
                date = (EditText) findViewById(R.id.date);
                details = (EditText) findViewById(R.id.details);
                String a = readFromFile(getBaseContext());
                String s = a + " \n" + name.getText() + " " + date.getText() + " " + details.getText() + " \n";
                writeToFile(s, context);
                goToSecondAct();
            }
        });
    }

    private void goToSecondAct() {

        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("Name", name.getText().toString());
        intent.putExtra("Date", date.getText().toString());
        intent.putExtra("Details", details.getText().toString());
        startActivity(intent);

    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }


    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
