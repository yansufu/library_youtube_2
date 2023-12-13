package com.example.library_youtube;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    ViewHolder holder;

    public EventAdapter(ArrayList<Event> listEvent) {
        this.listEvent = listEvent;
    }

    private ArrayList<Event> listEvent;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder =  new ViewHolder(inflater.inflate(R.layout.event_item, parent, false));

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = listEvent.get(position);
        holder.txtNamaDrinks.setText(event.getEventname());
        holder.txtHargaDrinks.setText(event.getCollaborator());
        holder.imgDrinks.setImageResource(event.getImgEvent());

    }

    @Override
    public int getItemCount() {
        return listEvent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaDrinks, txtHargaDrinks;
        public ImageView imgDrinks;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaDrinks = (TextView) itemView.findViewById(R.id.txtNamaItem);
            txtHargaDrinks = (TextView) itemView.findViewById(R.id.txtHargaItem);
            imgDrinks = (ImageView) itemView.findViewById(R.id.imgItem);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}
