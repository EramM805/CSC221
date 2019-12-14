package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class DirectoryController {
	@FXML private Label lblFilename;
	@FXML private Button btnLoad;
	@FXML private Button AddButton;
	@FXML private Button RemoveButton;
	@FXML private Button btnNavNext;
	@FXML private Button btnSerialize;
	@FXML private Button PrevButton;
	@FXML private TextField NameTextField;
	@FXML private TextField CompanyTextField;
	@FXML private TextField ExtensionTextField;
	@FXML private Label lblCurrRecord;
	private File selectedFile;

	private int index = 0;
	private ObservableList<Employee> lst = FXCollections.observableArrayList();
    private List<Employee> input = new ArrayList<>(); 
	private EmployeeList list = new EmployeeList();
	IntegerBinding listSize = Bindings.size(lst);
	BooleanBinding listPopulated = listSize.greaterThan(0);
		
	
	public String getButtonId(ActionEvent event) {
		return ((Control)event.getSource()).getId();
	}
	@FXML
	public void serialize() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeList.class);
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    list.setLst(lst);
		    jaxbMarshaller.marshal(list, selectedFile);
		}
		catch(Exception e){
			list.setLst(input);
			System.out.println(e);
		}
		
	}
	@FXML
	public void unMarshaling(File file){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeList.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			EmployeeList emps = (EmployeeList) jaxbUnmarshaller.unmarshal(file);
			input = emps.getLst();
			for(Employee e: input) {
				addToList(e.getName(), e.getDepartment(), e.getExtension());
				RemoveButton.disableProperty().bind(Bindings.size(lst).greaterThan(0).not());
				index = index+1;
				setTextOfLabelCurrentRecord();
				checkNextAndPrev();
				setTextField();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	    
	}


	@FXML
    public void loadButtonClicked (ActionEvent event){
	  FileChooser fileChooser = new FileChooser();
	  fileChooser.setTitle("Open Resource File");
	  final Node source = (Node) event.getSource();
	  final Stage stage = (Stage) source.getScene().getWindow();
	  fileChooser.showOpenDialog(stage);
	  selectedFile = fileChooser.showOpenDialog(null);
	  if (selectedFile != null) {
		  lblFilename.setText("File: " + selectedFile.getName());
		  btnSerialize.setDisable(false);
		  AddButton.setDisable(false);
		  NameTextField.setDisable(false);
		  CompanyTextField.setDisable(false);
		  ExtensionTextField.setDisable(false);
		  unMarshaling(selectedFile);
		}
		else {
			lblFilename.setText("File: not set");
			 AddButton.setDisable(true);
		}
    }
	@FXML
    private void exitButtonClicked(ActionEvent event){
		updateList();
		Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Confirm Exit");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
        else {
        	serialize();
        	Stage stage = (Stage) btnLoad.getScene().getWindow();
    	    stage.close();
        }
		
	};
	public boolean validateName(String name) {
		return name.matches("[A-Z][A-Za-z]{2,}|[A-Z][A-Za-z]{2,}\\s[A-Z][A-Za-z]{2,}");
	}
	public boolean validateCompany(String company) {
		return company.matches("[A-Z]\\w*\\s[A-Z]\\w*|[A-Z]\\w*");
	}
	public boolean validateExtension(String extension) {
			return extension.matches("\\d{1,3}[-]\\d{1,2}");
	}
	public void checkNextAndPrev() {
		if(index == lst.size()) {
			btnNavNext.setDisable(true);
		}
		else {
			btnNavNext.setDisable(false);
		}
		if(index > 1 && index <= lst.size()) {
			PrevButton.setDisable(false);
		}
		else {
			PrevButton.setDisable(true);
		}
	}
	public void alertFunction(String name, String company, String extension) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid value");
		if(!validateName(name)) {
			alert.setHeaderText("Invalid Name.\nNames can be one or two words.\n1.Each word must start with an uppercase letter followed by at least two characters. \n2.Numbers are not allowed");
		}
		else {
			if(!validateCompany(company)) {
				alert.setHeaderText("Invalid Department. Department name\nDepartment can be one or two words.\n1.Each word must start with an uppercase letter.A word can be a single uppercase letter.\n2.Numbers are allowed");
			}
			else {
				alert.setHeaderText("Invalid Extension.\nExtension can only start with 1,2, or 2 numbers followed by a - followed by 1 or 2 numbers");
			}
		}
		alert.showAndWait();
	}
	public void setTextOfLabelCurrentRecord() {
		lblCurrRecord.setText(index + " of " + lst.size());
	}
	public boolean addToList(String name, String company, String extension) {
		if(validateName(name) && validateCompany(company) && validateExtension(extension)) {
			Employee employee = new Employee(name, company, extension);
			lst.add(employee);
			return true;

		}
		else {
			alertFunction(name,company,extension);
		}
		return false;
	}
	@FXML
    public void addButtonClicked (ActionEvent event) {
		String name = NameTextField.getText();
		String company =  CompanyTextField.getText();
		String extension =  ExtensionTextField.getText();
		if(addToList(name,company,extension)) {
			RemoveButton.disableProperty().bind(Bindings.size(lst).greaterThan(0).not());
			index = index+1;
			setTextOfLabelCurrentRecord();
			checkNextAndPrev();
		}
    }
	
	public void updateList() {
		String name = NameTextField.getText();
		String company = CompanyTextField.getText();
		String extension =  ExtensionTextField.getText();
		if(index != 0 && validateName(name) && validateCompany(company) && validateExtension(extension)) {
			lst.get(index-1).setName(NameTextField.getText());
			lst.get(index-1).setDepartment(CompanyTextField.getText());
			lst.get(index-1).setExtension(ExtensionTextField.getText());
		}
		else {
			alertFunction(name, company, extension);
		}
		
	}
	public void setTextField() {
		if(index > lst.size()) {
			index = lst.size();
		}
		NameTextField.setText(lst.get(index-1).getName());
		CompanyTextField.setText(lst.get(index-1).getDepartment());
		ExtensionTextField.setText(lst.get(index-1).getExtension());
	}
	@FXML
    public void prevButtonClicked (ActionEvent event){
		updateList();
		index = index-1;
		checkNextAndPrev();
		setTextField();
		setTextOfLabelCurrentRecord();
    }
	@FXML
    public void removeButtonClicked (ActionEvent event){
		if(!lst.isEmpty() && index <= lst.size()) {
			lst.remove(index-1);
			if(lst.size() > 0) {
				setTextField();
			}
		}
		if(lst.isEmpty()) {	
			index = 0;
		}
		checkNextAndPrev();
 		setTextOfLabelCurrentRecord();
    }
	@FXML
    public void nextButtonClicked (ActionEvent event){
		updateList();
		index = index+1;
		checkNextAndPrev();
		setTextField();
		setTextOfLabelCurrentRecord();
    }

}
