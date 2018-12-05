

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

/**
 * 
 * @author check
 *
 */
public class Magnet extends ImageView implements Tokens, Serializable
{
    private Point Coordinate;

    /**
     *  Duration of the magnet token
     */
    private int duration;

    /**
     * This is a constructor to initialize Magnet object
     * @param img
     */
    public Magnet(Image img)
    {
        super(img);
        super.setFitWidth(40.0);
        super.setFitHeight(40.0);
    }

    /**
     * To get coordinates of the Magnet object
     * @return coordinates of the Magnet object
     */
    @Override
    public Point getCoordinate() {
        return Coordinate;
    }

    /**
     * To know duration of the Magnet object
     * @return Duration of the Magnet object
     */
    public int getDuration() {
        return duration;
    }

    /**
     * To set duration of the Magnet object
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}



