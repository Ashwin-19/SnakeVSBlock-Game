import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Snake extends ImageView implements BoardObjects
{
    private int length;
    private String colour;

    private boolean leftkey;
    private boolean rightkey;

    Snake(int x, int y, Image image)
    {
        super(image);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    void move()
    {
        if (leftkey && !rightkey && getLayoutX() > 0)
        {
            setLayoutX(getLayoutX() - 10);
        }

        if (!leftkey && rightkey && getLayoutX() < 640)
        {
            setLayoutX(getLayoutX() + 10);
        }

        if (!leftkey && !rightkey)
        {
            //
        }

        if(leftkey && rightkey)
        {
            //
        }
    }

    public void setLeftkey(boolean leftkey)
    {
        this.leftkey = leftkey;
    }

    public void setRightkey(boolean rightkey)
    {
        this.rightkey = rightkey;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
