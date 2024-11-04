public class Ship {

    private int size;
    private boolean isSunk;
    private int hitCount;

    public Ship(int size) {
        this.size = size;
        this.isSunk = false;
        this.hitCount = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void hit() {
        hitCount++;
        if(hitCount >= size) {
            isSunk = true;
        }
    }
}
