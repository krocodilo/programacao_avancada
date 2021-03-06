package pt.isec.pa.apoio_poe.ui.gui.phaseFour;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.io.File;

public class GestaoOrientadoresGUI extends BorderPane {

    ModelManager model;

    Button btnAssign;
    Button btnEdit;
    Button btnDelete;
    Button btnPrevious;
    Text txtOrientadoresCount;
    Text txtWarning;
    boolean warningWasShown = false;
    ListView<Docente> list;
    ChoiceBox<Docente> cbDocente;
    ChoiceBox<Projeto> cbProjeto;

    public GestaoOrientadoresGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        list = new ListView<>();
        list.getItems().addAll( model.getOrientadores() );

        txtOrientadoresCount = new Text("Numero de Orientadores: " + model.getOrientadores().size());
        txtOrientadoresCount.setVisible(true);
        btnAssign = new Button("Atribuir");
        btnEdit = new Button("Editar");
        btnDelete = new Button("Eliminar");
        btnDelete.setStyle("-fx-background-color: red; -fx-text-fill: white");
        txtWarning = new Text(); txtWarning.setVisible(false);
        txtWarning.setFill(Color.RED);

        cbProjeto = new ChoiceBox<>();
        cbDocente = new ChoiceBox<>();

        HBox form = new HBox(cbProjeto ,cbDocente, btnAssign);
        form.setSpacing(10);
        form.setAlignment(Pos.CENTER);

        HBox editDelete = new HBox(btnEdit, btnDelete);
        editDelete.setSpacing(10);
        editDelete.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(form, editDelete);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        btnPrevious = new Button("Voltar");
        VBox vbox = new VBox(list, txtOrientadoresCount, hbox, txtWarning, btnPrevious);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> update());
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> update() );

        btnPrevious.setOnAction(actionEvent -> model.previous() );

        btnDelete.setOnAction(actionEvent -> {
            try {
                if( list.getSelectionModel().getSelectedItem() != null ){
                    model.removeOrientador( list.getSelectionModel().getSelectedItem() );
                } else
                    throw new Exception("Nenhum orientador foi selecionado!");
            } catch (Exception e) {
                txtWarning.setText(e.getMessage());
            }
        });

        btnAssign.setOnAction(actionEvent -> {
            if(cbProjeto.getSelectionModel().getSelectedItem() != null &&
                    cbDocente.getSelectionModel().getSelectedItem() != null)
            {
                model.atribuicaoOrientadorProposta(
                        cbDocente.getSelectionModel().getSelectedItem(),
                        cbProjeto.getSelectionModel().getSelectedItem()
                );
            }
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.GESTAO_ORIENTADORES);
        list.getItems().clear();
        list.getItems().addAll( model.getOrientadores() );
        txtOrientadoresCount.setText("Numero de Orientadores: " + model.getOrientadores().size());
        if( !txtWarning.isVisible() && !txtWarning.getText().isBlank()){
            txtWarning.setVisible(true);
            warningWasShown = true;
        }
        else if(txtWarning.isVisible() && warningWasShown ){
            txtWarning.setText("");
            txtWarning.setVisible(false);
        }

        cbProjeto.getItems().clear();
        cbDocente.getItems().clear();
        cbProjeto.getItems().addAll( model.getProjetos() );
        cbDocente.getItems().addAll( model.getDocentes() );
    }

}
