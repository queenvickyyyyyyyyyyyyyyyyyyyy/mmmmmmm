package aplicacoes;
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox xmlns="http://javafx.com/javafx"
      xmlns:fx="http://javafx.com/fxml"
      fx:controller="application.MainController"
      spacing="10" alignment="CENTER"
      prefWidth="300" prefHeight="200">

    <Label text="DataShield" fx:id="labelMensagem"/>

    <Button text="Clique Aqui" onAction="#botaoClicado"/>

</VBox>
