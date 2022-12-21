package com.example.moduloproveedores.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoPais {
    @Query("SELECT * FROM pais")
    List<Pais> getPaises();

    @Query("SELECT * FROM pais WHERE id= :id")
    Pais getPaisById(int id);

    @Insert
    void insertPais(Pais...pais);

    @Update
    public void updatePais(Pais...pais);

    @Delete
    public void deletePais(Pais...pais);
}
