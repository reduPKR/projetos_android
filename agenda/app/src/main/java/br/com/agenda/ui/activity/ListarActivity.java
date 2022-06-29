package br.com.agenda.ui.activity;

import static br.com.agenda.ui.activity.Contantes.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
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
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configurarFabNovoAluno();
        configuraLista();
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
        atualizarAlunos();
    }

    private void atualizarAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_list_view);

        configuraAdapter(listaDeAlunos);
        configuraListenerCreate(listaDeAlunos);
        //configurarListenerDelete(listaDeAlunos); foi substituido pelo de baixo
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }

//    Foi substituido pelo debaixo pos o usuario pode excluir sem querer
//    private void configurarListenerDelete(ListView listaDeAlunos){
//        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
//                Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
//                dao.remove(aluno);
//                adapter.remove(aluno);
//                return true;
//            }
//        });
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add("Delete");
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //if(item.getTitle().equals("Remover")) não é seguro comparar por string
        if( item.getItemId() == R.id.activity_lista_alunos_menu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = adapter.getItem(menuInfo.position);
            remover(aluno);
        }

        return super.onContextItemSelected(item);
    }

    private void remover(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
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
