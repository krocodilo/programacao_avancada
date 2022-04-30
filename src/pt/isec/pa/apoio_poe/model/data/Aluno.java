package pt.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private long id;
    private String name;
    private String email;
    private String curso;
    private String ramo;
    private double classificacao;
    private boolean podeEstagiar;

    public Aluno(long id, String name, String email, String curso, String ramo, double classificacao, boolean podeEstagiar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.curso = curso;
        this.ramo = ramo;
        this.classificacao = classificacao;
        this.podeEstagiar = podeEstagiar;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCurso() {
        return curso;
    }

    public String getRamo() {
        return ramo;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public boolean isPodeEstagiar() {
        return podeEstagiar;
    }

    public static Aluno parseAluno(ArrayList<String> params) throws NumberFormatException{
        return new Aluno(
            Long.parseLong( params.get(0) ),
            params.get(1),
            params.get(2),
            params.get(3),
            params.get(4),
            Double.parseDouble( params.get(5) ),
            Boolean.parseBoolean( params.get(6) )
        );
    }
    @Override
    public boolean equals(Object obj) {
        if(getClass() != obj.getClass())
            return false;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).intValue();
    }

    @Override
    public String toString() {
        return String.join("    ", String.valueOf(id), name, email, curso, ramo, String.valueOf(classificacao) );
    }
}