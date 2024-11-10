public class Main {
    public static void main(String[] args) {
        int philosopherCount = 5;
        int meals = 3;
        Fork[] forks = new Fork[philosopherCount];
        Philosopher[] philosophers = new Philosopher[philosopherCount];

        for (int i = 0; i < philosopherCount; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < philosopherCount; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % philosopherCount];
            philosophers[i] = new Philosopher(i + 1, leftFork, rightFork, meals);
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                System.out.println("Главный поток прерван.");
            }
        }

        System.out.println("Все философы завершили обед.");
    }
}