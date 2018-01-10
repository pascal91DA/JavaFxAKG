package z4;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fx3DShapeExample1 extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        Line lineX = new Line();
        lineX.setStartX(0);
        lineX.setStartY(400);
        lineX.setEndX(400);
        lineX.setEndY(300);

        Line lineY = new Line();
        lineY.setStartX(400);
        lineY.setStartY(0);
        lineY.setEndX(400);
        lineY.setEndY(300);

        Line lineZ = new Line();
        lineZ.setStartX(800);
        lineZ.setStartY(400);
        lineZ.setEndX(400);
        lineZ.setEndY(300);

        Text textX = new Text();
        textX.setX(0);
        textX.setY(380);
        textX.setText("X");

        Text textY = new Text();
        textY.setX(780);
        textY.setY(380);
        textY.setText("Y");

        Text textZ = new Text();
        textZ.setX(380);
        textZ.setY(20);
        textZ.setText("Z");

        AmbientLight light = new AmbientLight();
        light.setColor(Color.WHITE);

        PhongMaterial material1 = new PhongMaterial();
        material1.setDiffuseColor(Color.WHITE);
        material1.setSpecularColor(Color.BLACK);
        material1.setSpecularPower(0.1);

        Cylinder cylinder = new Cylinder(100, 200, 6);
        cylinder.setTranslateX(400);
        cylinder.setTranslateY(300);
        cylinder.setTranslateZ(0);
        cylinder.setDrawMode(DrawMode.LINE);
        cylinder.setRotationAxis(Rotate.Y_AXIS);

        cylinder.setMaterial(material1);

        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-20);

        Group root = new Group(lineX, textX, lineY, textY, lineZ, textZ, cylinder);

        Scene scene = new Scene(root, 800, 600, true);
        scene.setCamera(camera);

        stage.setScene(scene);
        stage.setTitle("AKG 4");
        stage.setResizable(false);
        stage.show();

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.05),
                event -> cylinder.setRotate(cylinder.getRotate() + 0.5));

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }
}