Nobel Rock-Paper-Scissors is a rock-paper-scissors game based on webserver that uses additional under the hood logic to predict Player's next move.
It relies on API calls to run and control the game, as well as to view statistics

API Examples:

Path: SERVER_URL/api/game/start?name=name_example
Type: POST
Description: Used for creating a new game with a specific id, this ID is used in other APIs to control the game, end it, or view the statistics of it.
Request body: None
Response Example: {
    "id": 1,
    "createdAt": 2024-09-08T20:56:37.2801332,
    "updatedAt": null,
    "rounds": null,
    "active": true
}

Path: SERVER_URL/api/game/{gameId}/move
Type: POST
Description: Used for creating a making a move in the game, you can only use one of the three valid moves at the time in request body "ROCK", "PAPER" or "SCISSORS"
Request body: "ROCK"
Response Example: {
    "id": 1,
    "playerMove": "ROCK",
    "aiMove": "ROCK",
    "playerResult": "DRAW",
    "createdAt": "2024-09-08T20:56:37.2801332"
}

Path: SERVER_URL/api/game/{gameId}/end
Type: POST
Description: Used for ending a specific game and displays the statistics of that game
Request body: None
Response Example: {
    "aiWins": 0,
    "draws": 1,
    "gameId": 1,
    "roundsPlayed": 1,
    "playerWins": 0
}

Path: SERVER_URL/api/stats/{gameId}
Type: GET
Description: Used for displaying the statistics of a certain game
Request body: None
Response Example: {
    "aiWins": 0,
    "draws": 1,
    "gameId": 1,
    "roundsPlayed": 1,
    "playerWins": 0
}

Path: SERVER_URL/api/stats/{gameId}
Type: GET
Description: Used for displaying the statistics of every game played
Request body: None
Response Example: [
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
