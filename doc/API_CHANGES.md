# API Changes

## Gideon Pfeffer (gap16)

### ActorGrid 

| Interface         | Classes That Implement It | Methods                                                                                                                                                                                                                                                                                                       |
|-------------------|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ReadableGrid      | ActorGrid                 | Collection< Grid2D> getActorLocationsInRadius(double x, double y, double radius, BasicActorType type);  <br><br> Grid2D getLocationOf(int id); <br><br> Collection< Grid2D> getActorLocations(BasicActorType type); <br><br> boolean isValidLoc(double x, double y); <br><br> double getMaxX(); <br><br> double getMaxY(); <br><br> ListenQueue getEventQueue(); |
| ReadAndMoveGrid   | ActorGrid                 | extends ReadableGrid <br><br> void move(int ID, double newX, double newY);                                                                                                                                                                                                                                            |
| ReadAndSpawnGrid  | ActorGrid                 | extends ReadableGrid <br><br> void actorSpawnActor(Integer actorType, double startX, double startY, Consumer< Collection< IActProperty< MasterGrid>>> action);                                                                                                                                                                                               |
| ReadAndDamageGrid | ActorGrid                 | extends ReadableGrid <br><br> Map< Consumer< Double>, Double>getActorDamagablesInRadius(double x, double y,,double radius, BasicActorType type); <br><br> Consumer getMyDamageable(int actorID); <br><br> WriteableGameStatus getWriteableGameStatus();                                                                                                    |
| ReadShootMoveGrid | ActorGrid                 | extends ReadAndSpawnGrid, ReadAndMoveGrid                                                                                                                                                                                                                                               |
| MasterGrid        | ActorGrid                 | extends ReadShootMoveGrid, ReadAndDamageGrid                                                                                                                                                                                                                                            |

### ControllerGrid

| Interface        | Classes That Implement It | Methods                                                                                                                                                                                                                                 |
|------------------|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Steppable        | ActorGrid                 | void step();                                                                                                                                                                                                                            |
| ControllableGrid | ActorGrid                 | extends Steppable(); <br><br> void move(int ID, double newX, double newY); <br><br> void removeActor(int ID); <br><br> void controllerSpawnActor(Actor actor, double startX, double startY); <br><br> Grid2D getLocationOf(int ID); <br><br> boolean isValidLoc(double x, double y); |

### Grid Utilities and Handlers

| Interface            | Classes That Implement It | Methods                                                                          |
|----------------------|---------------------------|----------------------------------------------------------------------------------|
| GridHandler          | See GameController        | WriteableGameStatus getWriteableGameStatus(); <br><br> ListenQueue getEventQueue();    |
| FrontEndInformation  | DisplayInfo               | Grid2D getActorLocation(); <br><br> double getActorPercentHealth(); <br><br> int getActorOption(); |
| ActorLocator         | ActorFinder               | Actor getActor(); <br><br> Grid2D getLocation();                                          |
| SettableActorLocator | ActorFinder               | extends ActorLocator <br><br> void setLocation(double x, double y);                       |
| Grid2D               | Coordinates               | double getY(); <br><br> double getX();                                                    |