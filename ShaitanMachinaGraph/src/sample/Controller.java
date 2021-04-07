package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private LineChart<Integer, Integer> lineChart;
    @FXML
    private ComboBox<String> cmbY;
    @FXML
    private ComboBox<String> cmbY1;
    @FXML
    private TextField txtInput;
    @FXML
    private TextField txtOutput;

    static final String NRZ = "NRZ";
    static final String RZ = "RZ";
    static final String MANCH = "Манчестерское";
    static final String FIZ = "Физическое";
    static final String LOG = "Логическое";
    static final String SCR = "Скрэмблирование";

    public void updateChart() {
        String output;
        StringBuilder name = new StringBuilder();
        XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
        int y;
        int i = 0;
        switch (cmbY1.getSelectionModel().getSelectedItem()) {
            case FIZ -> {
                output = Converter.stringToBinary(txtInput.getText());
                txtOutput.setText(Converter.stringSplit(output, 8));
                name.append("Физическое кодирование");
            }
            case LOG -> {
                output = Converter.quadBinaryToPent(Converter.stringToBinary(txtInput.getText()));
                txtOutput.setText(Converter.stringSplit(output, 10));
                name.append("Логическое кодирование");
            }
            case SCR -> {
                output = Converter.binaryScrambler(Converter.quadBinaryToPent(Converter.stringToBinary(txtInput.getText())));
                txtOutput.setText(Converter.stringSplit(output, 10));
                name.append("Кодирование через скрэмблирование");
            }
            default -> output = "0";
        }
        switch (cmbY.getSelectionModel().getSelectedItem()) {
            case NRZ -> {
                for (int x = 0; x < output.length(); x++) {
                    y = output.charAt(x) == '1' ? 1 : 0;
                    series.getData().add(new XYChart.Data<>(i, y));
                    i += 1;
                    series.getData().add(new XYChart.Data<>(i, y));
                }
                name.append(" (NRZ)");
            }
            case RZ -> {
                for (int x = 0; x < output.length(); x++) {
                    y = output.charAt(x) == '1' ? 1 : -1;
                    series.getData().add(new XYChart.Data<>(i, y));
                    i += 1;
                    series.getData().add(new XYChart.Data<>(i, y));
                    series.getData().add(new XYChart.Data<>(i, 0));
                    i += 1;
                    series.getData().add(new XYChart.Data<>(i, 0));
                }
                name.append(" (RZ)");
            }
            case MANCH -> {
                for (int x = 0; x < output.length(); x++) {
                    y = output.charAt(x) == '1' ? -1 : 1;
                    series.getData().add(new XYChart.Data<>(i, y));
                    i += 1;
                    series.getData().add(new XYChart.Data<>(i, y));
                    series.getData().add(new XYChart.Data<>(i, -1 * y));
                    i += 1;
                    series.getData().add(new XYChart.Data<>(i, -1 * y));
                }
                name.append(" (манчестерское)");
            }
            default -> output = "0";
        }
        series.setName(name.toString());
        lineChart.getData().setAll(series);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lineChart.setCreateSymbols(false);
        ObservableList<String> items = FXCollections.observableArrayList(NRZ, RZ, MANCH);
        cmbY.setItems(items);
        ObservableList<String> bins = FXCollections.observableArrayList(FIZ, LOG, SCR);
        cmbY1.setItems(bins);
        cmbY.getSelectionModel().selectFirst();
        cmbY1.getSelectionModel().selectFirst();
    }
}
