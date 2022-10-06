package com.example.appluce.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.appluce.AreaCrianca;
import com.example.appluce.ComecarJogo;
import com.example.appluce.Indicador;
import com.example.appluce.R;
import com.example.appluce.Usuario;
import com.example.appluce.config.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Usuario usuario = new Usuario();
    Crianca dependente = new Crianca();
    List<Crianca> criancaInfo = new ArrayList<Crianca>();
    private HomeViewModel homeViewModel;
    String id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView listview = root.findViewById(R.id.listview);

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        final DatabaseReference referencia = firebase.child("usuario").child(Usuario.getId2()).child("dependente");
        //Pegar dados do firebase
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cont = 0;
                criancaInfo.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    cont ++;
                    id = objSnapshot.getKey();
                    dependente.setId(id);
                    Crianca.setId2(id);
                    dependente = objSnapshot.getValue(Crianca.class);
                    criancaInfo.add(dependente);
                }
                if(cont == 0){
                    Toast.makeText(getContext(), "Adicione dependentes!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), ComecarJogo.class);
                    startActivity(intent);
                }
                else{
                    Adaptador<Crianca> adapter = new Adaptador<>(getContext(), R.layout.item, criancaInfo);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            AreaCrianca.criancaInfoA = criancaInfo;
                            AreaCrianca.posicao = position;
                            Intent intent = new Intent(getContext(), AreaCrianca.class);
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ocorreu um erro ao conectar com o banco de dados", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}