package utils;

import com.badlogic.gdx.graphics.Color;

public class Constants {
    public static final float MIN_WIDTH = 1250;
    public static final float MIN_HEIGHT = 700;
    public static final float MAX_WIDTH = 1400;
    public static final float MAX_HEIGHT = 800;

    public static final int MAX_ARRAY_ELEMENT = 16;
    public static final int MAX_ELEMENT_VAL = 280;
    public static final int MIN_ELEMENT_VAL = 25;

    public static final Color [] COUNT_SLOT_COLOR_ARRAY = new Color[]
            {
                    new Color(193.0f/255.0f,193.0f/255.0f,193.0f/255.0f,1),
                    new Color(135.0f/255.0f, 170.0f/255.0f, 222.0f/255.0f, 1),
                    new Color(128.0f/255.0f, 128.0f/255.0f, 1, 1),
                    new Color(135.0f/255.0f, 135.0f/255.0f, 222.0f/255.0f, 1),
                    new Color(179.0f/255.0f, 128.0f/255.0f, 1, 1),
                    new Color(170.0f/255.0f, 135.0f/255.0f, 222.0f/255.0f, 1)
            };

    public static final Color DEFAULT_COLOR = new Color(128.0f/255.0f, 179.0f/255.0f, 1, 1);
    public static final Color ORANGE_COLOR = new Color(1,153.0f/255.0f,85.0f/255.0f,1);
    public static final Color GREEN_COLOR = new Color(63.0f/255.0f,195.0f/255.0f,128.0f/255.0f,1);
    public static final Color RED_COLOR = new Color(1,128.0f/255.0f,128.0f/255.0f,1);
    public static final Color BLUE_COLOR = new Color(68.0f/255.0f,108.0f/255.0f,179.0f/255.0f,1);

    public static final float TIME_PER_ACTION = 0.2f;
    public static final float REAL_TIME_PER_ACTION = 0.15f;
}
