package uno.player;

import uno.cards.Action_Card;
import uno.cards.Face_Card;
import uno.cards.Hand;
import uno.cards.UNO_Card;
import uno.uno_game.Game_Engine;

import java.util.Iterator;

public class Player_PaulP extends Player{
    /**
     * This method constructs s a new player object.  The player
     * object constructed has the specified hand of cards and
     * a reference to the dealer who dealt the hand.
     *
     * @param hand   The hand of UNO_Cards.
     * @param dealer A reference to the Game_Engine object that is controlling
     *               the game play.
     */
    public Player_PaulP(Hand hand, Game_Engine dealer) {
        super(hand, dealer);
    }

    /**
     * This method plays your strategy using the hand that you have.
     * You can play a card, draw, and pass.
     *
     * @param card The card at the top of the discard pile.
     * @param useColorInstead checks whether the color of card is checked.
     * @return number of cards left in hand.
     */
    @Override
    public int play(UNO_Card card, boolean useColorInstead) {
        // Do I draw a card?
        if (dealer.doIDraw()) {
            // Is it a Wild card?
            if (card.getColor() == UNO_Card.BLACK) {
                // Is it a Draw 4?
                if (((Action_Card) card).getAction() == Action_Card.WILD_DRAW_4) {
                    // Is a Wild Draw 4.
                    // Draws 4 cards.
                    for (int i = 0; i < 4; i++) {
                        UNO_Card c1 = dealer.drawCard();
                        hand.addCard(c1);
                        dealer.notifyPass(this);
                    }
                }
            }
            // Is it a Draw 2?
            else if (((Action_Card) card).getAction() == Action_Card.DRAW_2) {
                // Is a Draw 2.
                // Draws 2 cards.
                for (int i = 0; i < 2; i++) {
                    UNO_Card c1 = dealer.drawCard();
                    hand.addCard(c1);
                    dealer.notifyPass(this);
                }
            }
        }

        // I don't draw
        else {
            cardIterate(card);
        }
        // Do I have UNO?
        if (hand.numberOfCards() == 1) {
            dealer.notifyUNO(this);
        }
        // Did I win?
        else if (hand.numberOfCards() == 0) {
            dealer.notifyWon(this);
        }
        return hand.numberOfCards();
    }

    /**
     * This method returns your name.
     *
     * @return The name of the player
     */
    @Override
    public String whoAreYou() {
        return "PaulP";
    }

    /**
     * This method returns the score of your cards when another player wins a hand.
     * Face cards = The number on it
     * Action cards = 20
     * Wild cards = 50
     *
     * @return The score of your hand based on your cards
     */
    @Override
    public int scoreOnCards() {
        int score = 0;
        Iterator<UNO_Card> it = hand.iterator();
        UNO_Card myCard = it.next();
        for (int i = 0; i < hand.numberOfCards(); i++) {
            // Wild cards
            if (myCard.getColor() == UNO_Card.BLACK) {
                score = score + 50;
            }
            // Action cards
            else if (myCard instanceof Action_Card) {
                score = score + 20;
            }
            // Face cards
            else {
                score = score + ((Face_Card) myCard).getFaceNumber();
            }
            // Checks to make sure that it doesn't extend past hte boundaries.
            if (it.hasNext()) {
                myCard = it.next();
            }
        }
        return score;
    }

    /**
     * Chooses a card in your hand based on the card on the top of the discard pile. If there
     * isn't a card you draw one. If that card can't be played, you pass.
     *
     * @param card Card on top of the discard pile.
     */
    public void cardIterate(UNO_Card card) {
        // Check for card in hand with correct color
        Iterator<UNO_Card> it = hand.iterator();
        UNO_Card myCard = it.next();
        boolean stopSearch = false;
        do {
            // Check cards based on color
            if (myCard.getColor() == card.getColor() || myCard.getColor() == dealer.getCurrent_Color()){
                playCard(myCard);
                stopSearch = true;
            }
            // Check cards based on action
            else if (myCard instanceof Action_Card && card instanceof Action_Card) {
                if ( ((Action_Card) myCard).getAction() == ((Action_Card) card).getAction()) {
                    playCard(myCard);
                    stopSearch = true;
                }
            }
            // Check cards based on number
            else if (myCard instanceof Face_Card && card instanceof Face_Card
                    && ((Face_Card) myCard).getFaceNumber() == ((Face_Card) card).getFaceNumber()) {
                playCard(myCard);
                stopSearch = true;
            }
            // Checks to make sure that it doesn't extend past hte boundaries.
            if (it.hasNext()) {
                myCard = it.next();
            }
        }
        while (it.hasNext() && !stopSearch);

        // If no card is found.
        if (!stopSearch) {
            UNO_Card c1 = dealer.drawCard();
            hand.addCard(c1);
            if (myCard.getColor() == dealer.getCurrent_Color()){
                playCard(myCard);
            }
            dealer.notifyPass(this);
        }
    }

    /**
     * Plays the card that is chosen in the cardIterate method. Also chooses
     * a color if the card played is wild.
     *
     * @param myCard The card chosen by cardIterate.
     */
    public void playCard(UNO_Card myCard) {
        dealer.discardCard(myCard);
        hand.remove(myCard);

        // Chooses a color if the card is wild.
        if (hand.numberOfCards() != 0 && myCard.getColor() == UNO_Card.BLACK) {
            if (chooseColor() == 1) {
                dealer.setPlayColor(UNO_Card.RED);
            }
            else if (chooseColor() == 2) {
                dealer.setPlayColor(UNO_Card.BLUE);
            }
            else if (chooseColor() == 3) {
                dealer.setPlayColor(UNO_Card.GREEN);
            }
            else if (chooseColor() == 4) {
                dealer.setPlayColor(UNO_Card.YELLOW);
            }
            else {
                dealer.setPlayColor(UNO_Card.BLUE);
            }
        }
        else {
            dealer.setPlayColor(UNO_Card.RED);
        }
    }

    /**
     * Chooses a color based on the number of cards each color has in your hand.
     *
     * @return 0-4 depending on which color has the most cards.
     */
    public int chooseColor() {
        // Count cards based on color.
        Iterator<UNO_Card> it = hand.iterator();
        UNO_Card myCard = it.next();
        int r = 0;
        int b = 0;
        int g = 0;
        int y = 0;
        for (int i = 0; i < hand.numberOfCards(); i++) {
            if (myCard.getColor() == UNO_Card.RED) {
                r++;
            }
            if (myCard.getColor() == UNO_Card.BLUE) {
                b++;
            }
            if (myCard.getColor() == UNO_Card.GREEN) {
                g++;
            }
            if (myCard.getColor() == UNO_Card.YELLOW) {
                y++;
            }
            if (it.hasNext()) {
                myCard = it.next();
            }
        }
        // Red Majority
        if (r < b && r < g && r < y) {
            return 1;
        }
        // Blue Majority
        if (b < r && b < g && b < y) {
            return 2;
        }
        // Green Majority
        if (g < b && g < r && g < y) {
            return 3;
        }
        // Yellow Majority
        if (y < b && y < g && y < r) {
            return 4;
        }
        return 0;
    }
}
