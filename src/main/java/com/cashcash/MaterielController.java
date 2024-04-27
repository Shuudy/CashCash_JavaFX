package com.cashcash;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MaterielController implements Initializable {

    @FXML
    private TableColumn<Materiel, Integer> col_id;

    @FXML
    private TableColumn<Materiel, String> col_label;

    @FXML
    private TableColumn<Materiel, String> col_emplacement;

    @FXML
    private TableColumn<Materiel, LocalDate> col_installationdate;

    @FXML
    private Label label_title;

    @FXML
    private Button button_create;

    @FXML
    private TableView<Materiel> table_materiels;

    private Client selectedClient;
    
    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;

        loadMaterielData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        
        col_id.setCellValueFactory(new PropertyValueFactory<Materiel, Integer>("numSerie"));
        col_label.setCellValueFactory(new PropertyValueFactory<Materiel, String>("libelleTypeMateriel"));
        col_emplacement.setCellValueFactory(new PropertyValueFactory<Materiel, String>("emplacement"));
        col_installationdate.setCellValueFactory(new PropertyValueFactory<Materiel, LocalDate>("dateInstallation"));

        table_materiels.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void handleButtonClick() {
        ObservableList<Materiel> selectedMateriels = table_materiels.getSelectionModel().getSelectedItems();

        // On vérifie si un ou plusieurs matériels est sélectionné ou non.
        if (!selectedMateriels.isEmpty()) {           
            for (Materiel materiel : selectedMateriels) {              
                System.out.println("Numéro de série : " + materiel.getNumSerie());
            }
    
            Alert a = new Alert(AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Contrat de maintenance créé avec succès pour les matériels sélectionnés !");
            a.show();

            // On actualise les matériels
            loadMaterielData();
        } else {
            Alert a = new Alert(AlertType.WARNING);
            a.setHeaderText(null);
            a.setContentText("Aucun matériel sélectionné !");
            a.show();
        }
    }

    private void loadMaterielData() {
        if (selectedClient != null) {
            ObservableList<Materiel> materielList = new DatabaseConnection().getMaterielForClient(selectedClient.getId(), true);
            table_materiels.setItems(materielList);

            label_title.setText("Matériels du client " + selectedClient.getRaisonSociale());
            button_create.setText(selectedClient.aUnContratMaintenance() ? "Ajouter au contrat" : "Créer un contrat");
        } else {
            System.out.println("selectedClient is null");
        }
    }
}
