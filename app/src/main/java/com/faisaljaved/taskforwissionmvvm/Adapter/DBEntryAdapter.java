package com.faisaljaved.taskforwissionmvvm.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faisaljaved.taskforwissionmvvm.Model.DBEntryModel;
import com.faisaljaved.taskforwissionmvvm.R;

import java.util.ArrayList;

public class DBEntryAdapter extends RecyclerView.Adapter<DBEntryAdapter.ViewHolder>{

    private ArrayList<DBEntryModel> dbEntryModels;
    private OnItemClickListener mOnItemClickListener;

    public DBEntryAdapter(ArrayList<DBEntryModel> dbEntryModels, OnItemClickListener onItemClickListener) {
        this.dbEntryModels = dbEntryModels;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);

        return new ViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(dbEntryModels.get(position));
        holder.name.setText(dbEntryModels.get(position).getName());
        holder.email.setText(dbEntryModels.get(position).getEmail());
        holder.phone.setText(dbEntryModels.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return dbEntryModels.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView email;
        TextView phone;
        OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_textview);
            email = (TextView) itemView.findViewById(R.id.email_textview);
            phone = (TextView) itemView.findViewById(R.id.phone_textview);

            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }
}
