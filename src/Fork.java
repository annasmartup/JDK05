public class Fork {
    private boolean takenFork = false;

    public synchronized boolean pickUp() {
        if (!takenFork) {
            takenFork = true;
            return true;
        }
        return false;
    }

    public synchronized void putDown() {
        takenFork = false;
    }
}
