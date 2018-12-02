import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Destroy_Blocks extends ImageView implements Tokens, Serializable
{
    private Point Coordinate;

    public Destroy_Blocks(Image image)
    {
        super(image);
        this.setFitWidth(40);
        this.setFitHeight(40);
    }
    @Override
    public Point getCoordinate() {
        return Coordinate;
    }
}
