package ui.authoring.delegates;

public interface DisplayDelegate {
	public abstract void slideDisplayOut();
	public abstract void didPressLoadButton();
	public abstract void didPressSaveButton();
	public abstract void slideDisplayIn();
}
