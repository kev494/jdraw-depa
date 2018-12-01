package jdraw.figures;

import jdraw.framework.Figure;

import java.awt.*;

public class DecoratorBorder extends AbstractDecoratorFigure {
    private int count;
    public DecoratorBorder(Figure inner) {
        super(inner);
        count = 1;
    }

    public DecoratorBorder(Figure inner, int count) {
        super(inner);
        this.count = count;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Rectangle r = getBounds();
        g.drawRect(r.x - 2 * count, r.y - 2 * count, r.width + 4 * count, r.height + 4 * count);
    }

    public int getCount() {
       return count;
    }
}
