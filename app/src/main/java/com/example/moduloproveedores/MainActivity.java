package com.example.moduloproveedores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.moduloproveedores.fragments.AdminProveedorFragment;
import com.example.moduloproveedores.fragments.CreateProveedorFragment;
import com.example.moduloproveedores.fragments.ListProveedoresFragment;
import com.example.moduloproveedores.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity implements MainCallbacks{

    AdminProveedorFragment adminProveedorFragment = new AdminProveedorFragment();
    ListProveedoresFragment listProveedoresFragment = new ListProveedoresFragment();
    LoginFragment loginFragment = new LoginFragment();
    CreateProveedorFragment createProveedorFragment = new CreateProveedorFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(listProveedoresFragment);
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    @Override
    public void onMsgFromFragmentToMain(String sender, int value) {
        if (sender.equals("LIST_PROVEEDOR")){
            try {
                loadFragment(listProveedoresFragment);
            } catch (Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
        if (sender.equals("CREATE_PROVEEDOR")){
            try {
                loadFragment(createProveedorFragment);
            } catch (Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}