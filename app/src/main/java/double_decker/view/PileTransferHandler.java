package double_decker.view;

import double_decker.model.MainPile;
import double_decker.model.dnd.TransferableCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.net.URL;

public class PileTransferHandler extends TransferHandler {

    /**
     * Returns the type of transfer actions supported by the source;
     * any bitwise-OR combination of {@code COPY}, {@code MOVE}
     * and {@code LINK}.
     * <p>
     * Some models are not mutable, so a transfer operation of {@code MOVE}
     * should not be advertised in that case. Returning {@code NONE}
     * disables transfers from the component.
     *
     * @param c the component holding the data to be transferred;
     *          provided to enable sharing of <code>TransferHandler</code>s
     * @return {@code COPY} if the transfer property can be found,
     * otherwise returns <code>NONE</code>
     */
    @Override
    public int getSourceActions(JComponent c) {
        System.out.println("Checking getSourceActions on a " + c.getClass());
        if (c instanceof ObjectivePilePanel || c instanceof DrawPilePanel) {
            return NONE;
        } else if (c instanceof MainPilePanel || c instanceof CardPanel) {
            return MOVE;
        }
        return super.getSourceActions(c);
    }

    /**
     * Creates a <code>Transferable</code> to use as the source for
     * a data transfer. Returns the representation of the data to
     * be transferred, or <code>null</code> if the component's
     * property is <code>null</code>
     *
     * @param c the component holding the data to be transferred;
     *          provided to enable sharing of <code>TransferHandler</code>s
     * @return the representation of the data to be transferred, or
     * <code>null</code> if the property associated with <code>c</code>
     * is <code>null</code>
     */
    @Override
    protected Transferable createTransferable(JComponent c) {
        System.out.println("Attempting to createTransferable from a " + c.getClass());
        if (c instanceof MainPilePanel) {
            MainPilePanel m = (MainPilePanel) c;
            MainPile p = m.getPile();
            Transferable t = new TransferableCard(p.popCard(), p);
            m.updateImage();
            return t;
        } else if (c instanceof CardPanel) {
            CardPanel p = (CardPanel) c;
            return new TransferableCard(p.getCard(), null);
        } else {
            return super.createTransferable(c);
        }
    }

    @Override
    public Icon getVisualRepresentation(Transferable t) {
        if (t instanceof TransferableCard) {
            TransferableCard tCard = (TransferableCard) t;
            try {
                String fileName = tCard.getCard().getFrontImageFileName();
                URL imgURL = getClass().getClassLoader().getResource(fileName);
                if (imgURL != null) {
                    return new ImageIcon(ImageIO.read(imgURL));
                } else {
                    System.out.println("Image <" + fileName + "> not found.");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return super.getVisualRepresentation(t);
    }

    @Override
    public boolean importData(TransferSupport support) {
        Transferable t = support.getTransferable();
        Component c = support.getComponent();
        if (t instanceof TransferableCard) {
            TransferableCard obj = (TransferableCard) t;
            if (c instanceof ObjectivePilePanel) {
                ObjectivePilePanel target = (ObjectivePilePanel) c;
                if (target.addCardToPile(obj.getCard())) {
                    target.updateImage();
                    return true;
                }
            }
            // if we didn't return yet, it either wasn't on an objective pile,
            // or the card couldn't be added to the pile. Either way, put it back
            MainPile fromPile = obj.getFromPile();
            fromPile.placeCard(obj.getCard());

            /*
                Find the Panel associated with the fromPile, so we can redraw it.
                Target component is either the RootPanel, one of its children
                (ObjectivePilePanel, MainPilePanel, DrawPilePanel, or HandPanel)
                or a CardPanel that is a child of the HandPanel.
             */
            RootPanel root;
            if (c instanceof RootPanel) {
                root = (RootPanel) c;
            } else {
                Container cont = c.getParent();
                if (cont instanceof HandPanel) {
                    cont = cont.getParent();
                }
                if (cont instanceof RootPanel) {
                    root = (RootPanel) cont;
                } else {
                    //we should never get here
                    return false;
                }
            }
            root.getMainPilePanel(fromPile.getPileValue()).updateImage();
            return true;
        }
        return false;
    }
}
