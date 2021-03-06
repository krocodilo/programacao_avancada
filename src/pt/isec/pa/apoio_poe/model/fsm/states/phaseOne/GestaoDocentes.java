package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class GestaoDocentes extends StateAdapter {

    public GestaoDocentes(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void addDocente(Docente newDocente) throws Exception {

        checkIfLocked();

        if( data.getDocentes().contains( newDocente ) )
            throw new Exception("Docente ja existe: " + newDocente.toString());

        if( data.emailExists( newDocente.getEmail() ) )
            throw new Exception("Ja existe um aluno/docente com o mesmo email: " +
                    newDocente.toString());
        data.getDocentes().add( newDocente );
    }

    @Override
    public void editDocente(Docente newVersionDocente) throws Exception {
        //TODO : meta2
        checkIfLocked();
    }

    @Override
    public void removeDocente(Docente toRemove) throws Exception {
        //TODO : meta2
        checkIfLocked();

        data.getDocentes().remove( toRemove );
    }

    @Override
    public IState getPreviousState() {
        return new PhaseOne(context, data);
    }

    @Override
    public State getState() {
        return State.GESTAO_DOCENTES;
    }

    public void checkIfLocked() throws Exception {
        super.checkIfLocked( data.phaseOneLocked );
    }
}
