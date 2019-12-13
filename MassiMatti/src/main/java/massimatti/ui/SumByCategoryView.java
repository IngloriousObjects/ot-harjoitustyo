package massimatti.ui;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import massimatti.domain.Entry;
import massimatti.domain.Category;
import massimatti.domain.UserController;
import massimatti.domain.EntryController;
import massimatti.domain.CategoryController;

public class SumByCategoryView {

    private UserController userController;
    private EntryController entryController;
    private CategoryController categoryController;

    public SumByCategoryView(UserController userController, EntryController entryController, CategoryController categoryController) {

        this.userController = userController;
        this.entryController = entryController;
        this.categoryController = categoryController;

    }

    public Scene getSumByCategoryScene(Stage secondStage) {

        entryController.emptyCache(userController.getUser().getUsername());
        List<Entry> entriesByUser = entryController.getEntries(userController.getUser().getUsername());
        TreeMap<String, Double> sumByCategory = entryController.sumByCategories(entriesByUser);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(); //(0, 10000, 50);
        yAxis.setForceZeroInRange(false);
        yAxis.setLabel("Summa (euroa)");

        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);

        barchart.setTitle("MENOT / TULOT KATEGORIOITTAIN");
        barchart.setLegendVisible(true);

        XYChart.Series sumCategory = new XYChart.Series();

        for (Map.Entry<String, Double> entries : sumByCategory.entrySet()) {

            String key = entries.getKey();
            Double value = entries.getValue();

            sumCategory.getData().add(new XYChart.Data(key, value));
        }

        sumByCategory.clear();
        barchart.getData().add(sumCategory);
        Scene scene = new Scene(barchart, 1280, 960);

        return scene;

    }

}
