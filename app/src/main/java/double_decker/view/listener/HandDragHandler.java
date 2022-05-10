package double_decker.view.listener;

import double_decker.model.Card;
import double_decker.model.dnd.TransferableCard;
import double_decker.view.CardPanel;
import double_decker.view.HandPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.dnd.*;
import java.io.IOException;
import java.net.URL;

public class HandDragHandler implements DragGestureListener, DragSourceListener {

    private int fromIndex = -1;
    private final HandPanel handPanel;
    private final Card card;
    private final Point dragImageOffset = new Point(-42,-60);

    public HandDragHandler(HandPanel handPanel, CardPanel cardPanel) {
        this.handPanel = handPanel;
        this.card = cardPanel.getCard();
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {

            Card draggedCard = card;
            fromIndex = handPanel.removeCard(draggedCard);

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

            TransferableCard transferable = new TransferableCard(draggedCard, null);

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
        if (!dsde.getDropSuccess() && fromIndex >= 0) {
            handPanel.returnCard(fromIndex, card);
        }
        handPanel.updateCards();
    }
}
