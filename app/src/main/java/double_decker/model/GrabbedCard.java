package double_decker.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.Objects;

public class GrabbedCard implements Transferable {

    private final Card card;
    private final Pile fromPile;

    private static final DataFlavor[] dataFlavors = new DataFlavor[]{
            new DataFlavor(GrabbedCard.class, "grabbedCard"),
            new DataFlavor(Card.class, "card"),
            new DataFlavor(Pile.class, "pile")
    };

    public GrabbedCard(Card card, Pile from) {
        this.card = card;
        this.fromPile = from;
    }

    public Card getCard() {
        return card;
    }

    public Pile getFromPile() {
        return fromPile;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return dataFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (DataFlavor df : dataFlavors) {
            if (df.equals(flavor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (isDataFlavorSupported(flavor)) {
            return card;
        }
        throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrabbedCard that = (GrabbedCard) o;
        return card.equals(that.card) && fromPile.equals(that.fromPile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card, fromPile);
    }
}
