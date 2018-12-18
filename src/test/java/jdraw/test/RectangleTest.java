package jdraw.test;

import jdraw.figures.Rect;
import jdraw.framework.Figure;


public class RectangleTest extends AbstractFigureTest{
	@Override
	protected Figure createFigure(int x, int y, int w, int h) {
		return new Rect(x, y, w, h);
	}
}
