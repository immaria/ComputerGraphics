import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 300);

        Rectangle rUpper = new Rectangle(150, 50, 200, 50);
        Rectangle rLower = new Rectangle(150, 200, 200, 50);
        Rectangle r = new Rectangle(300, 200, 50, 50);

        r.setFill(Color.RED);
        rLower.setFill(Color.RED);
        rUpper.setFill(Color.FORESTGREEN);

        Ellipse ellLeft = new Ellipse(175,150,25,100);
        Ellipse ellRight = new Ellipse(325,150,25,100);
        ellLeft.setFill(Color.BLUE);
        ellRight.setFill(Color.YELLOW);

        root.getChildren().add(rLower);
        root.getChildren().add(ellLeft);
        root.getChildren().add(rUpper);
        root.getChildren().add(ellRight);
        root.getChildren().add(r);

        scene.setFill(Color.ORCHID);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}