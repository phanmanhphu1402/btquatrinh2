package com.example.multi_notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton addNotebtn = findViewById(R.id.addnewnotebtn);
        addNotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NewNotes = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivity(NewNotes);
            }
        });

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Note> notesList = realm.where(Note.class).findAllAsync();

        RecyclerView recyclerView = findViewById(R.id.rectanglesview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),notesList);
        recyclerView.setAdapter(myAdapter);

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChange(@NonNull RealmResults<Note> notes) {
                myAdapter.notifyDataSetChanged();

            }
        });
    }
}