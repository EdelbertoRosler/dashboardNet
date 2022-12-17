package Controller;


import dao.MovimentacaoDao;
import dao.TipoMovimentacaoDao;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Categoria;
import model.Movimentacao;
import model.TipoMovimentacao;
import service.CategoriaService;

public class DashController {

    @FXML
    private Button cancel;

    @FXML
    private Label currentBalance;

    @FXML
    private TextField fieldDescription;

    @FXML
    private TableView<Movimentacao> movements;

    @FXML
    private Label expectedBalance;

    @FXML
    private RadioButton salary;

    @FXML
    private RadioButton expense;

    @FXML
    private ComboBox category;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private TextField fieldMov;

    @FXML
    private TextField fieldValue;

    @FXML
    private Button send;
    
    @FXML
    private TableColumn<Movimentacao, String> categoriaColum;
    
     @FXML
    private TableColumn<Movimentacao, LocalDate> dataColum;
     
    @FXML
    private TableColumn<Movimentacao, String> tipoColum;

    @FXML
    private TableColumn<Movimentacao, Double> valorColum;
   
    private Stage stage;
    
    private CategoriaService categoriaService;
    
    private MovimentacaoDao movimentacaoDao;
    
    private TipoMovimentacaoDao tipoMovimentacaoDao;
    
    private List<Categoria> categorias;
    private List<TipoMovimentacao> tiposMovimentacaoList;
    
    private ObservableList<Movimentacao> movimentacoes = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        tipoMovimentacaoDao = new TipoMovimentacaoDao();
        categoriaService = new CategoriaService();
        movimentacaoDao = new MovimentacaoDao();
        
        tiposMovimentacaoList = tipoMovimentacaoDao.getAll();
        categorias = categoriaService.getAll();
        categorias.forEach(c -> category.getItems().add(c.getDescricao()));
        
        List<Movimentacao> movSalvas = movimentacaoDao.getAll();
        popularColuna(movSalvas);
        populaUltimaMov(movSalvas.get(movSalvas.size()-1));
        
        calcularSaldo(movSalvas);
    }

    @FXML
    void handleSend(ActionEvent event) {
        salvarMovimentacao();
    }

    @FXML
    void handleCancel(ActionEvent event) {
       fieldValue.setText("");
       fieldDescription.setText("");
    }
    
    public void setStage(Stage stageDash) {
        this.stage = stageDash;
    }

    private void calcularSaldo(List<Movimentacao> movimentacoes) {
        Double saldoAtual = 0.0;
        Double saldoPrevisto = 0.0;
        for(Movimentacao mov : movimentacoes){
            if(mov.getData().isBefore(LocalDate.now())){
                if(mov.getTipoMovimentacao().getDescricao().equals("Receita")){
                    saldoAtual = saldoAtual + mov.getValor();
                } else if (mov.getTipoMovimentacao().getDescricao().equals("Despesa")){
                    saldoAtual = saldoAtual - mov.getValor();
                }
            } else {
                if(mov.getTipoMovimentacao().getDescricao().equals("Receita")){
                    saldoPrevisto = saldoPrevisto + mov.getValor();
                } else if (mov.getTipoMovimentacao().getDescricao().equals("Despesa")){
                    saldoPrevisto = saldoPrevisto - mov.getValor();
                }
            }
            
        }
        
        configSaldoAtual(saldoAtual);
        configSaldoPrevisto(saldoPrevisto);
    }

    private void popularColuna(List<Movimentacao> movSalvas) {
        
        movimentacoes.addAll(movSalvas);
        movements.getItems().addAll(movimentacoes);
        
        categoriaColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("categoria"));
        tipoColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("tipoMovimentacao"));
        dataColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, LocalDate>("data"));
        valorColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, Double>("valor"));
    }

    private void salvarMovimentacao() {
        
        if(criacaoValida()){
            Movimentacao novaMov = new Movimentacao();
            novaMov.setTipoMovimentacao(getTipo());
            novaMov.setCategoria(getCategoria());
            novaMov.setData(datePicker.getValue());
            novaMov.setValor(Double.parseDouble(fieldValue.getText()));
            novaMov.setDescricao(fieldDescription.getText());
            
            movimentacaoDao.save(novaMov);
            movements.getItems().add(novaMov);
            List<Movimentacao> movSalvas = movimentacaoDao.getAll();
            populaUltimaMov(novaMov);
        
            calcularSaldo(movSalvas);
        }
    }

    private boolean criacaoValida() {
       return category.getValue() != "Categoria"
               && !"".equals(fieldValue.getText())
               && datePicker.getValue() != null;
    }

    private TipoMovimentacao getTipo() {
        if(salary.isSelected()){
            return tiposMovimentacaoList.get(0);     
        } else{
            return tiposMovimentacaoList.get(1);
        }
    }

    private Categoria getCategoria(){
        Optional<Categoria> categoria = categorias.stream()
                .filter(c -> c.getDescricao() == category.getValue()).findFirst();
        
        return categoria.get();
    }

    private void configSaldoAtual(Double saldo) {
        DecimalFormat df = new DecimalFormat("0.00");
        currentBalance.setText("R$ " + df.format(saldo));
        if (saldo >= 0.0){
            currentBalance.setTextFill(Color.color(0.5, 1, 0.5));
        } else{
            currentBalance.setTextFill(Color.color(1, 0, 0));
        }
    }

    private void configSaldoPrevisto(Double saldo) {
        DecimalFormat df = new DecimalFormat("0.00");
        expectedBalance.setText("R$ " + df.format(saldo));
        if (saldo >= 0.0){
            expectedBalance.setTextFill(Color.color(0.5, 1, 0.5));
        } else{
            expectedBalance.setTextFill(Color.color(1, 0, 0));
        }
    }

    private void populaUltimaMov(Movimentacao mov) {
        String textoMov = "Data: " + mov.getData()
                + " Tipo: " + mov.getTipoMovimentacao().getDescricao()
                + "Categoria: " + mov.getCategoria().getDescricao()
                +". Valor: R$ "+mov.getValor();
        fieldMov.setText(textoMov);
    }
    
    private String getTipoMovimentacaoName(int tipo){
        return tipo == 1 ? "Receita" : "Despesa";
    }

    private String getCategoriaName(int id){
        return categorias.stream()
                .filter(c -> c.getId() == id).findFirst().get().getDescricao();
    }
}
