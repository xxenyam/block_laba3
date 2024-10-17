import java.security.MessageDigest;

public class Block {
    private String previousHash;
    private String hash;
    private Transaction[] transactions;
    private long nonce;

    public Block(String previousHash, Transaction[] transactions) {
        this.previousHash = previousHash;
        this.transactions = transactions;
        this.nonce = 0;
        this.hash = calculateHash(); // Обчислюємо хеш при створенні блоку
    }

    public String calculateHash() {
        StringBuilder dataToHash = new StringBuilder();
        dataToHash.append(previousHash);
        dataToHash.append(Long.toString(nonce));
        for (Transaction transaction : transactions) {
            dataToHash.append(transaction.toString());
        }
        return applySha256(dataToHash.toString());
    }

    public void incrementNonce() {
        nonce++;
        hash = calculateHash(); // Перераховуємо хеш при кожному збільшенні nonce
    }

    public long getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder blockString = new StringBuilder();
        blockString.append("Block{\n")
                .append("  previousHash='").append(previousHash).append("',\n")
                .append("  hash='").append(hash).append("',\n")
                .append("  nonce=").append(nonce).append(",\n")
                .append("  transactions=[\n");

        for (Transaction transaction : transactions) {
            blockString.append("    ").append(transaction.toString()).append(",\n");
        }

        blockString.append("  ],\n")
                .append("  numberOfTransactions=").append(transactions.length).append("\n")
                .append("}");
        return blockString.toString();
    }
}








