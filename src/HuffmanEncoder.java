import java.io.FileWriter;
import java.io.IOException;

public class HuffmanEncoder {
    private String inputFileName = "WarAndPeace.txt";
    private String outputFileName = "WarAndPeace-compressed.bin";
    private String codesFileName = "WarAndPeace-codes.txt";
    private BookReader book = new BookReader(inputFileName);
    private MyOrderedList<FrequencyNode>;
    private HuffmanNode huffmanTree;
    private MyOrderedList<CodeNode> codes;
    private byte[] encodedText;

    public HuffmanEncoder() throws IOException {
        countFrequency();
        buildTree();
        extractCodes(HuffanNode, codes);
        encode();
        writeFile();
    }
    private void countFrequency() {
        while (book.hasNext()) {
            String word = book.next();
            if (word.length() > 0) {
                if (frequency.contains(word)) {
                    FrequencyNode node = frequency.get(word);
                    node.increment();
                } else {
                    FrequencyNode node = new FrequencyNode(word);
                    frequency.add(node);
                }
            }
        }
    }
    private void buildTree() {
        while (frequency.size() > 1) {
            HuffmanNode left = frequency.removeFirst();
            HuffmanNode right = frequency.removeFirst();
            HuffmanNode parent = new HuffmanNode(left, right);
            frequency.add(parent);
        }
        huffmanTree = frequency.removeFirst();
    }
    private void extractCodes(HuffmanNode node, MyOrderedList<CodeNode> codes) {
        if (node.isLeaf()) {
            CodeNode code = new CodeNode(node.getValue(), "");
            codes.add(code);
        } else {
            extractCodes(node.getLeft(), codes);
            extractCodes(node.getRight(), codes);
        }
    }
    private void encode() {
        encodedText = new byte[book.size()];
        int index = 0;
        while (book.hasNext()) {
            String word = book.next();
            for (CodeNode code : codes) {
                if (code.getValue().equals(word)) {
                    encodedText[index] = code.getCode();
                }
            }
        }
    }
    private void writeFile() {
        try {
            FileWriter writer = new FileWriter(outputFileName);
            writer.write(String.valueOf(encodedText));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private class FrequencyNode{
        public char character;
        public int count;
        public FrequencyNode(char character, int count){
            this.character = character;
            this.count = count;
        }
        public int compareTo(FrequencyNode other){
            return this.count - other.count;
        }
        public String toString(){
            return character + ": " + count;
        }
    }
    private class HuffmanNode{
        public char character;
        public int weight;
        public HuffmanNode left;
        public HuffmanNode right;
        public HuffmanNode(char character, int weight){
            this.character = character;
            this.weight = weight;
        }
        public HuffmanNode(HuffmanNode left, HuffmanNode right){
            this.left = left;
            this.right = right;
            this.weight = left.weight + right.weight;
        }
        public int compareTo(HuffmanNode other){
            return this.weight - other.weight;
        }
        public String toString(){
            return character + ": " + weight;
        }
    }
    private class CodeNode{
        public char character;
        public String code;
        public CodeNode(char character, String code){
            this.character = character;
            this.code = code;
        }
        public int compareTo(CodeNode other){
            return this.character - other.character;
        }
        public String toString(){
            return character + ": " + code;
        }
    }

}
