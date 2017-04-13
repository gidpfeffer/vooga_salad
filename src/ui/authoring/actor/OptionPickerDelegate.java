package ui.authoring.actor;

import gamedata.compositiongen.Data;

public interface OptionPickerDelegate {
	public abstract void didPickOptionWithData(String dataName);
	public abstract void didClickClose();
}
