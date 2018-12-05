
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

/**
 * 
 * @author check
 *
 */
public class Block extends ImageView implements Restrictions, Serializable
{
    /**
     * Value of block object
     */
    private int value;
    private Point Coordinate;

    /**
     * This is a constuctor to initialize the block object
     * @param image Image to be set as Block
     */
    public Block(Image image)
    {
        super(image);
        super.setFitWidth(100.0);
        super.setFitHeight(100.0);
        this.value = 1;
    }

    /**
     * To get value of block object
     * @return value of that block object
     */
    public int getValue() {
        return value;
    }

    /**
     * To set value of block object
     * @return value of that block object to be set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * To get coordinates of the block object
     * @return coordinates of the block object
     */
    @Override
    public Point getCoordinate() {
        return Coordinate;
    }
}