# Nobel Rock-Paper-Scissors

Nobel Rock-Paper-Scissors is a web-based Rock-Paper-Scissors game that incorporates advanced logic to predict the player's next move. The game is controlled and monitored through RESTful API calls, offering features like game creation, move submission, and statistics retrieval.

## API Endpoints

### Start a New Game

- **Path**: `/api/game/start?name={gameName}`
- **Method**: `POST`
- **Description**: Initializes a new game with the specified `gameName`. This game ID will be used in subsequent API calls to manage the game.
- **Request Body**: None
- **Response Example**:

```json
{
    "id": 1,
    "createdAt": "2024-09-08T20:56:37.2801332",
    "updatedAt": null,
    "rounds": null,
    "active": true
}
```

### Make a move

- **Path**: SERVER_URL/api/game/{gameId}/move
- **Type**: POST
- **Description**: Used for creating a making a move in the game, you can only use one of the three valid moves at the time in request body "ROCK", "PAPER" or "SCISSORS"
- **Request body**: "ROCK"
- **Response Example**:
```json
  {
    "id": 1,
    "playerMove": "ROCK",
    "aiMove": "ROCK",
    "playerResult": "DRAW",
    "createdAt": "2024-09-08T20:56:37.2801332"
}
```

### End a game

- **Path**: SERVER_URL/api/game/{gameId}/end
- **Type**: POST
- **Description**: Used for ending a specific game and displays the statistics of that game
- **Request body**: None
- **Response Example**:
```json
{
    "aiWins": 0,
    "draws": 1,
    "gameId": 1,
    "roundsPlayed": 1,
    "playerWins": 0
  }
```
### Statistics of a game
- **Path**: SERVER_URL/api/stats/{gameId}
- **Type**: GET
- **Description**: Used for displaying the statistics of a certain game
- **Request body**: None
- **Response Example**:
```json
{
    "aiWins": 0,
    "draws": 1,
    "gameId": 1,
    "roundsPlayed": 1,
    "playerWins": 0
}
```
### Statistics of all games
- **Path**: SERVER_URL/api/stats/{gameId}
- **Type**: GET
- **Description**: Used for displaying the statistics of every game played
- **Request body**: None
- **Response Example**:
```json
 [
    {
        "gameName": "example",
        "roundsPlayed": 8,
        "playerWins": 5,
        "aiWins": 2,
        "gameId": 1,
        "draws": 1
    },
    {
        "gameName": "example number two",
        "roundsPlayed": 1,
        "playerWins": 0,
        "aiWins": 1,
        "gameId": 2,
        "draws": 0
    }
]
```
