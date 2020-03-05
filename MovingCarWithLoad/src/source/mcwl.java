package source;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class mcwl extends Canvas {

    GraphicsContext gc;
    Boolean nalozene;

    mcwl() {
        super(300,300);
        gc = getGraphicsContext2D();
        nalozene = true;
        vykresli();
        Timeline t = new Timeline(new KeyFrame(Duration.millis(16),e -> posun()));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }

    public void vykresli() {
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.setFill(Color.RED);
        gc.fillOval(30,250,20,20);
        gc.fillOval(250,250,20,20);
        gc.setFill(Color.BLUE);
        gc.fillRect(40,250,220,10);
        gc.fillRect(160,160,100,90);
        gc.setFill(Color.GRAY);
        gc.fillRect(200,170,55,40);
        gc.setFill(Color.BLACK);
        gc.fillOval(230,180,10,10);
        gc.fillRect(232.5,187.5,5,22.5);
        if (nalozene) {
            gc.setFill(Color.SANDYBROWN);
            gc.fillRect(120,230,40,20);
            gc.fillRect(75,230,40,20);
            gc.fillRect(45,230,25,20);
            gc.fillRect(95,205,45,20);
        }
    }

    public void posun() {
        if (nalozene) {
            setLayoutX(getLayoutX()+4);
        } else {
            setLayoutX(getLayoutX()-4);
        }
       if (getLayoutX()>super.getScene().getWidth()+30) {
           nalozene = !nalozene;
           vykresli();
       }
       if (getLayoutX()<-340) {
           nalozene = !nalozene;
           vykresli();
       }
    }
}
