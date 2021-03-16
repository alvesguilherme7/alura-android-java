package eti.policarto.agenda.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eti.policarto.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static Long contIds = 0L;

    static {

        AlunoDAO alunoDAO = new AlunoDAO();

        Aluno aluno = new Aluno();
        aluno.setNome("Guilherme");
        alunoDAO.salvar(aluno);

        aluno = new Aluno();
        aluno.setNome("Amanda");
        alunoDAO.salvar(aluno);

    }

    public void salvar(Aluno aluno) {
        if(aluno.getId() != null){
            editar(aluno);
            return;
        }

        aluno.setId(contIds);
        AlunoDAO.alunos.add(aluno);
        AlunoDAO.contIds++;
    }

    private Aluno findById(Long id) {
        for (Aluno outro : AlunoDAO.alunos) {
            if(id.equals(outro.getId())){
                return outro;
            }
        }
        return null;
    }

    public void editar(Aluno aluno){
        Aluno alunoLocalizado = findById(aluno.getId());
        if(alunoLocalizado != null){
            alunoLocalizado.setNome(aluno.getNome());
            alunoLocalizado.setEmail(aluno.getEmail());
            alunoLocalizado.setTelefone(aluno.getTelefone());
        }
    }

    public void deleteById(Long id) {
        Aluno aluno = findById(id);
        AlunoDAO.alunos.remove(aluno);
    }

    public List<Aluno> todos() {
        return Collections.unmodifiableList(AlunoDAO.alunos);
    }
}
