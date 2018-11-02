import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends ImageView implements Restrictions
{
    private int length;
    private Point Coordinate;

    public Wall(Image image)
    {
        super(image);
    }

    @Override
    public Point getCoordinate() {
        return Coordinate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
