package aplicacoes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label labelMensagem;

    @FXML
    private void botaoClicado() {
        labelMensagem.setText("o botão foi clicado");
    }
}
