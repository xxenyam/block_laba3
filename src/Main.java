import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        Node miner = new Node(blockchain);
        Scanner scanner = new Scanner(System.in);

        // Створення кількох блоків
        for (int i = 0; i < 3; i++) { // Змінюйте число для створення більшої кількості блоків
            System.out.println("Додаємо транзакції для блоку " + (i + 1) + ":");
            // Додаємо транзакції
            blockchain.addTransaction(new Transaction("user " + (i + 1), "user " + (i + 2), 50));
            blockchain.addTransaction(new Transaction("user " + (i + 2), "user " + (i + 3), 25));

            // Майнінг блоку
            miner.mineBlock();
            System.out.println("Блок " + (i + 1) + " замайнено!\n");
        }

        // Перевірка блокчейну
        System.out.println("Чи дійсний блокчейн: " + blockchain.isChainValid());

        // Виведення всіх блоків у блокчейні
        System.out.println("Всі блоки в блокчейні:");
        blockchain.printBlockchain();
    }
}






