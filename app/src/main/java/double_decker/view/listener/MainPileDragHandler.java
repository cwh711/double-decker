package double_decker.view.listener;

import double_decker.model.Card;
import double_decker.model.MainPile;
import double_decker.model.dnd.TransferableCard;
import double_decker.view.MainPilePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.IOException;
import java.net.URL;

public class MainPileDragHandler implements DragGestureListener, DragSourceListener {

    private final MainPilePanel fromPanel;
    private final Point dragImageOffset = new Point(-42,-60);

    public MainPileDragHandler(MainPilePanel fromPanel) {
        this.fromPanel = fromPanel;
    }

    public MainPilePanel getFromPanel() {
        return fromPanel;
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        MainPile fromPile = fromPanel.getPile();
        Card draggedCard = fromPile.popCard();

        Image image = null;
        String fileName = draggedCard.getFrontImageFileName();
        try {
            URL imgURL = getClass().getClassLoader().getResource(fileName);
            if (imgURL != null) {
                image = ImageIO.read(imgURL);
            } else {
                System.out.println("Image <" + fileName + "> not found.");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        fromPanel.updateImage();

        TransferableCard transferable = new TransferableCard(draggedCard, fromPile);

        dge.getDragSource().startDrag(dge, Cursor.getDefaultCursor(), image, dragImageOffset, transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {}

    @Override
    public void dragOver(DragSourceDragEvent dsde) {}

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {}

    @Override
    public void dragExit(DragSourceEvent dse) {}

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        if (!dsde.getDropSuccess()) {
            Transferable t = dsde.getDragSourceContext().getTransferable();
            if (t instanceof TransferableCard) {
                TransferableCard tc = (TransferableCard) t;
                tc.getFromPile().placeCard(tc.getCard());
                fromPanel.updateImage();
            }
        }
    }
}
