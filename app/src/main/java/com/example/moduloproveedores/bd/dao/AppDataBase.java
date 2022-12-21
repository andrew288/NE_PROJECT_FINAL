package com.example.moduloproveedores.bd.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        version = 1,
        entities = {EstadoRegistro.class,
                Pais.class,
                Proveedor.class,
                TipoProveedor.class}
)
public abstract class AppDataBase extends RoomDatabase {
    public static AppDataBase INSTANCE;

    public abstract DaoProveedor daoProveedor();
    public abstract DaoPais daoPais();
    public abstract DaoEstadoRegistro daoEstadoRegistro();
    public abstract DaoTipoProveedor daoTipoProveedor();

    public static AppDataBase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDataBase.class, "proveedores.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
