package br.com.agenda.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.agenda.model.Aluno;

public class AlunoDAO {
    private static long id = 1;

    private final static List<Aluno> lista = new ArrayList<>();

    public void salva(Aluno aluno) {
        aluno.setId(id++);
        lista.add(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(lista);
    }

    public void editar(Aluno aluno){
        Aluno atual = null;
        //Como usei o streams precisa vertificar o SDK
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            atual = lista
                    .stream()
                    .filter(item -> item.getId() == aluno.getId())
                    .collect(Collectors.toList())
                    .get(0);
        }

        if(atual != null){
            int posicao = lista.indexOf(atual);
            lista.set(posicao, aluno);
        }

    }
}
