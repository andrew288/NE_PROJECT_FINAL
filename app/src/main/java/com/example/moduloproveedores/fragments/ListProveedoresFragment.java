package com.example.moduloproveedores.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moduloproveedores.MainCallbacks;
import com.example.moduloproveedores.R;
import com.example.moduloproveedores.adapter.AdapterProveedores;
import com.example.moduloproveedores.bd.dao.AppDataBase;
import com.example.moduloproveedores.bd.dao.DaoEstadoRegistro;
import com.example.moduloproveedores.bd.dao.DaoPais;
import com.example.moduloproveedores.bd.dao.DaoProveedor;
import com.example.moduloproveedores.bd.dao.DaoTipoProveedor;
import com.example.moduloproveedores.bd.dao.EstadoRegistro;
import com.example.moduloproveedores.bd.dao.Pais;
import com.example.moduloproveedores.bd.dao.Proveedor;
import com.example.moduloproveedores.bd.dao.TipoProveedor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListProveedoresFragment extends Fragment {
    RecyclerView recview;
    FloatingActionButton btAddProveedor;
    MainCallbacks mainCallbacks;
    List<Proveedor> proveedors;
    AdapterProveedores adapterProveedores;
    SearchView searchViewProveedor;
    Spinner spinnerTypeFilter;
    String arrayTypeFilter[] = {"Nombre", "RUC"};
    int itemSelectedTypeFilter;

    public ListProveedoresFragment() {
        // Required empty public constructor
    }

    public static ListProveedoresFragment newInstance(String param1, String param2) {
        ListProveedoresFragment fragment = new ListProveedoresFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        if (getArguments() != null) {
        }
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        List<Proveedor> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Proveedor item : proveedors) {
            // checking if the entered string matched with any item of our recycler view.
            if(itemSelectedTypeFilter == 0){
                if (item.getNombre().toLowerCase().contains(text.toLowerCase())) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(item);
                }
            }
            if(itemSelectedTypeFilter == 1){
                if (item.getRUC().toLowerCase().contains(text.toLowerCase())) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(item);
                }
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this.getActivity(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapterProveedores.filterProveedores(filteredlist);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_proveedores, container, false);
        AppDataBase db = AppDataBase.getInstance(this.getContext());
        DaoProveedor daoProveedor = db.daoProveedor();
        DaoTipoProveedor daoTipoProveedor = db.daoTipoProveedor();
        DaoPais daoPais = db.daoPais();
        DaoEstadoRegistro daoEstadoRegistro = db.daoEstadoRegistro();

        recview = root.findViewById(R.id.proveedor_items_recycle_view);
        btAddProveedor =  root.findViewById(R.id.list_pro_bt_floating);
        spinnerTypeFilter = root.findViewById(R.id.proveedor_filter_type);

        ArrayAdapter<String> adapterTypeFilter = new ArrayAdapter<String>( getActivity(),
                R.layout.list_item_dropdown,
                arrayTypeFilter);
        spinnerTypeFilter.setAdapter(adapterTypeFilter);

        spinnerTypeFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelectedTypeFilter = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchViewProveedor = root.findViewById(R.id.proovedor_search);
        searchViewProveedor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        recview.setLayoutManager(layoutManager);

        proveedors = daoProveedor.getProveedores();
        List<TipoProveedor> tipoProveedors = daoTipoProveedor.getTiposProveedor();
        List<Pais> paises = daoPais.getPaises();

        adapterProveedores = new AdapterProveedores(this.getContext(), proveedors, tipoProveedors, paises);
        recview.setAdapter(adapterProveedores);

        btAddProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                daoEstadoRegistro.insertEstadoRegistro(new EstadoRegistro("A",'A'));
//                daoEstadoRegistro.insertEstadoRegistro(new EstadoRegistro("I",'A'));
//                daoEstadoRegistro.insertEstadoRegistro(new EstadoRegistro("*",'A'));
//
//                // ADD PAISES
//                daoPais.insertPais(new Pais("Peru",1));
//                daoPais.insertPais(new Pais("Colombia",1));
//                daoPais.insertPais(new Pais("Bolivia",1));
//                daoPais.insertPais(new Pais("Argentina",1));
//                daoPais.insertPais(new Pais("Brasil",1));
//
//                // ADD TIPO DE PROVEEDORES
//                daoTipoProveedor.insertTipoProveedor(new TipoProveedor("Vegetales",1));
//                daoTipoProveedor.insertTipoProveedor(new TipoProveedor("Carnes",1));
//                daoTipoProveedor.insertTipoProveedor(new TipoProveedor("Internet",1));
//
//                // ADD PROVEEDOR
//                daoProveedor.insertProveedor(new Proveedor("Juan Juarez",
//                        "20525994741",
//                        1,
//                        2,
//                        1));
//                daoProveedor.insertProveedor(new Proveedor("Pedro Castillo",
//                        "20494156211",
//                        2,
//                        1,
//                        1));
                mainCallbacks.onMsgFromFragmentToMain("CREATE_PROVEEDOR",0);
            }
        });

        return root;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainCallbacks){
            mainCallbacks = (MainCallbacks) context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement FragmentCallbacks");
        }
    }
}