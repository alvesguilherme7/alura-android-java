package eti.policarto.agenda.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import eti.policarto.agenda.R;
import eti.policarto.agenda.dao.AlunoDAO;
import eti.policarto.agenda.model.Aluno;

import static eti.policarto.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO = "Cadastrando Aluno";
    private static final String TITULO_APPBAR_EDIT = "Editando Aluno";

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        iniciarCampos();
        configurarBotaoSalvar();
        preencherAluno();
    }

    private void preencherAluno() {
        this.aluno = (Aluno) getIntent().getSerializableExtra(CHAVE_ALUNO);
        boolean ehEdicao = this.aluno != null;
        if(ehEdicao){
            setTitle(TITULO_APPBAR_EDIT);
            this.campoNome.setText(aluno.getNome());
            this.campoTelefone.setText(aluno.getTelefone());
            this.campoEmail.setText(aluno.getEmail());
        }else{
            setTitle(TITULO_APPBAR_NOVO);
            this.aluno = new Aluno();
        }
    }

    private void configurarBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(view -> salvarAlunoEFechar(criarAluno()));
    }

    private void iniciarCampos() {
        this.campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        this.campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        this.campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvarAlunoEFechar(Aluno aluno) {
        new AlunoDAO().salvar(aluno);
        finish();
    }

    private Aluno criarAluno() {
        this.aluno.setNome(this.campoNome.getText().toString());
        this.aluno.setTelefone(this.campoTelefone.getText().toString());
        this.aluno.setEmail(this.campoEmail.getText().toString());
        return this.aluno;
    }

}