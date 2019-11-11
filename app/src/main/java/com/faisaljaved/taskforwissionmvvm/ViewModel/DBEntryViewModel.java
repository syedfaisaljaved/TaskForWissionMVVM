package com.faisaljaved.taskforwissionmvvm.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.faisaljaved.taskforwissionmvvm.Model.DBEntryModel;
import com.faisaljaved.taskforwissionmvvm.Repository.Repo;

import java.util.ArrayList;

public class DBEntryViewModel extends ViewModel {

    MutableLiveData<ArrayList<DBEntryModel>> entries;

    public void  init(Context context) {

        if (entries != null) {

            return;
        }
        entries = Repo.getInstance(context).getEntries();
    }

    public LiveData<ArrayList<DBEntryModel>> getEntries () {

        return entries;
    }
}
