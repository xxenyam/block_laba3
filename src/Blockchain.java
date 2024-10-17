import java.util.ArrayList;

public class Blockchain {
    private ArrayList<Block> chain;
    private ArrayList<Transaction> pendingTransactions;
    private int difficulty;

    public Blockchain() {
        chain = new ArrayList<>();
        pendingTransactions = new ArrayList<>();
        chain.add(createGenesisBlock());
        this.difficulty = 4; // Визначити рівень складності
    }

    private Block createGenesisBlock() {
        return new Block("0", new Transaction[]{});
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block block) {
        chain.add(block);
        pendingTransactions.clear(); // Очищення черги транзакцій після майнінгу блоку
    }

    public void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Transaction[] getPendingTransactions() {
        return pendingTransactions.toArray(new Transaction[0]);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Хеш поточного блоку недійсний");
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("Хеш попереднього блоку недійсний");
                return false;
            }
        }
        return true;
    }

    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println(block); // Виводимо інформацію про блок
            System.out.println("-------------------------------------------------"); // Додатковий розділювач
        }
    }
}


