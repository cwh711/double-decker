package double_decker.view.listener;

import double_decker.model.Card;
import double_decker.model.GameModel;
import double_decker.model.dnd.CardDataFlavor;
import double_decker.view.ObjectivePilePanel;
import double_decker.view.RootPanel;

import java.awt.*;
import java.awt.dnd.*;

public class DropHandler implements DropTargetListener {

    private final RootPanel root;

    public DropHandler(RootPanel root) {
        this.root = root;
    }
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(CardDataFlavor.INSTANCE)) {
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        } else {
            dtde.rejectDrag();
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {}

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {}

    @Override
    public void dragExit(DropTargetEvent dte) {}

    @Override
    public void drop(DropTargetDropEvent dtde) {
        boolean success = false;
        try {
            if (dtde.isDataFlavorSupported(CardDataFlavor.INSTANCE)) {
                Object payload = dtde.getTransferable().getTransferData(CardDataFlavor.INSTANCE);
                if (payload instanceof Card) {
                    Card card = (Card) payload;
                    DropTargetContext dtc = dtde.getDropTargetContext();
                    Component component = dtc.getComponent();
                    if (component instanceof ObjectivePilePanel) {
                        ObjectivePilePanel toPanel = (ObjectivePilePanel) component;
                        if (toPanel.addCardToPile(card)) {
                            success = true;
                            dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                            toPanel.updateImage();
                            root.incrementScore(5);
                        } else {
                            dtde.rejectDrop();
                        }
                    } else {
                        dtde.rejectDrop();
                    }
                } else {
                    dtde.rejectDrop();
                }
            } else {
                dtde.rejectDrop();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
        dtde.dropComplete(success);
    }
}
