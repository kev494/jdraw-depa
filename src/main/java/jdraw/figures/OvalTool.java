/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

/**
 * This tool defines a mode for drawing Ovals.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 */
public class OvalTool extends AbstractDrawTool {


	/**
	 * Create a new Oval tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public OvalTool(DrawContext context, String name) {
		super(context, name);
	}


	@Override
	public Figure createFigure(int x, int y) {
		return new Oval(x, y, 0, 0);
	}


}
