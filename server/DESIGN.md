# Design
## Structures
### Game State
Record of:
* Player states
* Bank state (if we do limited resources)
* Configuration

### Board
Array of 40 locations with their state.

### Location
Enum of:
* Property
* Train Station
* Utility Company
* Chance
* Community Chest
* Jail
* Go-to-Jail
* Free Parking
* Go

### Property
Record of:
* Relevant colour
* Building state

### Building
Enum of:
* None
* Houses
* Hotel

### Player
Record of:
* Money
* Properties owned (does clojure do references like this?)
* Position on the board
