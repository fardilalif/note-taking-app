package com.example.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditNote extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.note.EXTRA_ID";
    public static final String EXTRA_NOTE = "com.example.note.EXTRA_NOTE";
    public static final String EXTRA_DESCRIPTION = "com.example.note.EXTRA_DESCRIPTION";

    private EditText editText_title;
    private EditText editText_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        editText_title = findViewById(R.id.editText_title);
        editText_description = findViewById(R.id.editText_description);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_cancel);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit note");
            editText_title.setText(intent.getStringExtra(EXTRA_NOTE));
            editText_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }
        else{
            setTitle("Add note");
        }
    }

    public void saveNote(){
        String title = editText_title.getText().toString();
        String description = editText_description.getText().toString();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please insert any note", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NOTE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note_menu:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}