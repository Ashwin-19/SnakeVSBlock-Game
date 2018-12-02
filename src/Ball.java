import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Ball extends ImageView implements Tokens, Serializable
{
    private int value;
    private Point Coordinate;

    public Ball(Image image)
    {
        super(image);
        this.setFitWidth(40);
        this.setFitHeight(40);
        this.value = 1;
    }

    @Override
    public Point getCoordinate() {
        return Coordinate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
