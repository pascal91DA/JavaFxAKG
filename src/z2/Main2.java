package z2;

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
import z1.Controller;

public class Main2 extends Application {

    private static boolean TRACE = false;
    private static int STEP_XY = 5;

    private static int STEPS_SIZE = 60;

    private double xPointsStart[] = {250, 400, 400, 250, 100, 100};
    private double yPointsStart[] = {400, 300, 200, 100, 200, 300};

    private double xPoints[] = {250, 400, 400, 250, 100, 100};
    private double yPoints[] = {400, 300, 200, 100, 200, 300};

    private double xStep[] = new double[xPointsStart.length];
    private double yStep[] = new double[yPointsStart.length];

    private double xPointsFinish[] = {400, 400, 400, 100, 100, 100};
    private double yPointsFinish[] = {400, 400, 100, 100, 100, 400};
    private int nPoints = 6;

    private static double formPoints[][] = {
            {200, 500, 500, 200, 200, 400, 400, 200},
            {500, 500, 200, 200, 300, 300, 400, 400}
    };

    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainform2.fxml"));
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

        gc.setFill(Color.WHITE);
        gc.strokePolygon(formPoints[0], formPoints[1], formPoints[0].length);
        gc.fillPolygon(formPoints[0], formPoints[1], formPoints[0].length);
        gc.fillRect(0, 0, controller.pane.getWidth(), controller.pane.getHeight());


        controller.pane.getChildren().add(canvas);

        for (int i = 0; i < xPointsStart.length; i++) {
            xStep[i] = (xPointsFinish[i] - xPointsStart[i]) / STEPS_SIZE;
        }

        for (int i = 0; i < yPointsStart.length; i++) {
            yStep[i] = (yPointsFinish[i] - yPointsStart[i]) / STEPS_SIZE;
        }

        gc.strokePolygon(xPoints, yPoints, nPoints);
        gc.strokePolygon(formPoints[0], formPoints[1], formPoints[0].length);
        gc.fillPolygon(formPoints[0], formPoints[1], formPoints[0].length);
        controller.DRAW_BUTTON.setOnAction(e -> {
            if (timeline != null && timeline.getStatus().equals(Animation.Status.RUNNING)) {
                timeline.stop();
                controller.DRAW_BUTTON.setText("СТАРТ");
            } else {
                restart();
                gc.clearRect(0, 0, controller.pane.getWidth(), controller.pane.getHeight());
                gc.fillRect(0, 0, controller.pane.getWidth(), controller.pane.getHeight());
                timeline = new Timeline(
                        new KeyFrame(Duration.millis(100),
                                event -> {
                                    if (!TRACE) {
                                        gc.clearRect(0, 0, controller.pane.getWidth(), controller.pane.getHeight());
                                        gc.fillRect(0, 0, controller.pane.getWidth(), controller.pane.getHeight());
                                    }

                                    for (int i = 0; i < xPoints.length; i++) {
                                        xPoints[i] += xStep[i];
                                        xPoints[i] += STEP_XY;
                                        yPoints[i] += yStep[i];
                                        yPoints[i] += STEP_XY;
                                    }

                                    gc.strokePolygon(xPoints, yPoints, nPoints);
                                    gc.strokePolygon(formPoints[0], formPoints[1], formPoints[0].length);
                                    gc.fillPolygon(formPoints[0], formPoints[1], formPoints[0].length);

                                }
                        )
                );

                timeline.setCycleCount(STEPS_SIZE);
                TRACE = false;
                timeline.play();
                controller.DRAW_BUTTON.setText("СТОП");
            }
        });

    }

    private void restart() {
        for (int i = 0; i < xPointsStart.length; i++) {
            xPoints[i] = xPointsStart[i];
            yPoints[i] = yPointsStart[i];
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
