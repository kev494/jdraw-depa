package jdraw.std;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StdDrawCommandHandler implements DrawCommandHandler {
    private Stack<DrawCommand> undoStack = new Stack<>();
    private Stack<DrawCommand> redoStack = new Stack<>();
    private Script script = null;

    @Override
    public void addCommand(DrawCommand cmd) {
        if(script == null) {
            undoStack.push(cmd);
        } else {
            script.list.add(cmd);
        }
        redoStack.clear();

    }

    @Override
    public void undo() {
        if(!undoStack.empty()) {
            DrawCommand cmd = undoStack.pop();
            redoStack.push(cmd);
            cmd.undo();
        }
    }

    @Override
    public void redo() {
        if(!redoStack.empty()) {
            DrawCommand cmd = redoStack.pop();
            undoStack.push(cmd);
            cmd.redo();
        }
    }

    @Override
    public boolean undoPossible() {
        return undoStack.size() > 0;
    }

    @Override
    public boolean redoPossible() {
        return redoStack.size() > 0;
    }

    @Override
    public void beginScript() {
        script = new Script();
    }

    @Override
    public void endScript() {
        if(script != null) {
            Script s = script;
            script = null;
            addCommand(s);
        }

    }

    @Override
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }

    private static class Script implements DrawCommand {
        private List<DrawCommand> list = new ArrayList<>();
        @Override
        public void redo() {
            for(DrawCommand cmd : list) {
                cmd.redo();
            }
        }

        @Override
        public void undo() {
            for(DrawCommand cmd : list) {
                cmd.undo();
            }
        }
    }
}
