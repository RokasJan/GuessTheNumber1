package com.example.guessthenumber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<PersonData> data;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView difficultyView;
        public TextView numberView;
        public TextView resultView;

        public CustomViewHolder(View view)
        {
            super(view);
            this.nameView = view.findViewById(R.id.name);
            this.difficultyView = view.findViewById(R.id.difficulty);
            this.numberView = view.findViewById(R.id.number);
            this.resultView = view.findViewById(R.id.result);
        }
    }

    public CustomAdapter(List<PersonData> data)
    {
        this.data = data;
    }

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person_data, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(itemView);
        return viewHolder;
    }

    public void onBindViewHolder(CustomViewHolder viewHolder, int position)
    {
        PersonData person = data.get(position);
        viewHolder.nameView.setText(person.getName());
        viewHolder.difficultyView.setText(person.getDifficulty());
        viewHolder.numberView.setText(Integer.toString(person.getNumber()));
        viewHolder.resultView.setText(person.getResult());
    }

    public int getItemCount()
    {
        return data.size();
    }

    @Override
    public int getItemViewType(int position){
        return super.getItemViewType(position);
    }
}