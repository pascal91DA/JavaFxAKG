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

public class Main4 extends Application {
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
        meshView.setMaterial(new PhongMaterial(Color.WHITE));
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
        light.setColor(Color.grayRgb(31));

        PointLight pointLight = new PointLight();
        pointLight.setTranslateX(400);
        pointLight.setTranslateY(200);
        pointLight.setTranslateZ(-600);
        pointLight.setColor(Color.rgb(60, 60, 60));

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

        Line l0 = new Line(points[0], points[2], points[3], points[5]);
        Line l1 = new Line(points[3], points[5], points[6], points[8]);
        Line l2 = new Line(points[6], points[8], points[9], points[11]);
        Line l3 = new Line(points[9], points[11], points[12], points[14]);
        Line l4 = new Line(points[12], points[14], points[15], points[17]);
        Line l5 = new Line(points[15], points[17], points[18], points[20]);

        Line l6 = new Line(points[0], points[2], points[3], points[5]);
        Line l7 = new Line(points[3], points[5], points[6], points[8]);
        Line l8 = new Line(points[6], points[8], points[9], points[11]);
        Line l9 = new Line(points[9], points[11], points[12], points[14]);
        Line l10 = new Line(points[12], points[14], points[15], points[17]);
        Line l11 = new Line(points[15], points[17], points[18], points[20]);

        Line l12 = new Line(points[15], points[17], points[18], points[20]);

        Group lineUpperGroup = new Group(l0, l1, l2, l3, l4, l5);
        lineUpperGroup.setRotationAxis(Rotate.X_AXIS);
        lineUpperGroup.setRotate(90);
        lineUpperGroup.setTranslateX(400);
        lineUpperGroup.setTranslateY(200);
        lineUpperGroup.setTranslateZ(0);

        Group lineUnderGroup = new Group(l6, l7, l8, l9, l10, l11);
        lineUnderGroup.setRotationAxis(Rotate.X_AXIS);
        lineUnderGroup.setRotate(90);
        lineUnderGroup.setTranslateX(400);
        lineUnderGroup.setTranslateY(400);
        lineUnderGroup.setTranslateZ(0);

        Group hexagonGroup = new Group(lineUpperGroup, lineUnderGroup);
        hexagonGroup.setRotationAxis(Rotate.Y_AXIS);

        Group root = new Group(cylinder, light, lineX, textX, lineY, textY, lineZ, textZ);

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
        tl.play();

        Timeline t2 = new Timeline();
        t2.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveMesh = new KeyFrame(Duration.seconds(0.05),
                event -> meshView.setRotate(meshView.getRotate() + 3));

        t2.getKeyFrames().add(moveMesh);
        t2.play();

        Timeline t3 = new Timeline();
        t3.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveHexagonGroup = new KeyFrame(Duration.seconds(0.05),
                event -> hexagonGroup.setRotate(hexagonGroup.getRotate() + 3));

        t3.getKeyFrames().add(moveHexagonGroup);
        t3.play();
    }
}