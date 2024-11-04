public class Cell {

    private String display;
    private boolean hasShip;

    public Cell(){
        this.display = "";
        this.hasShip = false;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void placeShip() {
        hasShip = true;
    }
}
