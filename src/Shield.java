/**
 * 
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Shield extends ImageView implements Tokens, Serializable
{
    private Point Coordinate;

    /**
     *  Duration of the shield token
     */
    private int duration;

    /**
     * This is a constructor to initialize shield object
     * @param img
     */
    public Shield(Image img)
    {
        super(img);
        this.setFitWidth(40);
        this.setFitHeight(40);
    }

    /**
     * To get coordinates of the shield object
     * @return coordinates of the shield object
     */
    @Override
    public Point getCoordinate() {
        return Coordinate;
    }

    /**
     * To know duration of the shield object
     * @return Duration of the shield object
     */
    public int getDuration() {
        return duration;
    }

    /**
     * To set duration of the shield object
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}