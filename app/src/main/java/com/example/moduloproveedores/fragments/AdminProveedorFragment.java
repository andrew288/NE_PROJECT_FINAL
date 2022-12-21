package com.example.moduloproveedores.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AdminProveedorFragment extends Fragment {

    private int id;
    private TextView tvProName;
    private TextView tvProRuc;
    private TextView tvProId;
    private TextView tvProEstReg;
    private Spinner etProTipPro;
    private Spinner etProPais;
    private Button btModificar;
    private Button btEliminar;
    private Button btInactivar;
    private Button btReactivar;
    private Button btCancelar;
    private Button btGuardar;
    private Button btRegresar;
    private List<TipoProveedor> tipoProveedors;
    private List<Pais> paises;
    private List<EstadoRegistro> estadoRegistros;
    private String arrayTipoProveedores[];
    private String arrayPaises[];
    private String FLAG = "";
    private MainCallbacks mainCallbacks;

    public AdminProveedorFragment() {
        // Required empty public constructor
    }

    public static AdminProveedorFragment newInstance(String param1, String param2) {
        AdminProveedorFragment fragment = new AdminProveedorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("ID_PROVEEDOR");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_admin_proveedor, container, false);

        tvProId = root.findViewById(R.id.admin_pro_id);
        tvProName = root.findViewById(R.id.admin_pro_name);
        tvProRuc = root.findViewById(R.id.admin_pro_ruc);
        tvProEstReg = root.findViewById(R.id.admin_pro_est_reg);
        etProTipPro = root.findViewById(R.id.admin_pro_tipo_pro);
        etProPais = root.findViewById(R.id.admin_pro_pais);
        btModificar = root.findViewById(R.id.admin_pro_modificar);
        btEliminar = root.findViewById(R.id.admin_pro_eliminar);
        btInactivar = root.findViewById(R.id.admin_pro_inactivar);
        btReactivar = root.findViewById(R.id.admin_pro_reactivar);
        btCancelar = root.findViewById(R.id.admin_pro_cancelar);
        btGuardar = root.findViewById(R.id.admin_pro_guardar);
        btRegresar = root.findViewById(R.id.admin_pro_regresar);

        // VALORES POR DEFECTO
        tvProId.setEnabled(false);
        tvProName.setEnabled(false);
        tvProRuc.setEnabled(false);
        tvProEstReg.setEnabled(false);
        etProTipPro.setEnabled(false);
        etProPais.setEnabled(false);
        btCancelar.setEnabled(false);
        btGuardar.setEnabled(false);

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

        // ESTABLECER VALROES DE PROVEEDOR
        Proveedor proveedor = daoProveedor.getProveedorById(id);
        tvProId.setText(String.valueOf(proveedor.getId()));
        tvProName.setText(proveedor.getNombre());
        tvProRuc.setText(proveedor.getRUC());
        tvProEstReg.setText(estadoRegistros.get(proveedor.getIdEstReg() - 1).getDescription());
        String sProTipPro = daoTipoProveedor.getTipoProveedorById(proveedor.getIdTipPro()).getName();
        String sProPais = daoPais.getPaisById(proveedor.getIdPais()).getName();
        etProTipPro.setSelection(adapterTipoProveedor.getPosition(sProTipPro));
        etProPais.setSelection(adapterPais.getPosition(sProPais));

        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FLAG = "MODIFICAR";

                //ACCIONES
                tvProName.setEnabled(true);
                tvProRuc.setEnabled(true);
                etProTipPro.setEnabled(true);
                etProPais.setEnabled(true);
                btCancelar.setEnabled(true);
                btGuardar.setEnabled(true);
                btEliminar.setEnabled(false);
                btInactivar.setEnabled(false);
                btReactivar.setEnabled(false);

            }
        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FLAG = "ELIMINAR";
                tvProName.setEnabled(false);
                tvProRuc.setEnabled(false);
                etProTipPro.setEnabled(false);
                etProPais.setEnabled(false);
                btCancelar.setEnabled(true);
                btGuardar.setEnabled(true);
                btInactivar.setEnabled(false);
                btReactivar.setEnabled(false);
                btModificar.setEnabled(false);
            }
        });

        btInactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FLAG = "INACTIVAR";
                tvProName.setEnabled(false);
                tvProRuc.setEnabled(false);
                etProTipPro.setEnabled(false);
                etProPais.setEnabled(false);
                btCancelar.setEnabled(true);
                btGuardar.setEnabled(true);
                btReactivar.setEnabled(false);
                btModificar.setEnabled(false);
                btEliminar.setEnabled(false);
            }
        });

        btReactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FLAG = "REACTIVAR";
                tvProName.setEnabled(false);
                tvProRuc.setEnabled(false);
                etProTipPro.setEnabled(false);
                etProPais.setEnabled(false);
                btCancelar.setEnabled(true);
                btGuardar.setEnabled(true);
                btModificar.setEnabled(false);
                btEliminar.setEnabled(false);
                btInactivar.setEnabled(false);

            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvProId.setText(String.valueOf(proveedor.getId()));
                tvProName.setText(proveedor.getNombre());
                tvProRuc.setText(proveedor.getRUC());
                tvProEstReg.setText(estadoRegistros.get(proveedor.getIdEstReg()-1).getDescription());
                etProTipPro.setSelection(proveedor.getIdTipPro()-1);
                etProPais.setSelection(proveedor.getIdPais()-1);

                //ACCIONES
                FLAG = "";
                tvProName.setEnabled(false);
                tvProRuc.setEnabled(false);
                etProTipPro.setEnabled(false);
                etProPais.setEnabled(false);
                btCancelar.setEnabled(false);
                btGuardar.setEnabled(false);
                btModificar.setEnabled(true);
                btEliminar.setEnabled(true);
                btInactivar.setEnabled(true);
                btReactivar.setEnabled(true);
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (FLAG){
                    case "MODIFICAR":
                        proveedor.setNombre(tvProName.getText().toString());
                        proveedor.setRUC(tvProRuc.getText().toString());
                        int positionTipPro, positionPais;
                        positionTipPro = etProTipPro.getSelectedItemPosition() + 1;
                        positionPais = etProPais.getSelectedItemPosition() + 1;
                        proveedor.setIdTipPro(positionTipPro);
                        proveedor.setIdPais(positionPais);
                        // ACTUALIZAMOS EN LA BASE DE DATOS
                        daoProveedor.updateProveedor(proveedor);
                        break;
                    case "ELIMINAR":
                        proveedor.setIdEstReg(3);
                        tvProEstReg.setText("*");
                        // ACTUALIZAMOS EN LA BASE DE DATOS
                        daoProveedor.updateProveedor(proveedor);
                        break;
                    case "INACTIVAR":
                        proveedor.setIdEstReg(2);
                        tvProEstReg.setText("I");
                        // ACTUALIZAMOS EN LA BASE DE DATOS
                        daoProveedor.updateProveedor(proveedor);
                        break;
                    case "REACTIVAR":
                        proveedor.setIdEstReg(1);
                        tvProEstReg.setText("A");
                        // ACTUALIZAMOS EN LA BASE DE DATOS
                        daoProveedor.updateProveedor(proveedor);
                        break;
                }

                //ACCIONES POSTERIORES
                tvProName.setEnabled(false);
                tvProRuc.setEnabled(false);
                etProTipPro.setEnabled(false);
                etProPais.setEnabled(false);
                btCancelar.setEnabled(false);
                btGuardar.setEnabled(false);
                btModificar.setEnabled(true);
                btEliminar.setEnabled(true);
                btInactivar.setEnabled(true);
                btReactivar.setEnabled(true);
            }
        });

        btRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FLAG = "";
                tvProName.setEnabled(false);
                tvProRuc.setEnabled(false);
                etProTipPro.setEnabled(false);
                etProPais.setEnabled(false);
                btCancelar.setEnabled(false);
                btGuardar.setEnabled(false);
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