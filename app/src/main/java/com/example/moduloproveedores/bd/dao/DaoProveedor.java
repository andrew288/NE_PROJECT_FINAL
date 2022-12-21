package com.example.moduloproveedores.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoProveedor {
    @Query("SELECT * FROM proveedor")
    List<Proveedor> getProveedores();

    @Query("SELECT * FROM proveedor WHERE id= :id")
    Proveedor getProveedorById(int id);

    @Query("SELECT * FROM proveedor WHERE idPais= :idPais")
    List<Proveedor> getProveedoresByPais(int idPais);

    @Query("SELECT * FROM proveedor WHERE idTipPro= :idTipPro")
    List<Proveedor> getProveedorByTipoProveedor(int idTipPro);

    @Insert
    void insertProveedor(Proveedor...proveedors);

    @Update
    public void updateProveedor(Proveedor proveedors);

    @Delete
    public void deleteProveedor(Proveedor...proveedors);
}
