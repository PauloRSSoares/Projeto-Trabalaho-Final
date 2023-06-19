package br.com.senac.trabalho.java.fx.controller;

import br.com.senac.trabalho.java.fx.model.CadastroCliente;
import br.com.senac.trabalho.java.fx.model.CadastroEndereco;
import br.com.senac.trabalho.java.fx.service.CadastroClienteService;
import br.com.senac.trabalho.java.fx.service.CadastroEnderecoService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("/main.fxml")
public class CadastroController {

    //serão declarados os campos da Aba Clientes:

    @FXML
    private TextField documento;

    @FXML
    private TextField nome;

    @FXML
    private TextField rg;

    @FXML
    private TextField email;

    @FXML
    private TextField telefone;

    @FXML
    private TableView<CadastroCliente> tabelaClientes;

    @FXML
    private TableColumn<CadastroCliente, Integer> colunaCodCli;

    @FXML
    private TableColumn<CadastroCliente, String> colunaDocumento;

    @FXML
    private TableColumn<CadastroCliente, String> colunaNome;

    @FXML
    private TableColumn<CadastroCliente, Integer> colunaRg;

    @FXML
    private TableColumn<CadastroCliente, String> colunaEmail;

    @FXML
    private TableColumn<CadastroCliente, String> colunaTelefone;

    // agora serão declarados os campos da Aba Endereços:

    @FXML
    private TextField idEnd;

    @FXML
    private TextField codigoCliente;
    @FXML
    private TextField cep;
    @FXML
    private TextField rua;
    @FXML
    private TextField numero;
    @FXML
    private TextField bairro;
    @FXML
    private TextField cidade;
    @FXML
    private TextField estado;

    @FXML
    private TableView<CadastroEndereco> tabelaEnderecos;

    @FXML
    private TableColumn<CadastroEndereco, Integer> colunaIdEnd;

    @FXML
    private TableColumn<CadastroEndereco, Integer> colunaCodigoCliente;
    @FXML
    private TableColumn<CadastroEndereco, Integer> colunaCep;
    @FXML
    private TableColumn<CadastroEndereco, String> colunaRua;
    @FXML
    private TableColumn<CadastroEndereco, Integer> colunaNumero;
    @FXML
    private TableColumn<CadastroEndereco, String> colunaBairro;
    @FXML
    private TableColumn<CadastroEndereco, String> colunaCidade;
    @FXML
    private TableColumn<CadastroEndereco, String> colunaEstado;

    private int index = -1;

    @FXML
    public void initialize() {

        //Aba Clientes:

        colunaCodCli.setCellValueFactory(new PropertyValueFactory<>("idCli"));
        colunaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabelaClientes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    CadastroCliente cli = (CadastroCliente) tabelaClientes.getSelectionModel().getSelectedItems();
                    documento.setText(cli.getDocumento());
                    nome.setText(cli.getNome());
                    rg.setText(String.valueOf(cli.getRg()));
                    email.setText(cli.getEmail());
                    telefone.setText(cli.getTelefone());
                    index = cli.getIdCli();

                }
            }
        });

        // Aba Endereços:

        colunaIdEnd.setCellValueFactory(new PropertyValueFactory<>("idEnd"));
        colunaCodigoCliente.setCellValueFactory(new PropertyValueFactory<>("codigoCliente"));
        colunaCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        colunaRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colunaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colunaBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        this.carregarListClientes();

        tabelaEnderecos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    CadastroEndereco end = (CadastroEndereco) tabelaEnderecos.getSelectionModel().getSelectedItems();
                    idEnd.setText(String.valueOf(end.getIdEnd()));
                    codigoCliente.setText(String.valueOf(end.getCodigoCliente()));
                    cep.setText(String.valueOf(end.getCep()));
                    rua.setText(String.valueOf(end.getRua()));
                    numero.setText(String.valueOf(end.getNumero()));
                    bairro.setText(end.getBairro());
                    cidade.setText(end.getCidade());
                    estado.setText(end.getEstado());
                    index = end.getIdEnd();
                }
            }
        });
    }

    public void executarSalvar() {

    //Aba Clientes:

        CadastroCliente cli = new CadastroCliente();
        cli.setDocumento(documento.getText());
        cli.setNome(nome.getText());
        if (rg.getText() != null || !rg.getText().trim().isEmpty()) {
            cli.setRg(Integer.parseInt(rg.getText()));
        }
        cli.setEmail(email.getText());
        cli.setTelefone(telefone.getText());
        if (index > -1) {
            CadastroClienteService.atualizarCliente(index, cli);
            index = -1;
        } else {

            if (CadastroClienteService.buscarClienteByDocumento(documento.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(("Alerta"));
                alert.setHeaderText("Documento: " + documento.getText() + "ja existe na base");
                alert.show();
            } else {
                CadastroClienteService.salvarCliente(cli);
                //cli.setDocumento(documento.getText());

            }

        }

        this.carregarListClientes();

        this.limparCampos();

    // Aba Endereços:

        CadastroEndereco end = new CadastroEndereco();
        end.setIdEnd(Integer.parseInt(idEnd.getText()));
        end.setCodigoCliente(Integer.parseInt(codigoCliente.getText()));
        end.setCep(Integer.parseInt(cep.getText()));
        end.setRua(rua.getText());
        end.setNumero(Integer.parseInt(numero.getText()));
        end.setBairro(bairro.getText());
        end.setCidade(cidade.getText());
        end.setEstado(estado.getText());
        if (index > -1) {
            CadastroEnderecoService.atualizarEndereco(index, end);
            index = -1;
        } else {

            if (CadastroEnderecoService.atualizarEndereco(index, end)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(("Alerta"));
                alert.setHeaderText("IDEND: " + idEnd.getText() + "ja existe na base");
                alert.show();
            } else {
                CadastroEnderecoService.salvarEndereco(end);

            }
        }

        this.carregarListEnderecos();

        this.limparCampos();

    }

    public void executarExcluir() {

    // Aba Clientes:

        if (index > -1) {
            CadastroClienteService.deletarCliente(index);
            this.carregarListClientes();
            index = -1;
            this.limparCampos();
        }

    // Aba Clientes:

        if (index > -1) {
            CadastroEnderecoService.deletarEndereco(index);
            this.carregarListEnderecos();
            index = -1;
            this.limparCampos();
        }
    }

    public void limparCampos() {

    // Aba Clientes:

        documento.setText("");
        nome.setText("");
        rg.setText("");
        email.setText("");
        telefone.setText("");


    // Aba Endereços:

        idEnd.setText("");
        codigoCliente.setText("");
        cep.setText("");
        rua.setText("");
        numero.setText("");
        bairro.setText("");
        cidade.setText("");
        estado.setText("");
    }

    public void carregarListClientes() {

    // Aba Clientes:

        try {
            tabelaClientes.getItems().remove(0, tabelaClientes.getItems().size());

            List<CadastroCliente> clienteList = CadastroClienteService.carregarClientes();

            tabelaClientes.getItems().addAll(clienteList);
        } catch (Exception e) {

        }
    }

    public void carregarListEnderecos() {

    // Aba Endereços:
        try {
            tabelaEnderecos.getItems().remove(0, tabelaEnderecos.getItems().size());

            List<CadastroEndereco> enderecoList = CadastroEnderecoService.carregarEndereco();

            tabelaEnderecos.getItems().addAll();
        } catch (Exception e) {

        }
    }
}