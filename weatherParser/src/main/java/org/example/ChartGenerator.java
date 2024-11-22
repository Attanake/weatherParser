package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ChartGenerator extends Application{

    static XYChart.Series realTempSerie = new XYChart.Series();
    static XYChart.Series explTempSerie = new XYChart.Series();
    @Override public void start(Stage stage) {
        stage.setTitle("Chart");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Temperature");
        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Expected and real temperature Monitoring");

        Scene scene  = new Scene(lineChart,950,500);
        lineChart.getData().add(realTempSerie);
        lineChart.getData().add(explTempSerie);
        realTempSerie.setName("Real temperature");
        explTempSerie.setName("Expected temperature");
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowTheChart(DataBase db,java.time.LocalDate date){
        PreparedStatement preparedStatement = db.QueryShowTemperature();
        ResultSet resultSet;
        try {
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                java.util.Date resDate = resultSet.getDate("date");
                int maxTemp = resultSet.getInt("max_temp");
                int minTemp = resultSet.getInt("min_temp");
                int maxExpTemp = resultSet.getInt("max_expected_temp");
                int minExpTemp = resultSet.getInt("min_expected_temp");
                int avgTemp = (minTemp + maxTemp)/2;
                int avgExpTemp = (maxExpTemp+minExpTemp)/2;
                int intDate = Integer.parseInt(resDate.toString().substring(8,10));
                realTempSerie.getData().add(new XYChart.Data(intDate,avgTemp));
                explTempSerie.getData().add(new XYChart.Data(intDate,avgExpTemp));
            }
        }catch (SQLException exception){
            System.out.println(exception);
        }
    }
    public static void Main(){
        launch();
    }
}
