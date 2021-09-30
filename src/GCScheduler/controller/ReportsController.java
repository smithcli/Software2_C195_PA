package GCScheduler.controller;

import GCScheduler.model.Appointment;
import GCScheduler.model.Country;
import GCScheduler.model.FirstLevelDiv;
import GCScheduler.model.Scheduler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Reports view nested in Scheduler's TabPane.
 */
public class ReportsController {
    @FXML private Label errorLabel;
    @FXML private AnchorPane leftAnchorPane;
    @FXML private AnchorPane rightAnchorPane;
    private static PieChart locationPieChart;
    private static BarChart<Number,String> apptByMonthBC;

    /**
     * Called when class is loaded. Calls methods placeLeftChart and placeRightChart.
     */
    public void initialize() {
        placeLeftChart(apptByMonthBC);
        placeRightChart(locationPieChart);
    }

    /**
     * Creates Horizontal Bar Chart that displays number of Appointments by Type and Month.
     * Needed Help on this class to change the xAxis numbers to display only integers not doubles.
     */
    public static void setupApptBarChart() {
        NumberAxis xAxis = new NumberAxis();
        CategoryAxis yAxis = new CategoryAxis();
        apptByMonthBC = new BarChart<>(xAxis, yAxis);
        apptByMonthBC.setTitle("Customer Appointments by Month");
        xAxis.setLabel("# of Appointments");
        StringConverter<Number> converter = new StringConverter<Number>() {
            //Needed help with the override link: https://stackoverflow.com/questions/23841268/how-to-make-javafx-chart-numberaxis-only-show-integer-value-not-double
            @Override
            public String toString(Number number) {
                if (number.intValue()!=number.doubleValue()) {
                    return "";
                }
                return ""+(number.intValue());
            }

            @Override
            public Number fromString(String s) {
                Number number = Double.parseDouble(s);
                return number.intValue();
            }
        };
        xAxis.setTickLabelFormatter(converter);
        xAxis.setMinorTickVisible(false);
        yAxis.setLabel("Month");
        List<String> types = new ArrayList<>();
        for (Appointment appt : Scheduler.getAllAppointments()) {
            String apptType = appt.getApptType();
            if (!types.contains(apptType)) {
                types.add(apptType);
            }
        }
        for (String type : types){
            XYChart.Series series = new XYChart.Series();
            series.setName(type);
            for (Month month : Month.values()) {
                int count = 0;
                for (Appointment appt : Scheduler.getAllAppointments()) {
                    if (appt.getStart().getMonth().equals(month) && appt.getApptType().equals(type)) {
                        count++;
                    }
                }
                if (count > 0) {
                    //System.out.println(type + " " + count + " " + month);
                    series.getData().add(new XYChart.Data<>(count,String.valueOf(month)));
                }
            }
            apptByMonthBC.getData().add(series);
        }
    }

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

    /**
     * Places a chart in the center of the AnchorPane on the SplitPane's right side.
     * @param chart chart to be placed.
     */
    protected void placeRightChart(Chart chart) {
        rightAnchorPane.getChildren().add(chart);
        AnchorPane.setTopAnchor(chart,50.0);
        AnchorPane.setBottomAnchor(chart,0.0);
        AnchorPane.setLeftAnchor(chart,0.0);
        AnchorPane.setRightAnchor(chart,0.0);
    }

    /**
     * Places a chart in the center of the AnchorPane on the SplitPane's left side.
     * @param chart chart to be placed.
     */
    protected void placeLeftChart(Chart chart) {
        leftAnchorPane.getChildren().add(chart);
        AnchorPane.setTopAnchor(chart,50.0);
        AnchorPane.setBottomAnchor(chart,0.0);
        AnchorPane.setLeftAnchor(chart,0.0);
        AnchorPane.setRightAnchor(chart,0.0);
    }
}
