/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.FigureHandle;
import jdraw.figures.Handle.*;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Line extends AbstractFigure {
	/**
	 * Use the java.awt.geom.Line2D in order to save/reuse code.
	 */
	private final Line2D line;
	private final List<FigureHandle> handles = new LinkedList<>();
	/**
	 * Create a new line of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the line
	 * @param y the y-coordinate of the upper left corner of the line
	 * @param w the line's width
	 * @param h the line's height
	 */
	public Line(int x, int y, int w, int h) {

		line = new Line2D.Double(x, y, w, h);
	}

	public Line(Line l) {
		//line = (Line2D.Double) l.line.clone();
		line = new Line2D.Double(l.getP1(), l.getP2());
	}

	/**
	 * Draw the line to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2());
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		if(null == origin && null == corner) {
			throw new NullPointerException();
		}
		Line2D original = new Line2D.Double(line.getX1(), line.getY1(), line.getX2(), line.getY2());
		line.setLine(origin, corner);
		if(!original.equals(line)) {
			propagateFigureEvent();
		}
	}

	@Override
	public void move(int dx, int dy) {
	    if(dx == 0 && dy == 0) {
            return;
        }
		line.setLine(line.getX1()+dx, line.getY1()+dy, line.getX2()+dx, line.getY2()+dy);
		propagateFigureEvent();
	}

	@Override
	public boolean contains(int x, int y) {

		int hitboxsize = 10;
		int boxX = x - hitboxsize / 2;
		int boxY = y - hitboxsize / 2;

		return line.intersects(boxX, boxY, hitboxsize, hitboxsize);
	}

	@Override
	public Rectangle getBounds() {
		return line.getBounds();
	}

	public java.util.List<FigureHandle> getHandles() {

		handles.add(new Handle(new LineStartHandle(this)));
		handles.add(new Handle(new LineEndHandle(this)));
		return handles;
	}

	@Override
	public void swapHorizontal() {

	}

	@Override
	public void swapVertical() {

	}

	public Point getP1() {
		return new Point((int)line.getX1(), (int)line.getY1());
	}

	public Point getP2() {
		return new Point((int)line.getX2(), (int)line.getY2());
	}

	public void setP1(Point point) {
		if(point != line.getP1()) {
			line.setLine(point, line.getP2());
			propagateFigureEvent();
		}
	}

	public void setP2(Point point) {
		if(point != line.getP2()) {
			line.setLine(line.getP1(), point);
			propagateFigureEvent();
		}
	}

	@Override
	public Line clone() {
		return new Line(this);
	}
}
