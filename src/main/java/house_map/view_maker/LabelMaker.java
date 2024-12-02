package house_map.view_maker;

import javax.swing.*;
import java.awt.*;

public class LabelMaker extends JLabel {

    public LabelMaker(String name, Font font, int x, int y, int width, int height) {
        super(name);
        setBounds(x, y, width, height);
        setFont(font);

        //setBorder(new LineBorder(Color.RED, 1)); //Temporary Border
    }

    public LabelMaker(String name, Font font) {
        super(name);
        setFont(font);

        //setBorder(new LineBorder(Color.RED, 1)); //Temporary Border
    }
}
