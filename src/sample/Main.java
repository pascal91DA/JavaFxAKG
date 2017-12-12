package sample;

import javafx.animation.Animation;
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
    private static int STEP_X = 3;

    private static int STEPS_SIZE = 30;

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

        gc.setFill(Color.LIGHTSKYBLUE);
        gc.setStroke(Color.BLACK);

        controller.pane.getChildren().add(canvas);

        double xPointsStart[] = {250, 400, 400, 250, 100, 100};
        double yPointsStart[] = {400, 300, 200, 100, 200, 300};

        double xStep[] = new double[xPointsStart.length];
        double yStep[] = new double[yPointsStart.length];

        double xPointsFinish[] = {400, 400, 400, 100, 100, 100};
        double yPointsFinish[] = {400, 400, 100, 100, 100, 400};
        int nPoints = 6;

        for (int i = 0; i < xPointsStart.length; i++) {
            xStep[i] = (xPointsFinish[i] - xPointsStart[i]) / STEPS_SIZE;
        }

        for (int i = 0; i < yPointsStart.length; i++) {
            yStep[i] =(yPointsFinish[i] - yPointsStart[i]) / STEPS_SIZE;
        }

        gc.strokePolygon(xPointsStart, yPointsStart, nPoints);
        gc.fillPolygon(xPointsStart, yPointsStart, nPoints);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100),
                        event -> {
                            if(CLEAR_TRACE){
                                gc.clearRect(0, 0, controller.pane.getWidth(), controller.pane.getHeight());
                            }

                            for (int i = 0; i < xPointsStart.length; i++) {
                                xPointsStart[i] += xStep[i];
                                xPointsStart[i] += STEP_X;
                            }

                            for (int i = 0; i < yPointsStart.length; i++) {
                                yPointsStart[i] += yStep[i];
                            }

                            gc.strokePolygon(xPointsStart, yPointsStart, nPoints);
                            gc.fillPolygon(xPointsStart, yPointsStart, nPoints);

                        }
                )
        );

        controller.DRAW_BUTTON.setOnAction(e -> {
            if(timeline.getStatus().equals(Animation.Status.RUNNING)){
                timeline.stop();
            }else {
                timeline.setCycleCount(STEPS_SIZE);
                CLEAR_TRACE = controller.CLEAR_TRACE.isSelected();
                timeline.play();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
