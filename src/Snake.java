

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.text.Text;

/**
 * 
 * @author check
 *
 */
public class Snake extends ImageView implements BoardObjects, Serializable
{
    /**
     *  Length of the snake
     */
    private int length;

    /**
     * Colour of the snake
     */
    private String colour;

    /**
     * Left button
     */
    private boolean leftkey;

    /**
     * Right button
     */
    private boolean rightkey;

    /**
     * The constructor to initialize Snake objects
     * @param x The x-coordinate of the snake
     * @param y The y-coordinate of the snake
     * @param image Image of snake
     */
    public Snake(double x, double y, Image image)
    {
        super(image);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /**
     * To move the snake
     * @param walls The wall object array
     * @param text Length of snake displayed on head
     */
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

    /**
     * To move left
     * @param leftkey Left button press
     */
    public void setLeftkey(boolean leftkey)
    {
        this.leftkey = leftkey;
    }

    /**
     * To move right
     * @param rightkey right button press
     */
    public void setRightkey(boolean rightkey)
    {
        this.rightkey = rightkey;
    }

    /**
     * To get length of snake
     * @return Length
     */
    public int getLength() {
        return length;
    }

    /**
     * To set length of snake
     * @param length Length of snake to be set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * To set colour of snake
     * @param colour Colour to be set
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
}