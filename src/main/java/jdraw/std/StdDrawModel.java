/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.*;

import static jdraw.framework.DrawModelEvent.Type.*;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Kevin Schaefer
 *
 */
public class StdDrawModel implements DrawModel {

	private final List<Figure> figureList = new LinkedList<>();
	private final List<DrawModelListener> drawModelListenerList = new CopyOnWriteArrayList<>();
	private final FigureListener fl = e -> {
		notifyDrawModelListeners(e.getFigure(), DRAWING_CHANGED);
	};

	@Override
	public void addFigure(Figure f) {
		if(!(figureList.contains(f))) {
			figureList.add(f);
			notifyDrawModelListeners(f, FIGURE_ADDED);
			f.addFigureListener(fl);
		}
	}


	@Override
	public Iterable<Figure> getFigures() {
		return figureList;
	}

	@Override
	public void removeFigure(Figure f) {
		if(figureList.remove(f)) {
			f.removeFigureListener(fl);
			notifyDrawModelListeners(f, FIGURE_REMOVED);
		}


	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		drawModelListenerList.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		drawModelListenerList.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		if(!figureList.contains(f)) {
			throw new IllegalArgumentException();
		}

		if(index >= figureList.size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		figureList.remove(f);
		figureList.add(index, f);
		notifyDrawModelListeners(f, DRAWING_CHANGED);

	}

	@Override
	public void removeAllFigures() {
		for (Figure f: figureList) {
			notifyDrawModelListeners(null, DRAWING_CLEARED);
			f.removeFigureListener(fl);
		}
		figureList.clear();
	}

	private void notifyDrawModelListeners (Figure figure, DrawModelEvent.Type type) {
		DrawModelEvent drawModelEvent = new DrawModelEvent(this, figure, type);
		drawModelListenerList.forEach(l -> l.modelChanged(drawModelEvent));
	}

}
