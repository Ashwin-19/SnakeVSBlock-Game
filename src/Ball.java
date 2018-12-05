import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

/**
 * 
 * @author check
 *
 */
public class Ball extends ImageView implements Tokens, Serializable
{
    /**
     * value of the ball
     */
    private int value;

    private Point Coordinate;

    /**
     * This is a constructor to initialize the ball object
     * @param image The ball image
     */
    public Ball(Image image)
    {
        super(image);
        this.setFitWidth(40);
        this.setFitHeight(40);
        this.value = 1;
    }

    /**
     *  To get the coordinates of the ball object
     * @return Coordinates of the ball object
     */
    @Override
    public Point getCoordinate() {
        return Coordinate;
    }

    /**
     * To get value of the ball object
     * @return value of ball object
     */
    public int getValue() {
        return value;
    }

    /**
     * To set value of ball object
     * @param value The value of ball object to be set
     */
    public void setValue(int value) {
        this.value = value;
    }
}