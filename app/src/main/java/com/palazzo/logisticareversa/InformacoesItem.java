package com.palazzo.logisticareversa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class InformacoesItem extends AppCompatActivity {

    private FirebaseApi firebaseApi;
    private ListView listaItemSelecionado;

    private TextView textGarrafasQuebradas;
    private TextView textGarrafasReciclagem;
    private TextView textGarrafasReclamacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_item);

        Toolbar toolbar = findViewById(R.id.toolbarInformacoes);
        setSupportActionBar(toolbar);

        listaItemSelecionado = findViewById(R.id.listItemSelecionado);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InformacoesItem.this.startActivity(new Intent(InformacoesItem.this, MainActivity.class));
            }
        });

        // Referências dos TextViews
        textGarrafasQuebradas = findViewById(R.id.textGarrafasQuebradas);
        textGarrafasReciclagem = findViewById(R.id.textGarrafasReciclagem);
        textGarrafasReclamacoes = findViewById(R.id.textGarrafasReclamacoes);

        // Get the selected item's data from the intent
        Intent intent = getIntent();
        Item selectedItem = (Item) intent.getSerializableExtra("selectedItem");

        if (selectedItem != null) {
            Log.d("InformacoesItem", "Selected Item: " + selectedItem);
        } else {
            Log.e("InformacoesItem", "Selected item is null");
        }

        // Inicializar FirebaseApi
        firebaseApi = new FirebaseApi(this);

        // Carregar as informações fixas
        carregarInformacoes(selectedItem);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregarInformacoes(Item item) {
        // Valores fixos para as informações
        String garrafasQuebradas = "50";
        String garrafasReciclagem = "30";
        String garrafasReclamacoes = "20";

        // Atualizar os TextViews com os valores do item
        textGarrafasQuebradas.setText(garrafasQuebradas);
        textGarrafasReciclagem.setText(garrafasReciclagem);
        textGarrafasReclamacoes.setText(garrafasReclamacoes);

        if (item != null) {
            // Crie um novo adaptador com apenas o item selecionado
            ArrayList<Item> itemList = new ArrayList<>();
            itemList.add(item);
            ItemListAdapter adapter = new ItemListAdapter(this, itemList);

            // Configure o adaptador para a ListView
            listaItemSelecionado.setAdapter(adapter);
        } else {
            // Se o item for nulo, exiba uma mensagem de erro ou faça outra coisa
            Toast.makeText(this, "Item não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

}
