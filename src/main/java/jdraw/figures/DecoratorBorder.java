package jdraw.figures;

import jdraw.framework.Figure;

import java.awt.*;

public class DecoratorBorder extends AbstractDecoratorFigure {

    public DecoratorBorder(Figure inner) {
        super(inner);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle r = getInner().getBounds();
        r.grow(5,5);
        return r;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Rectangle r = getBounds();
        //g.drawRect(r.x - 2 * count, r.y - 2 * count, r.width + 4 * count, r.height + 4 * count);
        g.setColor(Color.WHITE);
        g.drawLine(r.x, r.y, r.x, r.y + r.height);
        g.drawLine(r.x, r.y, r.x + r.width, r.y);
        g.setColor(Color.GRAY);
        g.drawLine(r.x + r.width, r.y, r.x + r.width, r.y + r.height);
        g.drawLine(r.x, r.y + r.height, r.x + r.width, r.y + r.height);
    }

}
