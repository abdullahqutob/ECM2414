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
- [ ] Thread safe if deck is empty
- [ ] Create text file
- [ ] Write contents of deck to file 
  ### Testing
    - [x] Test insertion and removal of card
    - [ ] Test creation and writing to text file
    - [ ] test thread safe-ing if deck is empty


## Player

- [ ] Check if game is won
- [ ] Grab card
- [ ] Discard card
- [ ] create text file
- [ ] write to text file
    ### Testing
  - [ ] test game winning
  - [ ] test grabbing and discarding card
  - [ ] Test creation and writing to text file


## CardGame

- [ ] check if deck is valid
- [ ] initialise player threads
- [ ] Distribute Cards to players in round-robin
- [ ] Distribute Cards to decks in round-robin
- [ ] start game

  ### Testing
    - [ ] testing deck validation
      - invalid and valid decks
        - deck too small
        - deck doesnt contain 4n duplicates
    - [ ] testing handing out cards to players and deck\
    - [ ] testing complete game cycle

## Card

- [x] Create Card
- [x] Get card value
  ### Testing
    - [x] Testing creation of card and its value
    