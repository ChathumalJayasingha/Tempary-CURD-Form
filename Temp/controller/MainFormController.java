package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSAlary;
    public TableView <CustomerDTO>tblCustomer;
    public TableColumn<CustomerDTO,String> clmId;
    public TableColumn<CustomerDTO,String> clmName;
    public TableColumn <CustomerDTO,String>clmAddress;
    public TableColumn <CustomerDTO,Double>clmSalary;

    CustomerBO customerBO= (CustomerBO) BOFactory.getInstance().getEnum(BOFactory.SetEnum.CUSTOMER);

    public void btnAdd(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSAlary.getText());
        CustomerDTO customerDTO=new CustomerDTO(id,name,address,salary);

        try {
            boolean b = customerBO.addCustomer(customerDTO);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Added").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Added Fail").show();
            }
            addCustomersForTheTable();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    public void btnDelete(ActionEvent actionEvent) {

        try {
            boolean b = customerBO.deleteCustomer(txtId.getId());
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Sucess").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Delete Fail").show();
            }
            addCustomersForTheTable();
        } catch (Exception e) {
            System.out.print(e.getMessage()+"btnDelete");
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {


    }

    public void btnSearch(ActionEvent actionEvent) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCustomersForTheTable();
    }

    private void addCustomersForTheTable() {
        try {
            ArrayList<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            ObservableList<CustomerDTO>all= FXCollections.observableArrayList();
            for (CustomerDTO customerDTO:allCustomer){
                CustomerDTO dto=new CustomerDTO(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary());
                all.addAll(dto);
            }tblCustomer.setItems(all);

            clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
            clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            clmSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));





        } catch (Exception e) {
            System.out.print(e.getMessage());
        }


    }
}
