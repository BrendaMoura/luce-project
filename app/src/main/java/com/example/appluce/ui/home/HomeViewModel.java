package com.example.appluce.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appluce.Usuario;
import com.example.appluce.config.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Crianca>> lista;
    List<Crianca> criancaInfo = new ArrayList<Crianca>();


    public HomeViewModel() {


    }

    private String TAG = HomeFragment.class.getSimpleName();

    public void setLista(List listaC) {
        lista = new MutableLiveData<>();
        lista.setValue(listaC);
    }

    MutableLiveData<List<Crianca>> getLista() {
        return lista;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

}