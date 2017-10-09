package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainform.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("AKG");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Controller controller = fxmlLoader.getController();

        Canvas canvas = new Canvas(controller.pane.getWidth(), controller.pane.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.AQUA);
        gc.setStroke(Color.RED);
        gc.setLineWidth(0.5);

        for (int i = 20; i < 60; i++) {
            gc.rect(i, i, 24, 24);
            gc.stroke();
        }

        controller.pane.getChildren().add(canvas);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
