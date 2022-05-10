package double_decker.model.dnd;

import double_decker.model.Card;

import java.awt.datatransfer.DataFlavor;

public class CardDataFlavor extends DataFlavor {

    public static final DataFlavor INSTANCE = new CardDataFlavor();

    public CardDataFlavor() {
        super(Card.class, "Card");
    }
}
