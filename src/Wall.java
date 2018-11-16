import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Wall extends ImageView implements Restrictions
{
    private int length;
    private Point Coordinate;
    Random random;

    public Wall(Image image)
    {
        super(image);

        double h1 = 300;
        double h2 = 600;
        double h3 = 450;

        random = new Random();
        int height_select = random.nextInt(3);

        this.setFitWidth(30);

        if(height_select == 0)
        {
            this.setFitHeight(h1);
        }
        else if(height_select == 1)
        {
            this.setFitHeight(h2);
        }
        else
        {
            this.setFitHeight(h3);
        }
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
