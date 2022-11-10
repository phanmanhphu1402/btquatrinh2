package com.example.multi_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class NewNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        EditText titleInput = findViewById(R.id.EditTextNote);
        EditText DeScripInput = findViewById(R.id.EditTextScrip);
        MaterialButton saveBtn = findViewById(R.id.SaveBtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tittle = titleInput.getText().toString();
                String description = DeScripInput.getText().toString();
                long createTime = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(tittle);
                note.setDescription(description);
                note.setCreatedTime(createTime);
                realm.commitTransaction();
                Toast.makeText(NewNoteActivity.this, "Note save", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}