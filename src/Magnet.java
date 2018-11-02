import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Magnet extends ImageView implements Tokens
{
    private Point Coordinate;
    private int duration;

    public Magnet(Image img)
    {
        super(img);
        super.setFitWidth(40.0);
        super.setFitHeight(40.0);
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
