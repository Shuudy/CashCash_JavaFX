package com.cashcash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class ClientController implements Initializable {    

    @FXML
    private TableColumn<Client, String> col_socialreason;

    @FXML
    private TableColumn<Client, String> col_email;

    @FXML
    private TableColumn<Client, Integer> col_id;

    @FXML
    private TableView<Client> table_clients;

    @FXML
    private Label label_title;
    
    ObservableList<Client> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

        col_id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		col_socialreason.setCellValueFactory(new PropertyValueFactory<Client, String>("raisonSociale"));
        col_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        
		list = new DatabaseConnection().getDataClients();
		table_clients.setItems(list);

        addXMLButtonToTable();
        addContratButtonToTable();
	}

    private void addXMLButtonToTable() {
        TableColumn<Client, Void> colBtn = new TableColumn("Actions");

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                    private final Button btn = new Button("XML");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Client client = getTableView().getItems().get(getIndex());

                            try {
                                DatabaseConnection dc = new DatabaseConnection();
                                GestionMateriels gm = new GestionMateriels(dc);
                                gm.xmlClient(client);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                                                   
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

    private void addContratButtonToTable() {
        TableColumn<Client, Void> colBtn = new TableColumn("Actions");

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                    private final Button btn = new Button("Contrat");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Client client = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("clients_materiels.fxml"));
                                Parent root = loader.load();                                

                                // Get the controller instance
                                MaterielController materielController = loader.getController();

                                // Pass the selected client to the controller
                                materielController.setSelectedClient(client);

                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.setTitle("Matériels - " + client.getRaisonSociale());
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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