package jdraw.std;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class AddCommand implements DrawCommand {

    private Figure figure;
    private DrawModel model;

    public AddCommand(Figure f, DrawModel model) {
        this.figure = f;
        this.model = model;
    }

    @Override
    public void redo() {
        model.addFigure(figure);
    }

    @Override
    public void undo() {
        model.removeFigure(figure);
    }
}
