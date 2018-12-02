import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Coins extends ImageView implements BoardObjects, Serializable
{
    private int value;
    private Point Coordinate;

    public Coins(Image image)
    {
        super(image);
        this.setFitWidth(40);
        this.setFitHeight(40);
        this.setValue(5);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Point getCoordinate() {
        return Coordinate;
    }
}
