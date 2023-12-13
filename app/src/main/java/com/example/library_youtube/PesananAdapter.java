package com.example.library_youtube;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library_youtube.DetailCartActivity;
import com.example.library_youtube.Pesanan;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PesananAdapter extends FirestoreRecyclerAdapter<Pesanan, PesananAdapter.ViewHolder> {

    public PesananAdapter(@NonNull FirestoreRecyclerOptions<Pesanan> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Pesanan model) {
        holder.txtPesanan.setText(model.namaPesanan + " (" + String.valueOf(model.jumlahPesanan) +
                " x " + rp(Integer.parseInt(String.valueOf(model.hargaPesanan))) + ')');
        holder.txtJmlHarga.setText(rp(Integer.parseInt(String.valueOf(model.hargaPesanan * model.jumlahPesanan))));

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemLayout.getContext();
                Intent it = new Intent(context, DetailCartActivity.class);
                it.putExtra("pesanan", model);
                context.startActivity(it);
            }
        });
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }



    @NonNull
    @Override
    public PesananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_records, parent, false));
        return (ViewHolder) holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtPesanan, txtJmlHarga;
        public ConstraintLayout itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPesanan = itemView.findViewById(R.id.txt_pesanan);
            txtJmlHarga = itemView.findViewById(R.id.txt_jmlharga);
            itemLayout = itemView.findViewById(R.id.item_layout);

        }
    }
}
