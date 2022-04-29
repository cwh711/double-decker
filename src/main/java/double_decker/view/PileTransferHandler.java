package double_decker.view;

import javax.swing.*;
import java.awt.datatransfer.Transferable;

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
        if (c instanceof ObjectivePilePanel || c instanceof DrawPilePanel) {
            return NONE;
        } else if (c instanceof MainPilePanel) {
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
        if (c instanceof MainPilePanel) {
            return ((MainPilePanel) c).getPile().popCard();
        } else {
            return super.createTransferable(c);
        }
    }
}
