package com.example.moduloproveedores.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moduloproveedores.MainCallbacks;
import com.example.moduloproveedores.R;
import com.example.moduloproveedores.bd.dao.Pais;
import com.example.moduloproveedores.bd.dao.Proveedor;
import com.example.moduloproveedores.bd.dao.TipoProveedor;
import com.example.moduloproveedores.fragments.AdminProveedorFragment;

import java.util.List;

public class AdapterProveedores extends RecyclerView.Adapter<AdapterProveedores.ViewHolder>{

    List<Proveedor> proveedors;
    List<TipoProveedor> tipoProveedors;
    List<Pais> paises;
    Context context;
    MainCallbacks mainCallbacks;

    public AdapterProveedores(Context context, List<Proveedor> proveedors,
                              List<TipoProveedor> tipoProveedors,
                              List<Pais> paises){
        this.context = context;
        this.proveedors = proveedors;
        this.tipoProveedors = tipoProveedors;
        this.paises = paises;
    }

    public void filterProveedores(List<Proveedor> filterlist){
        proveedors = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.proveedor_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Proveedor proveedorSelected = proveedors.get(position);
        holder.tvNombre.setText(proveedorSelected.getNombre());
        holder.tvTipoProveedor.setText(tipoProveedors.get(proveedorSelected.getIdTipPro()-1).getName());
        holder.tvPais.setText(paises.get(proveedorSelected.getIdPais()-1).getName());

        holder.tvNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PROO", "Click holder");
                Bundle bundle = new Bundle();
                bundle.putInt("ID_PROVEEDOR",proveedorSelected.getId());
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fragment = new AdminProveedorFragment();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return proveedors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvTipoProveedor;
        TextView tvPais;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.proveedor_item_name);
            tvTipoProveedor = (TextView) itemView.findViewById(R.id.proveedor_item_tippro);
            tvPais = (TextView) itemView.findViewById(R.id.proveedor_item_pais);
        }
    }
}
