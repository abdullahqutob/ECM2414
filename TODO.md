# TODO

## Remember/New things
- Test functions should have a `throws` statement that passes all errors that they are not explicitly testing for
- Test functions should now throw `AssertError` and use the functions `assertEqual` and `assertNotEqual` instead of the `assert` keyword
- Test Classes should be named in descending order of significance e.g. `testCreateCard`, `testInvalidDeck` etc. Test methods can be named however you'd like
- When you throw an error in the `CardGame` function it should contain a message explaining why it happened e.g. `throw new InvalidPackException("Less than 8n cards in pack")`
- Test return for empty arrays. Not null.

## Deck

- [x] Queue Data Structure
  - add to back
  - take from front
- [x] Thread safe if deck is empty
- [x] Create text file
- [x] Write contents of deck to file 
  ### Testing
    - [x] Test insertion and removal of card
    - [ ] Test creation and writing to text file
    - [ ] test thread safe-ing if deck is empty


## Player

- [x] Check if game is won
- [x] Grab card
- [x] Discard card
- [x] create text file
- [x] write to text file
    ### Testing
  - [ ] test game winning
  - [ ] test grabbing and discarding card
  - [ ] Test creation and writing to text file


## CardGame

- [ ] initialise player threads
- [x] Distribute Cards to players in round-robin
- [x] Distribute Cards to decks in round-robin
- [ ] start game

  ### Testing
    
    - [ ] testing handing out cards to players and deck
    - [ ] testing complete game cycle

## Card

- [x] Create Card
- [x] Get card value
  ### Testing
    - [x] Testing creation of card and its value
    

## Pack
- [x] Read pack file
- [ ] Validate pack file
- [x] add pack file ints to arraylist
  ### Testing
  - [ ] testing deck validation
    - deck too small/big
    - deck doesnt contain 4n duplicates
    - deck contains strings