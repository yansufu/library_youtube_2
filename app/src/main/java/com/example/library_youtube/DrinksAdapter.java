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

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    ViewHolder holder;

    public DrinksAdapter(ArrayList<Drinks> listDrinks) {
        this.listDrinks = listDrinks;
    }

    private ArrayList<Drinks> listDrinks;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder =  new ViewHolder(inflater.inflate(R.layout.item_menu, parent, false));

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drinks drinks = listDrinks.get(position);
        holder.txtNamaDrinks.setText(drinks.getNamaDrinks());
        holder.txtHargaDrinks.setText(drinks.getHargaDrinks());
        holder.imgDrinks.setImageResource(drinks.getImgDrinks());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listDrinks.get(position).getNamaDrinks().equals("Gramedia")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.account);
                    intent.putExtra("NAMA_DEFAULT", "Gramedia");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Gramedia@gmail.com");
                    intent.putExtra("HARGA_DEFAULT", "contact");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("BPKP")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.account);
                    intent.putExtra("NAMA_DEFAULT", "BPKP");
                    intent.putExtra("DESKRIPSI_DEFAULT", "bpkp@gmail.com");
                    intent.putExtra("HARGA_DEFAULT", "contact");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("American Library Association")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.account);
                    intent.putExtra("NAMA_DEFAULT", "American Library Association");
                    intent.putExtra("DESKRIPSI_DEFAULT", "ala@gmail.com");
                    intent.putExtra("HARGA_DEFAULT", "contact");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Sience Direct")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.account);
                    intent.putExtra("NAMA_DEFAULT", "Sience Direct");
                    intent.putExtra("DESKRIPSI_DEFAULT", "scdirect@gmail.com");
                    intent.putExtra("HARGA_DEFAULT", "contact");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("PT Erlangga")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.account);
                    intent.putExtra("NAMA_DEFAULT", "PT Erlangga");
                    intent.putExtra("DESKRIPSI_DEFAULT", "erlangga@gmail.com");
                    intent.putExtra("HARGA_DEFAULT", "contact");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("PT Tirta Buana")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.account);
                    intent.putExtra("NAMA_DEFAULT", "PT Tirta Buana");
                    intent.putExtra("DESKRIPSI_DEFAULT", "buana@gmail.com");
                    intent.putExtra("HARGA_DEFAULT", "contact");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDrinks.size();
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
