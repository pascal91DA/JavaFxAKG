package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static boolean CLEAR_TRACE = false;
    private static int STEP_X = 5;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainform.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("AKG");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Controller controller = fxmlLoader.getController();

        Canvas canvas = new Canvas(controller.pane.getWidth(), controller.pane.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);

        controller.pane.getChildren().add(canvas);

        double xPoints[] = {20, 30, 30, 20, 10, 10};
        double yPoints[] = {10, 20, 30, 40, 30, 20};
        int nPoints = 6;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100),
                        event -> {
                            if(CLEAR_TRACE){
                                gc.clearRect(0, 0, controller.pane.getWidth(), controller.pane.getHeight());
                            }

                            gc.strokePolygon(xPoints, yPoints, nPoints);
                            gc.fillPolygon(xPoints, yPoints, nPoints);

                            for (int i = 0; i < xPoints.length; i++){
                                xPoints[i] += STEP_X;
                            }

                        }
                )
        );

//        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setCycleCount(50);
        timeline.play();
//        timeline.stop();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
