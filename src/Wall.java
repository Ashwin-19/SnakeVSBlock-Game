

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Random;

/**
 * 
 * @author check
 *
 */
public class Wall extends ImageView implements Restrictions, Serializable
{
    /**
     * length of wall
     */
    private int length;

    /**
     * coordinates of wall
     */
    private Point Coordinate;


    Random random;

    /**
     * The constructor to initialize the wall object
     * @param image Image to be set as wall
     */
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

    /**
     * To get coordinates of the wall
     * @return Coordinates of wall
     */
    @Override
    public Point getCoordinate() {
        return Coordinate;
    }

    /**
     * To get length of the wall
     * @return Length of wall
     */
    public int getLength() {
        return length;
    }

    /**
     * To set length of wall
     * @param length Length of wall to be set
     */
    public void setLength(int length) {
        this.length = length;
    }
}


