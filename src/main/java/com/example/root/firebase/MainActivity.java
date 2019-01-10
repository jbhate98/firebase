package com.example.root.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference("message");
    TextView ot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ot=(TextView)findViewById(R.id.textView3);
    }

    public void set_info(View view)
    {


        EditText et;
        et=(EditText) findViewById(R.id.editText);
        String inp =et.getText().toString();
        myRef.setValue(inp);
    }

    public void get_info(View view)
    {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                ot.setText(value);
                Log.d("Value is: ",value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("Failed to read value.",error.toException().toString());
            }
        });


    }


}
