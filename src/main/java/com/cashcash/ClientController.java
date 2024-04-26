package com.cashcash;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClientController implements Initializable {    

    @FXML
    private TableColumn<Client, String> col_socialreason;

    @FXML
    private TableColumn<Client, String> col_email;

    @FXML
    private TableColumn<Client, Integer> col_id;

    @FXML
    private TableView<Client> table_clients;
    
    ObservableList<Client> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

        col_id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		col_socialreason.setCellValueFactory(new PropertyValueFactory<Client, String>("raisonSociale"));
        col_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        
		list = new DatabaseConnection().getDataClients();
		table_clients.setItems(list);

        addXMLToTable();
	}

    private void addXMLToTable() {
        TableColumn<Client, Void> colBtn = new TableColumn("Actions");

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                    private final Button btn = new Button("XML");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Client client = getTableView().getItems().get(getIndex());

                            System.out.println(client.getContratMaintenance());
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setHeaderText(null);
                            a.setContentText("Fichier XML pour le client " + client.getRaisonSociale() + " créé.");
                            a.show();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        table_clients.getColumns().add(colBtn);
    }
}