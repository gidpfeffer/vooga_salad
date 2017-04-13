package ui.authoring.actor;

import gamedata.compositiongen.Data;

public interface DataViewDelegate {
	public abstract void setData(Data oldData, Data newData);
	public abstract void didClickDelete(DataView dataView);
}
