package ui.player.login;

import java.util.Map;
import javafx.scene.text.Text;

public class GenericGrid extends DataEntryGrid{

		private Map<Text, String> entryMap;
		
		public Map<Text, String> getEntryMap() {
			return entryMap;
		}
		
		public GenericGrid(Map<Text, String> map){
			super(map);
			this.entryMap = map;
			addToGrid(entryMap);
		}
		
		@Override
		public void addValues(){
			
		}

	}

