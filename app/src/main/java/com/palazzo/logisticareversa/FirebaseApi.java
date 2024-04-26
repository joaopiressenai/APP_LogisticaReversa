package com.palazzo.logisticareversa;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseApi {
    private static final String TABELA_NOME ="/pedidos";
    private final Activity activity;
    private ListView listViewItens;
    private ItemListAdapter adapter;
    private ArrayList<Item> itens;
    private FirebaseFirestore firestore;

    public FirebaseApi(Context context, ListView listView, ItemListAdapter adapter) {
        this.activity = (AppCompatActivity) context;
        this.listViewItens = listView;
        this.adapter = adapter;
        this.firestore = FirebaseFirestore.getInstance();
    }

    public FirebaseApi(Activity activity) {this.activity = activity;}

    public void criarItem(Item item, String message) {
        FirebaseFirestore.getInstance()
                .collection(TABELA_NOME)
                .add(item)
                .addOnSuccessListener(documentReference -> {
                    item.setId(documentReference.getId());
                    atualizarId(item);

                    Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    activity.finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(activity.getApplicationContext(), "Erro ao criar tarefa", Toast.LENGTH_LONG).show();
                });
    }

    public void buscarItens() {
        if(adapter != null) {
            firestore.collection(TABELA_NOME)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        List<Item> itens = new ArrayList<>();
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Item item = snapshot.toObject(Item.class);
                            if (item != null) {
                                itens.add(item);
                            }
                        }
                        adapter.atualizarLista(itens);
                    });
        }
    }

    public ItemListAdapter getItemListAdapter() {
        return adapter;
    }

    public Item getItem(int posicao) {return itens.get(posicao);}

    public void removerItem(Item item, String message) {
        FirebaseFirestore.getInstance().collection(TABELA_NOME)
                .document(item.getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(aVoid -> {
                    Toast.makeText(activity.getApplicationContext(), "Erro ao remover item", Toast.LENGTH_LONG).show();
                });
    }

    private void atualizarId(Item item) {
        FirebaseFirestore.getInstance().collection(TABELA_NOME)
                .document(item.getId())
                .set(item)
                .addOnSuccessListener(aVoid -> {});
    }
}