package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;

public abstract class AbstractDecoratorFigure implements Figure {
    private Figure inner;

    public AbstractDecoratorFigure(Figure inner) {
        this.inner = inner;
    }

    @Override
    public void draw(Graphics g) {
        inner.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        inner.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        return inner.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        inner.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds() {
        return inner.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        return inner.getHandles();
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        inner.addFigureListener(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        inner.removeFigureListener(listener);
    }

    @Override
    public Figure clone() {
        try {
            AbstractDecoratorFigure f = (AbstractDecoratorFigure) super.clone();
            f.inner = inner.clone();
            return f;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public Figure getInner() {
        return inner;
    }

}
