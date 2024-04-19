package com.palazzo.logisticareversa;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirebaseApi {
    private static final String TABELA_NOME ="/pedidos";
    private final Activity activity;
    private ListView listViewItens;
    private ArrayAdapter<Item> adapter;
    private List<Item> itens;

    public FirebaseApi(Activity activity, ListView listViewLivros, ArrayAdapter<Item> adapter) {
        this.activity = activity;
        this.listViewItens = listViewLivros;
        this.adapter = adapter;
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

    public void buscarItens () {
        ArrayList<Item> itens = new ArrayList<>();

        FirebaseFirestore
                .getInstance()
                .collection(TABELA_NOME)
                .addSnapshotListener((value, error) -> {
                    List<DocumentChange> dcs = value.getDocumentChanges();

                    for (DocumentChange doc: dcs) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            Item i = doc.getDocument().toObject(Item.class);
                            itens.add(i);
                        }
                    }
                    adapter = new ArrayAdapter<>(
                            activity.getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            itens
                    );
                    ItemListAdapter adapter = new ItemListAdapter(activity, itens);
                    listViewItens.setAdapter(adapter);
                });
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