package eti.policarto.agenda.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import eti.policarto.agenda.R;
import eti.policarto.agenda.dao.AlunoDAO;
import eti.policarto.agenda.model.Aluno;

import static eti.policarto.agenda.ui.activity.ConstantesActivities.*;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(ListaAlunosActivity.TITULO_APPBAR);

        configurarFabNovoAluno();
    }

    private void configurarFabNovoAluno() {
        final FloatingActionButton botaoMais = findViewById(R.id.active_lista_alunos_fab_novo_aluno);
        botaoMais.setOnClickListener(view -> startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarLista();
    }

    private void configurarLista() {
        ListView listaAlunos = findViewById(R.id.active_lista_de_alunos_list_view);
        configurarListaAdapterView(listaAlunos, new AlunoDAO().todos());
        configurarListaClickListener(listaAlunos);
        configurarListaDuploListener(listaAlunos);
    }

    private void configurarListaClickListener(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((adapterView, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
            abrirFormularioAluno(alunoEscolhido);
        });
    }

    private void configurarListaDuploListener(ListView listaAlunos) {
        listaAlunos.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
            new AlunoDAO().deleteById(alunoEscolhido.getId());
            adapterView.getAdapter().notify();
            return true;//informa que vamos consumir o listener... evitando que ele acione outro listener
        });
    }

    private void abrirFormularioAluno(Aluno aluno) {
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_ALUNO, aluno);
        startActivity(intent);
    }

    private void configurarListaAdapterView(ListView list, List<Aluno> alunos) {
        ArrayAdapter<Aluno> alunoArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos);
        alunoArrayAdapter.notifyDataSetChanged();
        list.setAdapter(alunoArrayAdapter);
    }
}
