package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main2 extends Application {

    private static double formPoints[][] = {
            {100, 400, 400, 100, 100, 300, 300, 100},
            {400, 400, 100, 100, 200, 200, 300, 300}
    };

    private static double figurePoints[][] = {
            {50, 250, 450, 250},
            {250, 450, 250, 50}
    };


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

        controller.pane.getChildren().add(canvas);

        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);

        gc.strokePolygon(figurePoints[0], figurePoints[1], figurePoints[0].length);
        gc.fillPolygon(figurePoints[0], figurePoints[1], figurePoints[0].length);

        gc.setFill(Color.LIGHTSKYBLUE);
        gc.setStroke(Color.BLACK);

        gc.strokePolygon(formPoints[0], formPoints[1], formPoints[0].length);
        gc.fillPolygon(formPoints[0], formPoints[1], formPoints[0].length);

    }

    public static void main(String[] args) {
        // launch(args);
        int asd = 0xF;
        System.out.println(Integer.toHexString(asd));
        System.out.println(Integer.toBinaryString(asd));
        System.out.println(asd);
    }
}
