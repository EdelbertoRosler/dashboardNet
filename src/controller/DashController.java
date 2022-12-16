package Controller;


import com.sun.tools.javac.tree.JCTree;
import dao.CategoriaDao;
import dao.MovimentacaoDao;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Categoria;
import model.Movimentacao;

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
    
    private CategoriaDao categoriaDao;
    
    private MovimentacaoDao movimentacaoDao;
    
    private List<Categoria> categorias;
    
    private ObservableList<Movimentacao> movimentacoes = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        categoriaDao = new CategoriaDao();
        movimentacaoDao = new MovimentacaoDao();
        
        categorias = categoriaDao.getAll();
        categorias.forEach(c -> category.getItems().add(c.getDescricao()));
        
        List<Movimentacao> movSalvas = movimentacaoDao.getAll();
        popularColuna(movSalvas);
        
        calcularSaldo(movSalvas);
    }

    @FXML
    void radioSalary(ActionEvent event) {

    }

    @FXML
    void radioExpense(ActionEvent event) {

    }

    @FXML
    void ff1313(ActionEvent event) {

    }

    @FXML
    void handleValue(ActionEvent event) {

    }

    @FXML
    void handleSend(ActionEvent event) {
        salvarMovimentacao();
    }

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleDescription(ActionEvent event) {

    }

    @FXML
    void handleCategory(ActionEvent event) {

    }
    
    public void setStage(Stage stageDash) {
        this.stage = stageDash;
    }

    private void calcularSaldo(List<Movimentacao> movimentacoes) {
        Double saldo = 0.0;
        for(Movimentacao mov : movimentacoes){
            if(mov.getTipo() == 1){
               saldo = saldo + mov.getValor();
           } else {
               saldo = saldo - mov.getValor();
           }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        currentBalance.setText("R$ " + df.format(saldo));
        if (saldo >= 0.0){
            currentBalance.setTextFill(Color.color(0.5, 1, 0.5));
        } else{
            currentBalance.setTextFill(Color.color(1, 0, 0));
        }
    }

    private void popularColuna(List<Movimentacao> movSalvas) {
        
        movimentacoes.addAll(movSalvas);
        movements.getItems().addAll(movimentacoes);
        
        categoriaColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("categoria"));
        tipoColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("tipo"));
        dataColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, LocalDate>("data"));
        valorColum.setCellValueFactory(new PropertyValueFactory<Movimentacao, Double>("valor"));
    }

    private void salvarMovimentacao() {
        
        if(criacaoValida()){
            Movimentacao novaMov = new Movimentacao();
            novaMov.setTipo(getTipo());
            novaMov.setCategoria(getCategoria());
            novaMov.setData(datePicker.getValue());
            novaMov.setValor(Double.parseDouble(fieldValue.getText()));
            novaMov.setDescricao(fieldDescription.getText());
            novaMov.setPago("N");
            
            movimentacaoDao.save(novaMov);
            
            List<Movimentacao> movSalvas = movimentacaoDao.getAll();
            popularColuna(movSalvas);
        
            calcularSaldo(movSalvas);
        }
    }

    private boolean criacaoValida() {
       return category.getValue() != "Categoria"
               && !"".equals(fieldValue.getText())
               && !"".equals(fieldDescription.getText())
               && datePicker.getValue() != null;
    }

    private int getTipo() {
        if (salary.isSelected()){
            return 1;
        } else{
            return 2;
        }
    }

    private int getCategoria() {
        Optional<Categoria> categoria = categorias.stream()
                .filter(c -> c.getDescricao() == category.getValue()).findFirst();
        
        return categoria.get().getId();
    }

}
