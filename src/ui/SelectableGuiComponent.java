package ui;

public abstract class SelectableGuiComponent extends GuiComponent implements Selectable {

	private boolean selected;
	
	private SelectionHandler selectionHandler;
	
	public SelectableGuiComponent (SelectionHandler selectionHandler, GUI handler, String id) {
		super (handler, id);
		this.selectionHandler = selectionHandler;
	}
	
	@Override
	public void mouseButtonEvent (int button, double x, double y) {
		if (button == 0) {
			select ();
		}
	}

	@Override
	public void select () {
		selected = true;
		selectionHandler.notifySelect (this);
	}
	
	@Override
	public void deselect () {
		selected = false;
		selectionHandler.notifyDeselect (this);
	}
	
	@Override
	public boolean isSelected () {
		return selected;
	}
	
}
