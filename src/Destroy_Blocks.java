
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

/**
 * 
 * @author check
 *
 */
public class Destroy_Blocks extends ImageView implements Tokens, Serializable
{
    private Point Coordinate;

    /**
     * This is a constuctor to initialize the Destroy_Blocks object
     * @param image Image to be set as Destroy_Blocks
     */
    public Destroy_Blocks(Image image)
    {
        super(image);
        this.setFitWidth(40);
        this.setFitHeight(40);
    }

    /**
     * To get coordinates of the Destroy_Blocks object
     * @return coordinates of the Destroy_Blocks object
     */
    @Override
    public Point getCoordinate() {
        return Coordinate;
    }
}