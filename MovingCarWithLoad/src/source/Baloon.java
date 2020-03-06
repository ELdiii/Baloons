package source;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Baloon extends Canvas {

    //premenne
    protected GraphicsContext gc;
    protected double rychlost;
    protected Group root;
    protected Timeline timeline;
    protected static int popped = 0;
    protected static int destroyed = 0;
    protected double vyskaOkna;
    protected double sirkaOkna;

    //konštruktor
    public Baloon(Group a) {
        super(50,50);
        root = a;
        vyskaOkna = root.getScene().getHeight();
        sirkaOkna = root.getScene().getWidth();
        //generovanie čisla na vytvorenie objektu (vyska)
        setLayoutY(GenerateDoubleInRange(vyskaOkna-20,vyskaOkna+10));
        //generovanie čisla na vytvorenie objektu (sirka)
        setLayoutX(GenerateDoubleInRange(sirkaOkna-((sirkaOkna/12)*11),sirkaOkna-((sirkaOkna/12)*2)));
        gc = getGraphicsContext2D();
        rychlost = GenerateDoubleInRange(1,3);
        draw();
        timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> posun()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        setOnMouseClicked(e -> {
            root.getChildren().remove(this);
            timeline.stop();
            destroyed++;
        });

    }
    //vykreslenie
    public void draw() {
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.setFill(Color.rgb(GenerateIntInRange(0,254),GenerateIntInRange(0,254),GenerateIntInRange(0,254)));
        gc.fillOval(15,1,20,30);
        gc.setFill(Color.WHITE);
        gc.fillRect(24.5,31,1,20);
        gc.setStroke(Color.WHITE);
        gc.strokeOval(15,1,20,30);
    }
    //posun nahor
    public void posun() {
        setLayoutY(getLayoutY()-rychlost);
        int rnd = GenerateIntInRange(0,80);
        //posun do strán (alpha verzia)
        switch (rnd) {
            case 2:
                setLayoutX(getLayoutX()+10);
                break;
            case 8:
                setLayoutX(getLayoutX()-10);
                break;
        }
        if (getLayoutY()<0) {
            odstran();
        }
    }
    //generovanie čísel (double)
    public static double GenerateDoubleInRange(double min, double max) {

        return (Math.random() * ((max - min) + 1)) + min;
    }
    //generovanie čísel (int)
    public static int GenerateIntInRange(int min, int max) {

        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    //odstranenie
    public void odstran() {
        root.getChildren().remove(this);
        timeline.stop();
        popped++;
    }


}
