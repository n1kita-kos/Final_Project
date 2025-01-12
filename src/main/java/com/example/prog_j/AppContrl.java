package com.example.prog_j;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AppContrl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField InputArchiveNameTextField;

    @FXML
    private CheckBox InputArchivedCheckBox;

    @FXML
    private CheckBox InputEncryptedCheckBox;

    @FXML
    private TextField InputFileNameTextField;

    @FXML
    private ChoiceBox<String> InputFileTypeChoiceBox;

    @FXML
    private TextField InputKey;

    @FXML
    private TextField OutputArchiveNameTextField;

    @FXML
    private CheckBox OutputArchivedCheckBox;

    @FXML
    private CheckBox OutputEncryptedCheckBox;

    @FXML
    private TextField OutputFileNameTextField;

    @FXML
    private ChoiceBox<String> OutputFileTypeChoiceBox;

    @FXML
    private TextField OutputKey;

    @FXML
    private RadioButton ParseByLibRadioButton;

    @FXML
    private RadioButton ParseByRegularRadioButton;

    @FXML
    private RadioButton ParseByStackRadioButton;

    @FXML
    private Button ParseFileButton;

    @FXML
    private ToggleGroup ParserSettingsRadioGroup;

    @FXML
    void InputFileEncryptedAction(ActionEvent event) {
        InputKey.setVisible(InputEncryptedCheckBox.isSelected());
    }

    @FXML
    void InputFileArchivedAction(ActionEvent event) {
        InputArchiveNameTextField.setVisible(InputArchivedCheckBox.isSelected());
    }

    @FXML
    void OutputFileEncryptedAction(ActionEvent event) {
        OutputKey.setVisible(OutputEncryptedCheckBox.isSelected());
    }

    @FXML
    void OutputFileArchivedAction(ActionEvent event) {
        OutputArchiveNameTextField.setVisible(OutputArchivedCheckBox.isSelected());
    }

    private FileSettings InputFileSettings;

    private FileSettings OutputFileSettings;

    @FXML
    void ParseFile(ActionEvent event) throws Exception {
        InputFileSettings.check_file_zip = InputArchivedCheckBox.isSelected();
        InputFileSettings.check_file_encrypted = InputEncryptedCheckBox.isSelected();
        InputFileSettings.file_type = InputFileTypeChoiceBox.getValue();
        InputFileSettings.file_name = InputFileNameTextField.getText() + InputFileSettings.file_type;
        InputFileSettings.archive_name = InputArchiveNameTextField.getText();
        InputFileSettings.key = InputKey.getText();

        OutputFileSettings.check_file_zip = OutputArchivedCheckBox.isSelected();
        OutputFileSettings.check_file_encrypted = OutputEncryptedCheckBox.isSelected();
        OutputFileSettings.file_type = OutputFileTypeChoiceBox.getValue();
        OutputFileSettings.file_name = OutputFileNameTextField.getText() + OutputFileSettings.file_type;
        OutputFileSettings.archive_name = OutputArchiveNameTextField.getText();
        OutputFileSettings.key = OutputKey.getText();

        try {
            filePRS.ReadInputFile(InputFileSettings);
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Not Found");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }

        String ParseType = "";
        if (ParseByLibRadioButton.isSelected()) {
            ParseType = ParseByLibRadioButton.getText();
        } else if (ParseByStackRadioButton.isSelected()) {
            ParseType = ParseByStackRadioButton.getText();
        } else if (ParseByRegularRadioButton.isSelected()) {
            ParseType = ParseByRegularRadioButton.getText();
        }

        try {
            filePRS.ParseMathematicalExpressions(InputFileSettings, OutputFileSettings, ParseType);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Math Parse Exception");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }

        try {
            filePRS.WriteOutputFile(OutputFileSettings);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }

    @FXML
    void initialize() {
        String[] types = {".txt", ".json", ".xml"};
        InputFileTypeChoiceBox.getItems().addAll(types);
        InputFileTypeChoiceBox.setValue(".txt");

        OutputFileTypeChoiceBox.getItems().addAll(types);
        OutputFileTypeChoiceBox.setValue(".txt");

        InputArchiveNameTextField.setVisible(false);
        InputKey.setVisible(false);
        OutputArchiveNameTextField.setVisible(false);
        OutputKey.setVisible(false);

        InputFileSettings = new FileSettings();
        OutputFileSettings = new FileSettings();
    }

}