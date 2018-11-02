import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block extends ImageView implements Restrictions
{
    private int value;
    private Point Coordinate;

    public Block(Image image)
    {
        super(image);
        super.setFitWidth(65.0);
        super.setFitHeight(65.0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Point getCoordinate() {
        return Coordinate;
    }
}
