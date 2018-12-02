import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.text.Text;

public class Snake extends ImageView implements BoardObjects, Serializable
{
    private int length;
    private String colour;

    private boolean leftkey;
    private boolean rightkey;

    public Snake(double x, double y, Image image)
    {
        super(image);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    void move(Wall[] walls, Text text)
    {
        double x = getLayoutX();
        double y = getLayoutY();

        boolean canmoveleft  = true;
        boolean canmoveright = true;

        if (leftkey && !rightkey && getLayoutX() > 0)
        {
            for (int i = 0; i < walls.length; i++)
            {
                if(walls[i]!=null && (y > walls[i].getLayoutY() && y <= walls[i].getLayoutY() + walls[i].getFitHeight()) && walls[i].getLayoutX()<x)
                {
                    if(x - walls[i].getLayoutX() < 20){
                        canmoveleft = false;
                    }
                }
            }
            if(canmoveleft)
            {
                setLayoutX(getLayoutX() - 10);
                text.setLayoutX(getLayoutX() - 10);
            }
        }

        if (!leftkey && rightkey && getLayoutX() < 590)
        {
            for (int i = 0; i < walls.length; i++)
            {
                if(walls[i]!=null && (y > walls[i].getLayoutY() && y <= walls[i].getLayoutY() + walls[i].getFitHeight()) && walls[i].getLayoutX()>x)
                {
                    if(-x + walls[i].getLayoutX() < 20){
                        canmoveright = false;
                    }
                }
            }
            if(canmoveright)
            {
                setLayoutX(getLayoutX() + 10);
                text.setLayoutX(getLayoutX() + 10);
            }
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
