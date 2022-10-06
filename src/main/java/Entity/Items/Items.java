package Entity.Items;

import Entity.Entity;
import javafx.scene.image.Image;

public abstract class Items extends Entity {
    protected boolean received = false;

    public Items(int x, int y, Image image) {
        super(x, y, image);
    }

    public Items() {

    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    @Override
    public void update() {

    }
}
