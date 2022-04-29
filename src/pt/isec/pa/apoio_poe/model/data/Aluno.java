package pt.isec.pa.apoio_poe.model.data;

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
}
