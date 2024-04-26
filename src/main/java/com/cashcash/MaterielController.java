package com.cashcash;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MaterielController implements Initializable {

    @FXML
    private TableColumn<Materiel, Integer> col_id;

    @FXML
    private TableColumn<Materiel, String> col_label;

    @FXML
    private TableView<Materiel> table_materiels;

    private Client selectedClient;
    
    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        
        col_id.setCellValueFactory(new PropertyValueFactory<Materiel, Integer>("numSerie"));
        //col_label.setCellValueFactory(new PropertyValueFactory<Materiel, String>("emplacement"));

        loadMaterielData();
    }

    private void loadMaterielData() {
        if (selectedClient != null) {
            System.out.println("good");
            ObservableList<Materiel> materielList = new DatabaseConnection().getMaterielForClient(selectedClient.getId());
            table_materiels.setItems(materielList);
        } else {
            // Handle the case where selectedClient is null (optional)
            System.out.println("selectedClient is null");
        }
    }
}
