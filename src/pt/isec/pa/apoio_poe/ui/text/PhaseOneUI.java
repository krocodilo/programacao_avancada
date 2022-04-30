package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

import static pt.isec.pa.apoio_poe.utils.IO.*;

public class PhaseOneUI {

    final private Context fsm;

    public PhaseOneUI(Context fsm) {
        this.fsm = fsm;
    }

    public void gestaoAlunos() {

        while (true) {
            printMenu("Gestao de Alunos",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "0 - Voltar");
            switch (readOption(null, 0, 4)) {
                case 1 -> {
                    /*Aluno tmp = new Aluno(
                            readLong("Numero de Aluno"),
                            prompt("Nome"),
                            prompt("E-mail"),
                            readCertainString("Curso", "LEI", "LEI-PL"),
                            readCertainString("Ramo", "DA", "RAS", "SI"),
                            readDouble("Classificacao"),
                            readBoolean("Pode estagiar")
                    );
                    fsm.addAluno(tmp);*/
                    try{
                        ArrayList<Aluno> alunos = FileUtils.readAlunosFromCSV(
                                prompt("Ficheiro de alunos")
                        );
                        fsm.addAlunos( alunos );
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }

                    Aluno tmp = new Aluno(2022987654,"Carlos Picoto","a2022987654@isec.pt","LEI-PL","SI",0.123,true);
                    fsm.addAluno(tmp);
                }
                case 2 ->
                    showList( fsm.getAlunos() );
                case 3 -> {
                    Aluno selected = selectOneFrom( fsm.getAlunos() );
                }
                case 4 -> {
                    Aluno selected = selectOneFrom( fsm.getAlunos() );
                    if( readBoolean("Are you sure you want to delete it?") )
                        fsm.removeAluno( selected );
                }
                case 0 -> {
                    fsm.previousState();
                    return;
                }
            }
        }
    }

    public void gestaoDocentes() {

        while (true) {
            printMenu("Gestao de Docentes",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "\n0 - Voltar");
            switch (readOption(null, 0, 4)) {
                case 1 -> {
                    Docente tmp = new Docente(
                            prompt("Nome"),
                            prompt("E-mail")
                    );
                    fsm.addDocente( tmp );
                }
                case 2 ->
                    showList(fsm.getAlunos());
                case 3 -> {
                    Aluno selected = selectOneFrom( fsm.getAlunos() );
                }
                case 4 -> {
                    Aluno selected = selectOneFrom( fsm.getAlunos() );
                    if( readBoolean("Are you sure you want to delete it?") )
                        fsm.removeAluno( selected );
                }
                case 0 -> { return; }
            }
        }
    }


    private <E> E selectOneFrom(List<E> list) {
        showList( list );
        return list.get(
                readOption("Select the index from the list: ", 0, list.size())
        );
    }
}
