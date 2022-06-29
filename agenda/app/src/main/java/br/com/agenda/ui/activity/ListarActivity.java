package br.com.agenda.ui.activity;

import static br.com.agenda.ui.activity.Contantes.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.agenda.R;
import br.com.agenda.dao.AlunoDAO;
import br.com.agenda.model.Aluno;


public class ListarActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configurarFabNovoAluno();
    }

    private void configurarFabNovoAluno() {
        FloatingActionButton botaoAdicionarNovoAluno =
                findViewById(R.id.activity_lista_alunos_FAB_adicionar);
        botaoAdicionarNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormularioInserirAluno();
            }
        });
    }

    private void abrirFormularioInserirAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_list_view);
        final List<Aluno> alunos = dao.todos();

        configuraAdapter(listaDeAlunos, alunos);
        configuraListenerCreate(listaDeAlunos);
    }

    private void configuraAdapter(ListView listaDeAlunos, List<Aluno> alunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));
    }

    private void configuraListenerCreate(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
               // Aluno aluno = alunos.get(posicao);
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
                abrirFormularioEditaAluno(aluno);
            }
        });
    }

    private void abrirFormularioEditaAluno(Aluno aluno) {
        Intent abrirFormulario = new Intent(
                ListarActivity.this,
                FormularioAlunoActivity.class);

        abrirFormulario.putExtra(CHAVE_ALUNO, aluno);
        startActivity(abrirFormulario);
    }

}
