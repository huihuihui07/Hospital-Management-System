package controllers;

import application.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.DaoModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class DoctorViewRegistrationController implements Initializable {
	// The title of current page
	static final String TITLE = "Doctor View Registration";
	static final String FXM_URL = "/views/DoctorViewRegistrationView.fxml";
	static final String CSS_URL = "../application/application.css";
	static Parent root = null;
	static Scene scene = null;
	// The unique instance of the current controller to implement page switching
	public static DoctorViewRegistrationController controller = null;
	public static final String Cancelled = "Cancelled";
	public static final String Registered = "Registered";
	
	@FXML
	private TableView<Registration> registrationTableView;
	
	@FXML
	private TableColumn<Registration, ?> registrationColIndex;
	
	@FXML
	private TableColumn<Registration, ?> registrationColId;
	
	@FXML
	private TableColumn<Registration, ?> registrationColDoctorName;
	
	@FXML
	private TableColumn<Registration, ?> registrationColSex;
	
	@FXML
	private TableColumn<Registration, ?> registrationColAge;
	
	@FXML
	private TableColumn<Registration, ?> registrationColDoctor;
	
	@FXML
	private TableColumn<Registration, ?> registrationColDepartment;
	
	@FXML
	private TableColumn<Registration, ?> registrationColStatus;
	
	@FXML
	private TableColumn<Registration, ?> registrationColReservationDate;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initRegistrationTable();
		//get patient information
		PatientViewRegistrationController patient= new PatientViewRegistrationController();
		patient.getRegistrationInfo();
	}
	
	public boolean initScene() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FXM_URL));
		try {
			root = loader.load();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return true;
	}

	public boolean showScene() {
		if (Main.stage == null || root == null) {
			return false;
		}
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource(CSS_URL).toExternalForm());
		Main.stage.setScene(scene);
		Main.stage.setTitle(TITLE);
		Main.stage.show();
		return true;
	}
	
	public void initRegistrationTable(){
		registrationColIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
		registrationColId.setCellValueFactory(new PropertyValueFactory<>("id"));
		registrationColDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
		registrationColSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
		registrationColAge.setCellValueFactory(new PropertyValueFactory<>("age"));
		registrationColDoctor.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
		registrationColDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
		registrationColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		registrationColReservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
	}
	
//	public void getRegistrationInfo(){
//		ArrayList<PatientViewRegistrationController.Registration> ls = DaoModel.dao.getCurrentPatientRegistrationList();
//		ObservableList<Registration> data = FXCollections.observableArrayList(
//				//new Registration(1, 4, "Doctor1", "Male", 27, "Doctor Lee", "DP1", "Registered", date.toString()),
//				//new Registration(2, 5, "Doctor2", "Male", 32, "Doctor Lee", "DP1", "Registered", date.toString())
//				);
//		for(PatientViewRegistrationController.Registration i:ls) {
//			data.add(i);
//		}
//		this.registrationTableView.setItems(data);
//	}
	
//	public void cancelRegistration() {
//		Registration item = registrationTableView.getSelectionModel().getSelectedItem();
//		if(item==null) {
//			return;
//		}
//		if(item.status.get().equals(Registered)) {
//			//change status
//			boolean flag = DaoModel.dao.cancelPatientRegistration(item.id.get());
//			if(flag) {
//				getRegistrationInfo();
//			}
//		} else {
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Alert");
//			alert.setHeaderText(null);
//			alert.setContentText("This registration can't be cancelled");
//			alert.showAndWait();
//		}
//	}
	
	public void backDoctorPage() {
		DoctorPageController.controller.initScene();
		DoctorPageController.controller.showScene();
	}
	
	public static class Registration{
		private final SimpleIntegerProperty index;
		private final SimpleIntegerProperty id;
		private final SimpleStringProperty patientName;
		private final SimpleStringProperty sex;
		private final SimpleIntegerProperty age;
		private final SimpleStringProperty doctorName;
		private final SimpleStringProperty department;
		private final SimpleStringProperty status;
		private final SimpleStringProperty reservationDate;
		
		public Registration(int index,int id,String patientName,String sex,int age,String doctorName,String department,String status,String reservationDate) {
			this.index = new SimpleIntegerProperty(index);
			this.id = new SimpleIntegerProperty(id);
			this.patientName = new SimpleStringProperty(patientName);
			this.sex = new SimpleStringProperty(sex);
			this.age = new SimpleIntegerProperty(age);
			this.doctorName = new SimpleStringProperty(doctorName);
			this.department = new SimpleStringProperty(department);
			this.status = new SimpleStringProperty(status);
			this.reservationDate = new SimpleStringProperty(reservationDate);
		}

		public int getIndex() {
			return index.get();
		}

		public int getId() {
			return id.get();
		}

		public String getPatientName() {
			return patientName.get();
		}

		public String getSex() {
			return sex.get();
		}

		public int getAge() {
			return age.get();
		}

		public String getDoctorName() {
			return doctorName.get();
		}

		public String getDepartment() {
			return department.get();
		}

		public String getStatus() {
			return status.get();
		}

		public String getReservationDate() {
			return reservationDate.get();
		}
		
	}
}
