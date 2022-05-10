package double_decker.model.dnd;

import double_decker.model.Card;
import double_decker.model.MainPile;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.Objects;

public class TransferableCard implements Transferable {

    private final Card card;
    private final MainPile fromPile;

    private static final DataFlavor[] dataFlavors = new DataFlavor[]{CardDataFlavor.INSTANCE};

    public TransferableCard(Card card, MainPile from) {
        this.card = card;
        this.fromPile = from;
    }

    public Card getCard() {
        return card;
    }

    public MainPile getFromPile() {
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
        TransferableCard that = (TransferableCard) o;
        return card.equals(that.card) && fromPile.equals(that.fromPile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card, fromPile);
    }
}
