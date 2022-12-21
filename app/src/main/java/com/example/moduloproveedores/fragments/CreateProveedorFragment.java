package com.example.moduloproveedores.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moduloproveedores.MainCallbacks;
import com.example.moduloproveedores.R;
import com.example.moduloproveedores.bd.dao.AppDataBase;
import com.example.moduloproveedores.bd.dao.DaoEstadoRegistro;
import com.example.moduloproveedores.bd.dao.DaoPais;
import com.example.moduloproveedores.bd.dao.DaoProveedor;
import com.example.moduloproveedores.bd.dao.DaoTipoProveedor;
import com.example.moduloproveedores.bd.dao.EstadoRegistro;
import com.example.moduloproveedores.bd.dao.Pais;
import com.example.moduloproveedores.bd.dao.Proveedor;
import com.example.moduloproveedores.bd.dao.TipoProveedor;

import java.util.List;

public class CreateProveedorFragment extends Fragment {

    private TextView tvProName;
    private TextView tvProRuc;
    private Spinner etProTipPro;
    private Spinner etProPais;
    private Button btCrear;
    private Button btCancelar;
    private List<TipoProveedor> tipoProveedors;
    private List<Pais> paises;
    private List<EstadoRegistro> estadoRegistros;
    private String arrayTipoProveedores[];
    private String arrayPaises[];
    private MainCallbacks mainCallbacks;


    public CreateProveedorFragment() {
        // Required empty public constructor
    }

    public static CreateProveedorFragment newInstance(String param1, String param2) {
        CreateProveedorFragment fragment = new CreateProveedorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create_proveedor, container, false);
        tvProName = root.findViewById(R.id.create_pro_name);
        tvProRuc = root.findViewById(R.id.create_pro_ruc);
        etProTipPro = root.findViewById(R.id.create_pro_tipo_pro);
        etProPais = root.findViewById(R.id.create_pro_pais);
        btCrear = root.findViewById(R.id.create_pro_create);
        btCancelar = root.findViewById(R.id.create_pro_cancelar);

        // INICIALIZAR BASE DE DATOS
        AppDataBase db = AppDataBase.getInstance(this.getContext());
        DaoProveedor daoProveedor = db.daoProveedor();
        DaoTipoProveedor daoTipoProveedor = db.daoTipoProveedor();
        DaoPais daoPais = db.daoPais();
        DaoEstadoRegistro daoEstadoRegistro = db.daoEstadoRegistro();

        // ESTABLECER VALORES DE TABLAS REFERENCIALES
        estadoRegistros = daoEstadoRegistro.getEstadoRegistros();
        tipoProveedors = daoTipoProveedor.getTiposProveedor();
        paises = daoPais.getPaises();

        // DROPDOWNS DE VIEW
        arrayTipoProveedores = addAdaptersTipoProDropDowns();
        arrayPaises = addAdaptersPaisDropDowns();

        ArrayAdapter<String> adapterTipoProveedor = new ArrayAdapter<String>( getActivity(),
                R.layout.list_item_dropdown,
                arrayTipoProveedores);
        ArrayAdapter<String> adapterPais = new ArrayAdapter<String>( getActivity(),
                R.layout.list_item_dropdown,
                arrayPaises);

        etProTipPro.setAdapter(adapterTipoProveedor);
        etProPais.setAdapter(adapterPais);

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvProName.getText().toString().isEmpty() && tvProRuc.getText().toString().length() == 11){
                    int positionTipPro, positionPais;
                    positionTipPro = etProTipPro.getSelectedItemPosition() + 1;
                    positionPais = etProPais.getSelectedItemPosition() + 1;

                    Proveedor proveedor = new Proveedor(
                            tvProName.getText().toString(),
                            tvProRuc.getText().toString(),
                            positionTipPro,
                            positionPais,
                            1);
                    daoProveedor.insertProveedor(proveedor);
                    mainCallbacks.onMsgFromFragmentToMain("LIST_PROVEEDOR",0);
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainCallbacks.onMsgFromFragmentToMain("LIST_PROVEEDOR",0);
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

    private String[] addAdaptersTipoProDropDowns(){
        String aux1[] = new String[tipoProveedors.size()];

        for(int i=0; i<tipoProveedors.size(); i++){
            aux1[i] = tipoProveedors.get(i).getName();
        }
        return aux1;
    }

    private String[] addAdaptersPaisDropDowns(){
        String aux2[] = new String[paises.size()];

        for(int i=0; i<paises.size(); i++){
            aux2[i] = paises.get(i).getName();
        }
        return aux2;
    }
}