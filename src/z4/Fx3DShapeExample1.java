package z4;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
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

        float[] points =
                {
                        0,        100,  100,   // 0
                        86.603f,  100,  50,    // 1
                        86.603f,  100,  -50,   // 2
                        0,        100,  -100,  // 3
                        -86.603f, 100,  -50,   // 4
                        -86.603f, 100,  50,    // 5

                        0,        -100,  100,  // 6
                        86.603f,  -100,  50,   // 7
                        86.603f,  -100,  -50,  // 8
                        0,        -100,  -100, // 9
                        -86.603f, -100,  -50,  // 10
                        -86.603f, -100,  50,   // 11
                };

        float[] texCoords =
                {
                        0.5f, 0.5f, // t0 (it0 = 0)
                        0.0f, 1.0f, // t1 (it1 = 1)
                        1.0f, 1.0f, // t2 (it2 = 2)
                        1.0f, 1.0f,
                        1.0f, 1.0f,
                        1.0f, 1.0f,
                        0.5f, 0.5f, // t0 (it0 = 0)
                        0.0f, 1.0f, // t1 (it1 = 1)
                        1.0f, 1.0f, // t2 (it2 = 2)
                        1.0f, 1.0f,
                        1.0f, 1.0f,
                        1.0f, 1.0f
                };

        int[] faces =
                {
                        0, 0, 1, 1, 2, 2,
                        2, 2, 3, 3, 4, 4,
                        4, 4, 5, 5, 0, 0,
                        0, 0, 2, 2, 4, 4,

                        6, 6, 8, 8, 7, 7,
                        8, 8, 10, 10, 9, 9,
                        10, 10, 6, 6, 11, 11,
                        6, 6, 10, 10, 8, 8,

                        0, 0, 6, 6, 7, 7,
                        0, 0, 7, 7, 1, 1,

                        1, 1, 7, 7, 8, 8,
                        1, 1, 8, 8, 2, 2,

                        2, 2, 8, 8, 9, 9,
                        2, 2, 9, 9, 3, 3,

                        3, 3, 9, 9, 10, 10,
                        3, 3, 10, 10, 4, 4,

                        4, 4, 10, 10, 11, 11,
                        4, 4, 11, 11, 5, 5,

                        5, 5, 11, 11, 6, 6,
                        5, 5, 6, 6, 0, 0,


                };

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(texCoords);
        mesh.getFaces().addAll(faces);

        MeshView meshView = new MeshView();
        meshView.setMesh(mesh);
        meshView.setMaterial(new PhongMaterial(Color.RED));
        // meshView.setDrawMode(DrawMode.LINE);
        meshView.setTranslateX(400);
        meshView.setTranslateY(300);
        meshView.setTranslateZ(0);
        meshView.setRotationAxis(Rotate.Y_AXIS);


        Line lineX = new Line();
        lineX.setStartX(0);
        lineX.setStartY(390);
        lineX.setEndX(400);
        lineX.setEndY(300);

        Line lineY = new Line();
        lineY.setStartX(400);
        lineY.setStartY(0);
        lineY.setEndX(400);
        lineY.setEndY(300);

        Line lineZ = new Line();
        lineZ.setStartX(800);
        lineZ.setStartY(390);
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
        light.setColor(Color.GRAY);

        PointLight pointLight = new PointLight();
        pointLight.setTranslateX(400);
        pointLight.setTranslateY(200);
        pointLight.setTranslateZ(-1000);
        pointLight.setColor(Color.GRAY);

        Cylinder cylinder = new Cylinder(100, 200, 6);
        cylinder.setTranslateX(400);
        cylinder.setTranslateY(300);
        cylinder.setTranslateZ(0);
        // cylinder.setMaterial(new PhongMaterial(Color.RED));
        cylinder.setDrawMode(DrawMode.LINE);
        cylinder.setRotationAxis(Rotate.Y_AXIS);

        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-20);

        // Group root = new Group(lineX, textX, lineY, textY, lineZ, textZ, cylinder, meshView);
        Group root = new Group(lineX, textX, lineY, textY, lineZ, textZ, meshView, light, pointLight);

        Scene scene = new Scene(root, 800, 600, true);
        scene.setCamera(camera);

        stage.setScene(scene);
        stage.setTitle("AKG 4");
        stage.setResizable(false);
        stage.show();

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.05),
                event -> cylinder.setRotate(cylinder.getRotate() + 3));

        tl.getKeyFrames().add(moveBall);
        //tl.play();

        Timeline t2 = new Timeline();
        t2.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveMesh = new KeyFrame(Duration.seconds(0.05),
                event -> meshView.setRotate(meshView.getRotate() + 3));

        t2.getKeyFrames().add(moveMesh);
        t2.play();
    }
}