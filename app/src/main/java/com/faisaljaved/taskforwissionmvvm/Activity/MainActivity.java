package com.faisaljaved.taskforwissionmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.faisaljaved.taskforwissionmvvm.Adapter.DBEntryAdapter;
import com.faisaljaved.taskforwissionmvvm.Listener.DataLoadListener;
import com.faisaljaved.taskforwissionmvvm.Model.DBEntryModel;
import com.faisaljaved.taskforwissionmvvm.R;
import com.faisaljaved.taskforwissionmvvm.ViewModel.DBEntryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataLoadListener, DBEntryAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private DBEntryAdapter dbEntryAdapter;
    private DBEntryViewModel dbEntryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbEntryViewModel = ViewModelProviders.of(MainActivity.this).get(DBEntryViewModel.class);
        dbEntryViewModel.init(MainActivity.this);

        dbEntryAdapter = new DBEntryAdapter(dbEntryViewModel.getEntries().getValue(), this);

        recyclerView.setAdapter(dbEntryAdapter);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onEntryLoaded() {
        dbEntryViewModel.getEntries().observe(this, new Observer<ArrayList<DBEntryModel>>() {
            @Override
            public void onChanged(ArrayList<DBEntryModel> dbEntryModels) {
                dbEntryAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        DBEntryModel entry = dbEntryViewModel.getEntries().getValue().get(position);
        //creating an intent
        Intent intent = new Intent(getApplicationContext(), DataEntryView.class);

        //putting entries to intent
        intent.putExtra("ENTRY_NAME", entry.getName());
        intent.putExtra("ENTRY_EMAIL", entry.getEmail());
        intent.putExtra("ENTRY_PHONE", entry.getPhone());

        //starting the activity with intent
        startActivity(intent);
    }
}
