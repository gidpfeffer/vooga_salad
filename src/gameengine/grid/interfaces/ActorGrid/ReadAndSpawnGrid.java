package gameengine.grid.interfaces.ActorGrid;

import java.util.function.Consumer;

import gameengine.actors.propertygen.IActProperty;

public interface ReadAndSpawnGrid extends ReadableGrid{

	Consumer<IActProperty<MasterGrid>> actorSpawnActor(Integer actorType, double startX, double startY);

}
