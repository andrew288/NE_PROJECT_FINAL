package com.example.moduloproveedores.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoEstadoRegistro {
    @Query("SELECT * FROM estado_registro")
    List<EstadoRegistro> getEstadoRegistros();

    @Query("SELECT * FROM estado_registro WHERE id= :id")
    EstadoRegistro getEstadoRegistroById(int id);

    @Insert
    void insertEstadoRegistro(EstadoRegistro...estadoRegistros);

    @Update
    public void updateEstadoRegistro(EstadoRegistro...estadoRegistros);

    @Delete
    public void deleteEstadoRegistro(EstadoRegistro...estadoRegistros);
}
