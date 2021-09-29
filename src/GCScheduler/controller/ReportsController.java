package GCScheduler.controller;

import GCScheduler.model.Country;
import GCScheduler.model.FirstLevelDiv;
import GCScheduler.model.Scheduler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.Month;

public class ReportsController {
    @FXML private Label errorLabel;
    @FXML private BarChart<Month, Integer> apptBarChart;
    @FXML private AnchorPane rightAnchorPane;
    private static PieChart locationPieChart;

    public void initialize() {
        setupLocationPieChart();
        placePieChart();
    }

    //TODO finish bar chart.
//    public static void setupApptBarChart() {
//        CategoryAxis xAxis = new CategoryAxis();
//        CategoryAxis yAxis = new CategoryAxis();
//        xAxis.setLabel("Appt Type by Month");
//        yAxis.setLabel("# of Appts");
//        for (Appointment appt : Scheduler.getAllAppointments()) {
//            String type = "";
//            if (!appt.getApptType().equals(type)) {
//                type = appt.getApptType();
//            }
//        }
//    }

    /**
     * Custom report that displays in a PieChart customers by Country.
     */
    public static void setupLocationPieChart() {
        ObservableList<PieChart.Data> locations = FXCollections.observableArrayList();
        for (Country country : Scheduler.getAllCountries()) {
            double customerCount = 0;
            for (FirstLevelDiv div : country.getFirstLevelDivs()) {
                customerCount += div.getCustomerList().size();
            }
            String countryName = country.getCountryName()+" - "+ (int)customerCount;
            locations.add(new PieChart.Data(countryName,customerCount));
        }
        locationPieChart = new PieChart(locations);
        locationPieChart.setTitle("Customers by Country");
    }

    protected void placePieChart() {
        rightAnchorPane.getChildren().add(locationPieChart);
        AnchorPane.setTopAnchor(locationPieChart,133.0);
        AnchorPane.setBottomAnchor(locationPieChart,133.0);
        AnchorPane.setLeftAnchor(locationPieChart,4.0);
        AnchorPane.setRightAnchor(locationPieChart,4.0);
    }
}
