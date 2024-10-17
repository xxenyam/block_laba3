public class Node {
    private Blockchain blockchain;

    public Node(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public void mineBlock() {
        Block newBlock = new Block(blockchain.getLastBlock().getHash(), blockchain.getPendingTransactions());
        String target = new String(new char[blockchain.getDifficulty()]).replace('\0', '0'); // Ціль на основі складності

        System.out.println("Майнінг блоку...");
        while (!newBlock.getHash().substring(0, blockchain.getDifficulty()).equals(target)) {
            newBlock.incrementNonce(); // Збільшуємо nonce
            newBlock.calculateHash(); // Перераховуємо хеш
        }

        System.out.println("Блок замайнено! Хеш: " + newBlock.getHash());
        blockchain.addBlock(newBlock);
    }
}

