package ui;

import br.com.valdevieso.exceptions.ComissariaPilotoSozinhosException;
import br.com.valdevieso.exceptions.MotoristaNaoHabilitadoException;
import br.com.valdevieso.exceptions.OficialSozinhoComChefeServicoException;
import br.com.valdevieso.exceptions.PresidiarioSemFiscalizacaoPolicialException;
import br.com.valdevieso.locais.Terminal;
import br.com.valdevieso.personagens.*;
import br.com.valdevieso.veiculos.Aviao;
import br.com.valdevieso.veiculos.IVeiculo;
import br.com.valdevieso.veiculos.SmartFortwo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView imagemCarro;

    @FXML
    private ImageView imagemTerminal;

    @FXML
    private ImageView imgAviao;

    @FXML
    private Button btnLevarAviao;

    @FXML
    private Button btnLevarTerminal;

    @FXML
    private ListView<String> listaTerminal;

    @FXML
    private ListView<String> listaAviao;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblMotorista;

    @FXML
    private Label lblCarona;

    private boolean estaNoTerminal = true;
    private IVeiculo smart;
    private Terminal terminal;
    private Aviao voo;

    public void iniciar() {
        this.iniciarLocais();
        this.definirPersonagensTerminal();

        try {
            this.iniciarVeiculos();
        } catch (MotoristaNaoHabilitadoException e) {
            e.printStackTrace();
        }
    }

    private void validarCenario() throws OficialSozinhoComChefeServicoException, PresidiarioSemFiscalizacaoPolicialException, ComissariaPilotoSozinhosException {
        for (IPersonagem personagem : this.terminal.getPersonagens()) {
            personagem.validarRestricoes(this.terminal.getPersonagens());
        }

        for (IPersonagem personagem : this.voo.getPassageiros()) {
            personagem.validarRestricoes(this.voo.getPassageiros());
        }

        if (this.smart.getMotorista() != null)
            this.smart.getMotorista().validarRestricoes(this.smart.getTripulantes());

        if (this.smart.getCarona() != null)
            this.smart.getCarona().validarRestricoes(this.smart.getTripulantes());
    }

    private void iniciarLocais() {
        this.terminal = new Terminal();
        this.voo = new Aviao();
    }

    private void iniciarVeiculos() throws MotoristaNaoHabilitadoException {
        this.smart = new SmartFortwo();
    }

    private void definirPersonagensTerminal() {
        this.terminal.addPersonagem(new Piloto());
        this.terminal.addPersonagem(new Oficial());
        this.terminal.addPersonagem(new Oficial());
        this.terminal.addPersonagem(new ChefeServico());
        this.terminal.addPersonagem(new Comissaria());
        this.terminal.addPersonagem(new Comissaria());
        this.terminal.addPersonagem(new Policial());
        this.terminal.addPersonagem(new Presidiario());
    }

    private void atualizarListaTerminal() {
        ObservableList<String> dados = FXCollections.observableArrayList();

        for (IPersonagem personagem : this.terminal.getPersonagens())
            dados.add(personagem.getDescricao());

        listaTerminal.setItems(dados);
    }

    private void atualizarListaAviao() {
        ObservableList<String> dados = FXCollections.observableArrayList();

        for (IPersonagem personagem : this.voo.getPassageiros())
            dados.add(personagem.getDescricao());

        listaAviao.setItems(dados);
    }

    private IPersonagem getPersonagemListaTerminal(String descricao) {
        for (IPersonagem personagem : this.terminal.getPersonagens())
            if (personagem.getDescricao().equals(descricao))
                return personagem;

        return null;
    }

    public void definirCarona() {
        String personagemSelecionado = "";
        IPersonagem caronaAnterior = this.smart.getCarona();

        if (this.listaTerminal.getSelectionModel().getSelectedItems().size() > 0) {
            personagemSelecionado = this.listaTerminal.getSelectionModel().getSelectedItem();
            this.smart.setCarona(this.getPersonagemListaTerminal(personagemSelecionado));
            this.terminal.removerPersonagem(this.smart.getCarona());

            if (caronaAnterior != null)
                this.terminal.addPersonagem(caronaAnterior);
        } else {
            personagemSelecionado = this.listaAviao.getSelectionModel().getSelectedItem();
            this.smart.setCarona(this.getPersonagemListaAviao(personagemSelecionado));
            this.voo.removerPassageiro(this.smart.getCarona());

            if (caronaAnterior != null)
                this.voo.addPassageiro(caronaAnterior);
        }

        this.atualizarListaAviao();
        this.atualizarListaTerminal();

        lblCarona.setText(personagemSelecionado);
        lblStatus.setText("Status: " + personagemSelecionado + " agora esta no carona.");
    }

    private IPersonagem getPersonagemListaAviao(String personagemSelecionado) {
        for (IPersonagem personagem : this.voo.getPassageiros())
            if (personagem.getDescricao() == personagemSelecionado)
                return personagem;

        return null;
    }

    public void definirMotorista() throws MotoristaNaoHabilitadoException {
        String personagemSelecionado = "";
        IPersonagem motoristaAnterior = this.smart.getMotorista();

        try {
            if (this.listaTerminal.getSelectionModel().getSelectedItems().size() > 0) {

                personagemSelecionado = this.listaTerminal.getSelectionModel().getSelectedItem();
                this.smart.setMotorista(this.getPersonagemListaTerminal(personagemSelecionado));
                this.terminal.removerPersonagem(this.smart.getMotorista());

                if (motoristaAnterior != null)
                    this.terminal.addPersonagem(motoristaAnterior);
            } else {
                personagemSelecionado = this.listaAviao.getSelectionModel().getSelectedItem();
                this.smart.setMotorista(this.getPersonagemListaAviao(personagemSelecionado));
                this.voo.removerPassageiro(this.smart.getMotorista());

                if (motoristaAnterior != null)
                    this.voo.addPassageiro(motoristaAnterior);
            }

            atualizarListas();

            lblMotorista.setText(personagemSelecionado);
            lblStatus.setText("Status: " + personagemSelecionado + " agora é o motorista do carro.");
        } catch (MotoristaNaoHabilitadoException e) {
            lblStatus.setText("Status: Motorista não habilitado selecionado.");
        }
    }

    public void levarCaronaParaAviao() throws MotoristaNaoHabilitadoException {
        try {
            validarCenario();
        } catch (Exception e) {
            lblStatus.setText("Conflito: " + e.getMessage());
            return;
        }

        if(this.smart.getMotorista() == null)
            return;

        IPersonagem carona = this.smart.getCarona();
        IPersonagem motorista = this.smart.getMotorista();

        this.voo.addPassageiro(carona);
        this.voo.addPassageiro(motorista);
        this.smart.setCarona(null);
        this.smart.setMotorista(null);
        this.terminal.removerPersonagem(carona);

        atualizarListas();

        lblMotorista.setText("Sem motorista");
        lblCarona.setText("Sem passageiro");
        lblStatus.setText("Status: ocupantes do carro agora estão dentro do avião.");

        estaNoTerminal = false;
        this.atualizarEstadoBotoes();

        if(this.listaTerminal.getItems().size() <= 0)
            this.lblStatus.setText("Status: Todos foram levados para o avião, estamos prontos para partir!");
    }

    public void validarListViews(){
        if(!this.estaNoTerminal)
            this.listaTerminal.getSelectionModel().clearSelection();
        else
            this.listaAviao.getSelectionModel().clearSelection();
    }

    private void atualizarEstadoBotoes(){
        this.btnLevarTerminal.setVisible(!estaNoTerminal);
        this.btnLevarAviao.setVisible(estaNoTerminal);
    }

    public void levarCaronaParaTerminal() throws MotoristaNaoHabilitadoException {
        try {
            validarCenario();
        } catch (Exception e) {
            lblStatus.setText("Conflito: " + e.getMessage());
            return;
        }

        if(this.smart.getMotorista() == null)
            return;

        IPersonagem carona = this.smart.getCarona();
        IPersonagem motorista = this.smart.getMotorista();

        this.voo.removerPassageiro(carona);

        this.smart.setCarona(null);
        this.smart.setMotorista(null);

        this.terminal.addPersonagem(motorista);
        this.terminal.addPersonagem(carona);

        atualizarListas();

        lblMotorista.setText("Sem motorista");
        lblCarona.setText("Sem passageiro");
        lblStatus.setText("Status: ocupantes do carro agora estão de volta ao terminal.");
        estaNoTerminal = true;
        this.atualizarEstadoBotoes();
    }

    private void atualizarListas() {
        this.atualizarListaTerminal();
        this.atualizarListaAviao();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.iniciar();
        this.atualizarListaTerminal();

        imgAviao.setImage(new Image("ui/aviao.png"));
        imagemCarro.setImage(new Image("ui/smart.png"));
        imagemTerminal.setImage(new Image("ui/terminal.jpg"));
    }
}
