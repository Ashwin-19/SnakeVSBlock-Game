import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Shield extends ImageView implements Tokens, Serializable
{
    private Point Coordinate;
    private int duration;

    public Shield(Image immge)
    {
        super(immge);
        this.setFitWidth(40);
        this.setFitHeight(40);
    }

    @Override
    public Point getCoordinate() {
        return Coordinate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
