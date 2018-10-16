package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractFigure implements Figure {

    private final java.util.List<FigureListener> figureListenerList = new CopyOnWriteArrayList<>();
    /**
     * Returns a list of 8 handles for this Rectangle.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public final void addFigureListener(FigureListener listener) {
        figureListenerList.add(listener);
    }

    @Override
    public final void removeFigureListener(FigureListener listener) {
        figureListenerList.remove(listener);
    }

    @Override
    public Figure clone() {
        return null;
    }

    protected final void propagateFigureEvent() {
        FigureEvent figureEvent = new FigureEvent(this);
        figureListenerList.forEach(l -> l.figureChanged(figureEvent));
    }

}
