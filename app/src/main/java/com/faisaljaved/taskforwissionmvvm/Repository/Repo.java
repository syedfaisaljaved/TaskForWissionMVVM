package com.faisaljaved.taskforwissionmvvm.Repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.faisaljaved.taskforwissionmvvm.Listener.DataLoadListener;
import com.faisaljaved.taskforwissionmvvm.Model.DBEntryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Repo {

    static Repo instance;
    private ArrayList<DBEntryModel> entryModels = new ArrayList<>();

    static Context mConext;
    static DataLoadListener dataLoadListener;

    public static Repo getInstance(Context context) {

        mConext = context;
        if (instance == null) {

            instance = new Repo();
        }

        dataLoadListener = (DataLoadListener) mConext;
        return instance;
    }

    public MutableLiveData<ArrayList<DBEntryModel>> getEntries() {

        loadEntries();

        MutableLiveData<ArrayList<DBEntryModel>> entry = new MutableLiveData<>();

        entry.setValue(entryModels);

        return entry;
    }

    private void loadEntries() {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Entries");


        Query query = databaseReference;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                entryModels.clear();

                for (DataSnapshot dbEntrySnapshot : dataSnapshot.getChildren()) {

                    entryModels.add(dbEntrySnapshot.getValue(DBEntryModel.class));
                }
                dataLoadListener.onEntryLoaded();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
