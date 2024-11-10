public class Philosopher extends Thread {
    private int id;
    private Fork leftFork;
    private Fork rightFork;
    private int mealsLeft;
    private String state;

    public Philosopher(int id, Fork leftFork, Fork rightFork, int meals) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.mealsLeft = meals;
        this.state = "размышляет";
    }

    @Override
    public void run() {
        while (mealsLeft > 0) {
            try {
                think();
                if (eat()) {
                    mealsLeft--;
                }
            } catch (InterruptedException e) {
                System.out.println("Философ " + id + " прерван!");
            }
        }
        System.out.println("Философ " + id + " наелся.");
    }

    private void think() throws InterruptedException {
        state = "размышляет";
        System.out.println("Философ " + id + " -> " + state);
        Thread.sleep(150);
    }

    private boolean eat() throws InterruptedException {
        if (leftFork.pickUp()) {
            if (rightFork.pickUp()) {
                state = "ест";
                System.out.println("Философ " + id + " -> " + state + " (осталось приемов пищи: " + (mealsLeft - 1) + ")");
                Thread.sleep(200);

                rightFork.putDown();
                leftFork.putDown();
                return true;
            } else {
                leftFork.putDown();
            }
        }
        return false;
    }
}