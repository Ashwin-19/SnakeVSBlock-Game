import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

/**
 * 
 * @author check
 *
 */
public class Coins extends ImageView implements BoardObjects, Serializable
{
    /**
     * Value of the coin object
     */
    private int value;
    private Point Coordinate;

    /**
     * This is a constructor to initialize coins object
     * @param image
     */
    public Coins(Image image)
    {
        super(image);
        this.setFitWidth(40);
        this.setFitHeight(40);
        this.setValue(5);
    }

    /**
     * To get value of coins object
     * @return value of that coins object
     */
    public int getValue() {
        return value;
    }

    /**
     * To set value of coins object
     * @return value of that coins object to be set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * To get coordinates of the coins object
     * @return coordinates of the coins object
     */
    public Point getCoordinate() {
        return Coordinate;
    }
}
