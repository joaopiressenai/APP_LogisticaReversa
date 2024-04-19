package com.palazzo.logisticareversa;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private FirebaseApi firebaseApi;
    private ListView listaItens;
    private ItemListAdapter itemListAdapter;
    private ArrayAdapter<Item> adapter;
    private ArrayList<Item> listaFiltrada; // Esta será a lista filtrada exibida na tela

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        listaItens = findViewById(R.id.listView);

        ArrayList<Item> itens = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itens
        );

        itemListAdapter = new ItemListAdapter(this, itens);
        listaItens.setAdapter(itemListAdapter);

        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, InformacoesItem.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);

        MenuItem buscaItem = menu.findItem(R.id.busca);
        SearchView searchView = (SearchView) buscaItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filtrarResultados(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarResultados(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void filtrarResultados(String query) {
        if (listaItens == null || listaItens.getAdapter() == null) {
            Log.e("Erro", "A listaItens ou seu adaptador é nulo");
            return;
        }

        ItemListAdapter adaptador = (ItemListAdapter) listaItens.getAdapter();

        // Verifica se listaFiltrada é nula e a inicializa, se necessário
        if (listaFiltrada == null) {
            listaFiltrada = new ArrayList<>();
        } else {
            listaFiltrada.clear(); // Limpa a listaFiltrada para evitar duplicatas
        }

        if (query.isEmpty()) {
            listaFiltrada.addAll(adaptador.getItens());
        } else {
            for (Item item : adaptador.getItens()) {
                if (item != null && item.getStatus() != null && removerAcentos(item.getStatus()).toLowerCase().contains(removerAcentos(query).toLowerCase())) {
                    listaFiltrada.add(item);
                }
            }
        }
        atualizarExibicaoResultados(query);
    }

    // Método para remover acentuação de uma string
    public static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }



    private void atualizarExibicaoResultados(String query) {
        ItemListAdapter adapter = (ItemListAdapter) listaItens.getAdapter();
        if (listaItens.getAdapter() instanceof ItemListAdapter) {
            adapter.atualizarLista(listaFiltrada);
            listaItens.setAdapter(adapter);
        }
        if (query.equals("")) {
            Log.e("FILTRO sem nada", "");
            firebaseApi.buscarItens();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_criar_item) {
            Intent telaCriar = new Intent(this, CriarItemActivity.class);
            startActivity(telaCriar);
        } else if(item.getItemId() == R.id.menu_abrir_grafico) {
            Intent telaGrafico = new Intent(this, GraficoActivity.class);
            startActivity(telaGrafico);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseApi = new FirebaseApi(this, listaItens, adapter);
        firebaseApi.buscarItens();
        configurarClicks();
    }

    private void configurarClicks() {
        listaItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Item i = firebaseApi.getItem(position);
                return true;
            }
        });
    }
}