package source;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //root
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.BLACK);
        Text pop = new Text();
        Text des = new Text();
        //generovanie každú sekundu
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(1), evt-> {
            Baloon b = new Baloon(root);
            pop.setText("Popped: "+b.popped);
            pop.setFill(Color.WHITE);
            pop.setX(root.getScene().getWidth()-90);
            pop.setY(root.getScene().getHeight()-70);
            des.setText("Destroyed: "+b.destroyed);
            des.setFill(Color.WHITE);
            des.setX(root.getScene().getWidth()-90);
            des.setY(root.getScene().getHeight()-55);
            root.getChildren().add(b);
        }));
        root.getChildren().addAll(pop,des);
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
        //stage
        primaryStage.setTitle("BA DUM TSS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
