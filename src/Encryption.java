import java.util.Random;
public class Encryption {
    private static final char[] alphabet = "abcdefghiklmnopqrstuvwxyz".toCharArray();
    public static String text;
    //Letter J is absent, but it'll be considered in future functions
    private char[][] matrix;
    private char[][] defaultMatrix;

    Encryption(){
        this.matrix = reset();
        this.defaultMatrix = reset();
    }

    // Set of common functions
    public char[][] content() {
        return matrix;
    }
    public char[][] reset(){
        char[][] newSquare = new char[5][5];
        for (int i = 0; i < 25; i++) {
//            System.out.print("[" + i/5 + " " + i%5 + "] "); //Check if array gets out of bounds
            newSquare[i/5][i%5] = alphabet[i];
        }
        System.out.println();
        return newSquare;
    }
    public char[][] randomize() {
        char[][] newSquare = new char[5][5];
        Random random = new Random();

        // Convert the alphabet string into a mutable list of characters
        char[] characters = alphabet.clone();

        for (int i = 0; i < 25; i++) {
            // Generate a random index within the range of remaining characters
            int randomIndex = random.nextInt(25-i);

            // Assign a random character to the 2D array from 0-24
            newSquare[i / 5][i % 5] = characters[randomIndex];

            // Remove the used character by shifting the remaining characters
            characters = removeElement(characters, randomIndex);

        }
        return newSquare;
    }
    private static char[] removeElement(char[] array, int id) {
        return new String(array).replaceFirst(Character.toString(array[id]), "").toCharArray();
    }
    public static int[] findRowIndexes(char[][] arr, char a) {
        if(a == ' '){
            return new int[] {6, 6};
        }
        // If given character is 'j'
        char b;
        if (a == 'j'){
            b = 'i';
        }
        else{
            b = a;
        }

        // Main code
        int i = 0;
        while (i < 25) {
            if (arr[i/5][i%5] == b){
                break;
            }
            i++;
        }
        // Returns the row and column indexes of a letter
        return new int[]{i/5, i%5};
    }

    // Randomly shuffles the array elements then returns ciphered input text
    public String polybius(String text){
        String cipher = "";
        System.out.print("Initial text: " + text + "\n");
        matrix = randomize();
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Encrypted text: ");
        for (int i = 0; i < text.length(); i++) {
            int[] id = findRowIndexes(matrix, characters[i]);
            if (id[0] != 6) {
                cipher += matrix[(id[0] + 1) % 5][(id[1]) % 5];
            }
            else{
                cipher += " ";
            }
        }
        this.text = cipher;
        System.out.println(cipher);
        return cipher;
    }
    // Deciphering by Polybius Method
    public String dePolybius(){
        String cipher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Decrypted text: ");
        for (int i = 0; i < text.length(); i++) {
            int[] id = findRowIndexes(matrix, characters[i]);
            if (id[0] != 6) {
                cipher += matrix[(id[0] + 4) % 5][(id[1]) % 5];
            }
            else{
                cipher += " ";
            }
        }
        this.text = cipher;
        System.out.println(cipher);
        return cipher;
    }
    public String dePolybius(String text){
        String cypher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Decrypted text: ");
        for (int i = 0; i < text.length(); i++) {
            int[] id = findRowIndexes(matrix, characters[i]);
            if (id[0] != 6) {
                cypher += matrix[(id[0] + 4) % 5][(id[1]) % 5];
            }
            else{
                cypher += " ";
            }
        }
        this.text = cypher;
        System.out.println(cypher);
        return cypher;
    }

    // Encryption using Caesar Method. Moves a character alphabetically by dislocation number
    public String caesar(String text, int dislocation){
        String cypher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Encrypted text: ");
        for (int i = 0; i < characters.length; i++) {
            int[] id = findRowIndexes(defaultMatrix, characters[i]);
            if (id[0] == 6){
                cypher += " ";
                continue;
            }
            int j = (id[0] * 5 + id[1] + dislocation) % 25; // Keeps the number in range between 0 - 24
            cypher += defaultMatrix[j / 5][j % 5];
        }
        this.text = cypher;
        System.out.println(cypher);
        return cypher;
    }
    // Default Caesar Cipher. Uses 3 as dislocation number
    public String caesar(String text){
        String cypher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Encrypted text: ");
        for (int i = 0; i < characters.length; i++) {
            int[] id = findRowIndexes(defaultMatrix, characters[i]);
            if (id[0] == 6){
                cypher += " ";
                continue;
            }
            int j = (id[0] * 5 + id[1] + 3) % 25;
            cypher += defaultMatrix[j / 5][j % 5];
        }
        this.text = cypher;
        System.out.println(cypher);
        return cypher;
    }
    // Decrypting by Caesar Method
    public String deCaesar(String text, int dislocation){
        String cypher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Decrypted text: ");
        for (int i = 0; i < characters.length; i++) {
            int[] id = findRowIndexes(defaultMatrix, characters[i]);
            if (id[0] == 6){
                cypher += " ";
                continue;
            }
            int j = (id[0] * 5 + id[1] - dislocation + 25) % 25; // 25 is added to prevent negative values
            cypher += defaultMatrix[j / 5][j % 5];
        }
        System.out.println(cypher);
        return cypher;
    }
    public String deCaesar(String text){
        String cypher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Decrypted text: ");
        for (int i = 0; i < characters.length; i++) {
            int[] id = findRowIndexes(defaultMatrix, characters[i]);
            if (id[0] == 6){
                cypher += " ";
                continue;
            }
            int j = (id[0] * 5 + id[1] + 22) % 25;
            cypher += defaultMatrix[(j / 5) % 5][j % 5];
        }
        System.out.println(cypher);
        return cypher;
    }
    public String deCaesar(int dislocation){ // Deciphers precious ciphered text and dislocates characters by -(dislocation)
        String cypher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Decrypted text: ");
        for (int i = 0; i < characters.length; i++) {
            int[] id = findRowIndexes(defaultMatrix, characters[i]);
            if (id[0] == 6){
                cypher += " ";
                continue;
            }
            int j = (id[0] * 5 + id[1] - dislocation + 25) % 25; // 25 is added to prevent negative values
            cypher += defaultMatrix[j / 5][j % 5];
        }
        System.out.println(cypher);
        return cypher;
    }
    public String deCaesar(){ // Takes previous ciphered text to decipher and dislocated characters by -3
        String cypher = "";
        char[] characters = text.toLowerCase().toCharArray();
        System.out.print("Decrypted text: ");
        for (int i = 0; i < characters.length; i++) {
            int[] id = findRowIndexes(defaultMatrix, characters[i]);
            if (id[0] == 6){
                cypher += " ";
                continue;
            }
            int j = (id[0] * 5 + id[1] + 22) % 25;
            cypher += defaultMatrix[(j / 5) % 5][j % 5];
        }
        System.out.println(cypher);
        return cypher;
    }

    // Encrypting by Trisemus Method.
    public String trisemus(String text, String key){
        // Creating new matrix using key word
        String left = String.copyValueOf(alphabet); // Combine char array back to String
        int count = key.length();
        for (int i = 0; i < count; i++) { // Placing letters from keyword first and deleting duplicates
            matrix[i / 5][i % 5] = key.charAt(i); // Places letters of key word one by one
            left = left.replaceAll(key.charAt(i) + "", "");
            System.out.println(left);
            count = i;
        }
        for (int i = 0; i < left.length(); i++) {
            matrix[(i + count) / 5][(i + count) % 5] = left.charAt(i);
        }


        // Replacing characters
        String cipher = "";
        char[] characters = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            int[] id = findRowIndexes(matrix, characters[i]);
            if (id[0] != 6) {
                cipher += defaultMatrix[(id[0]) % 5][(id[1]) % 5];
            }
            else{
                cipher += " ";
            }
        }
        this.text = cipher;
        System.out.println(cipher);
        return cipher;
    }
}
