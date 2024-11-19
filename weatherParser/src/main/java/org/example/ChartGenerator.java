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
import java.util.ArrayList;
import java.util.Date;


public class ChartGenerator extends Application{

    XYChart.Series realTempSerie = new XYChart.Series();
    XYChart.Series explTempSerie = new XYChart.Series();
    @Override public void start(Stage stage) {
        stage.setTitle("Chart");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("date");
        yAxis.setLabel("Temperature");
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Expected and real temperature Monitoring");


        Scene scene  = new Scene(lineChart,950,500);
        for(XYChart.Series serie: series ){
            serie.getData().add(serie);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowTheChart(DataBase db,String date){
        PreparedStatement preparedStatement = db.QueryTemperature();
        ResultSet resultSet;
        try {
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                java.util.Date resDate = resultSet.getDate("date");
                int maxTemp = resultSet.getInt("max_expected_temp");
                int minTemp = resultSet.getInt("min_expected_temp");
                Float avgTemp = (minTemp + maxTemp)/2.F;

            }
        }catch (SQLException exception){
            System.out.println(exception);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
