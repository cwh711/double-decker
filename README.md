# Double-Decker Solitaire Application

### Rules
Game is played with two decks of 52 standard playing cards, shuffled together.
###### Board consists of:
* 8 objective piles at the top (empty at the start of the game)
* 13 face-up piles corresponding to card values (A-K), arranged in 2 rows:
  * A-7 left-to-right in the top row.
  * 8-K left-to-right in the bottom row, with a gap between 10 & J.
* 1 Face-down draw pile, positioned between 10 & J piles.  
**Note:** Numbers of cards in face-up piles and draw pile will vary based on setup rules (knowledge not required to play this app, but detailed below for curiosity or tabletop play)

###### How to Play:
* Build onto objective piles: 4 up by suit on top of Aces, and 4 down by suit from Kings.
* Draw from (click) the face-down pile to pick up the face-up pile corresponding to the drawn card.
* Only cards on the top of face-up piles and cards "in-hand" can be moved, and cards can only be moved onto the objective piles.
* Only Aces and Kings can begin a new objective pile, and only one of each per suit.
* Try to play as many cards onto the objective piles

#### Legal
* Card graphics are public-domain, sourced from [this site](https://tekeye.uk/playing_cards/svg-playing-cards).

### Setup Procedure
Deal cards onto face-up piles in order (**A** up to **K**, repeating), noting which pile is being dealt to, until the 
deck is empty.
* If the dealt card matches the pile, the next card is placed face-down on top of the draw pile.
* If the card dealt is an ace, the next two cards are placed face-down on top of the draw pile.
* A card is placed on the draw pile after a card is dealt to the **10** pile (i.e. as the dealer passes by the draw pile),
and after a card is dealt to the **K** pile (before starting over at **A**).

Any of the above rules can occur at the same time. E.g:
* An ace dealt to the **10** pile will add 3 cards to the draw pile - two from an ace being dealt, and one
from "passing by" the draw pile after the **10** pile.
* A **K** dealt to the **K** pile will add 2 cards to the draw pile - one placed after dealing to the **K** pile, and
one from the card matching the pile.