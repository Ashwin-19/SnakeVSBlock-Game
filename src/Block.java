import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block extends ImageView implements Restrictions
{
    private int value;
    private Point Coordinate;

    public Block(Image image)
    {
        super(image);
        super.setFitWidth(100.0);
        super.setFitHeight(100.0);
        this.value = 1;
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
