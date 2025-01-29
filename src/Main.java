// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Encryption set = new Encryption();
        char[][] checker  = set.content(); //To see if initial matrix works
        System.out.println("Initial content");
        showMatrix(checker);
        System.out.println();

        // Encrypting the text my Polybius Method
//        set.polybius("Hello World");
//        checker = set.content();
//        showMatrix(checker);
//
//        System.out.println();
//        set.dePolybius();//Decipher existing ciphered text
//        set.dePolybius("State secret");//Decipher input text

        // Encrypting the text using Caesar Method
//        set.caesar("Hello World", 4);
//        set.deCaesar(4);
//        System.out.println();
//
//        set.caesar("Hello World");
//        set.deCaesar();

        // Encrypting the text using Trisemus Method
        set.trisemus("Hello world", "database");
        showMatrix(set.content());


    }

    public static void showMatrix(char[][] set){
        for (int i = 0; i < set.length; i++) {
            for (int j = 0; j < set[0].length; j++) {
                System.out.print(set[i][j] + " ");
            }
            System.out.println();
        }
    }
}