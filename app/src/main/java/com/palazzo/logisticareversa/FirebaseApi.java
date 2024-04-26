package com.palazzo.logisticareversa;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public void buscarItemPorNumeroPedido(int numeroPedido, OnSuccessListener<Item> onSuccessListener, OnFailureListener onFailureListener) {
        firestore.collection(TABELA_NOME)
                .whereEqualTo("numeroPedido", numeroPedido)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                        Item item = documentSnapshot.toObject(Item.class);
                        onSuccessListener.onSuccess(item);
                    } else {
                        onFailureListener.onFailure(new Exception("Item não encontrado"));
                    }
                })
                .addOnFailureListener(onFailureListener);
    }



    public ItemListAdapter getItemListAdapter() {
        return adapter;
    }

    public Item getItem(int posicao) {return itens.get(posicao);}

    private void atualizarId(Item item) {
        FirebaseFirestore.getInstance().collection(TABELA_NOME)
                .document(item.getId())
                .set(item)
                .addOnSuccessListener(aVoid -> {});
    }
}