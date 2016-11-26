package com.anandkumar.realmlearn;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import m_realm.Book;
import m_realm.RealmHelper;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private ArrayList<String> bookList;
    private ArrayAdapter adapter;
    private Spinner spinner;
    private EditText nameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realm=Realm.getDefaultInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               displayInputDialog();
            }
        });



        spinner=(Spinner)findViewById(R.id.spinner);

        RealmHelper realmHelper=new RealmHelper(realm);
        bookList=realmHelper.retrieve();

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,bookList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,bookList.get(i),Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void displayInputDialog() {

        Dialog d=new Dialog(this);
        d.setTitle("Enter Book Name");
        d.setContentView(R.layout.inputdialog);
        nameET=(EditText)d.findViewById(R.id.name);
        Button save=(Button)d.findViewById(R.id.submitButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book b=new Book();
                b.setName(nameET.getText().toString());

                RealmHelper helper=new RealmHelper(realm);
                helper.save(b);
                nameET.setText("");

                bookList=helper.retrieve();
                adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,bookList);
                spinner.setAdapter(adapter);

            }
        });
        d.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
