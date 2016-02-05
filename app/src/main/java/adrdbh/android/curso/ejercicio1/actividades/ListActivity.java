package adrdbh.android.curso.ejercicio1.actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adrdbh.android.curso.ejercicio1.R;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // default made part of code [default]
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // end [default]

        //              XML LAYOUT FILE @   ID
        // asign value to custom_layout @ act2 id
        final TextView tx2 = (TextView) findViewById(R.id.act2);
        // asign value to custom_layout @ btn2 id
        final Button btn2 = (Button) findViewById(R.id.btn2);
        // asign value to content_list @ tx1 id (Editable text field)
        final EditText ex2 = (EditText) findViewById(R.id.tx1);
        // asign value to content_list @ l1 id
        final ListView lv = (ListView) findViewById(R.id.l1);
        // bundle object to pass info/switch from activities
        final Bundle bundle = getIntent().getExtras();
        // Arraylist of string values to store input from user.
        final ArrayList<String> arl = new ArrayList<String>();
        // Array adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_layout, arl);
        // Retrieves string to share content from another activity
        tx2.setText(bundle.getString("key"));
        lv.setAdapter(adapter);

        // Button listener implementation to add elements typed by the user
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.add(ex2.getText().toString());
                adapter.notifyDataSetChanged();
                ex2.setText(" ");

            }
        });
        // Shows a message dialog from toast about relevant info from arraylist items
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, "elemento: " + lv.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
