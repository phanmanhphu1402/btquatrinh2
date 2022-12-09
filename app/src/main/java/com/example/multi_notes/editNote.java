package com.example.multi_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class editNote extends AppCompatActivity {
    String title, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent edit =getIntent();
        title = edit.getStringExtra("title");
        description = edit.getStringExtra("description");
        EditText titleInput = findViewById(R.id.EditTextNote);
        titleInput.setText(title);
        EditText DeScripInput = findViewById(R.id.EditTextScrip);
        DeScripInput.setText(description);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        MaterialButton saveBtn = findViewById(R.id.SaveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long createTime = System.currentTimeMillis();
                realm.beginTransaction();
                Note note = realm.where(Note.class).findFirst();
                assert note != null;
                note.setTitle(titleInput.getText().toString());
                note.setDescription(DeScripInput.getText().toString());
                note.setCreatedTime(createTime);
                realm.commitTransaction();
                Toast.makeText(editNote.this, "Save note", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}