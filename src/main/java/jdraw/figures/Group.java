package jdraw.figures;

import jdraw.figures.Handle.*;
import jdraw.framework.*;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Group implements Figure, FigureGroup {
    private List<Figure> parts;
    private final List<FigureListener> figureListenerList = new CopyOnWriteArrayList<>();

    public Group(List<Figure> parts) {
        this.parts = new CopyOnWriteArrayList<>(parts);
    }

    public Group(Group g) {
        parts = new CopyOnWriteArrayList<>();
        g.parts.forEach( f -> {
            parts.add(f.clone());
        });
    }

    @Override
    public void draw(Graphics g) {
        parts.forEach(f -> f.draw(g));
    }

    @Override
    public void move(int dx, int dy) {
        parts.forEach(f -> f.move(dx, dy));
        propagateFigureEvent();
    }

    @Override
    public boolean contains(int x, int y) {
        return getBounds().contains(x,y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {

    }

    @Override
    public Rectangle getBounds() {
        Rectangle rectangle = parts.get(0).getBounds(); //new Rectangle(parts.get(0).getBounds());
        parts.forEach(f -> rectangle.add(f.getBounds()));
        return rectangle;
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new CopyOnWriteArrayList<>();
        handles.add(new Handle(new NorthWestHandle(this)));
        handles.add(new Handle(new NorthEastHandle(this)));
        handles.add(new Handle(new SouthEastHandle(this)));
        handles.add(new Handle(new SouthWestHandle(this)));

        return handles;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        figureListenerList.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figureListenerList.remove(listener);
    }
    private void propagateFigureEvent() {
        FigureEvent figureEvent = new FigureEvent(this);
        figureListenerList.forEach(l -> l.figureChanged(figureEvent));
    }

    @Override
    public Group clone() {
        return new Group(this);
    }

    @Override
    public void swapHorizontal() {

    }

    @Override
    public void swapVertical() {

    }

    @Override
    public Iterable<Figure> getFigureParts() {
        return parts;
    }
}
