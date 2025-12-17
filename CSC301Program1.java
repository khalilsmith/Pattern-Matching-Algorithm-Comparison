///////////////////////////////
/// Program 1
/// Khalil Smith
/// 2025-09-15
//////////////////////////////////////

//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.Scanner;

//From the Linked list file 
class Node<Type> {
    private Type data;
    private Node<Type> link;

    public Node(Type data) {
        this.data = data;
        this.link = null;
    }

    public Type getData() {
        return data;
    }
    public void setData(Type data) {
        this.data = data;
    }
    public Node<Type> getLink() {
        return link;
    }
    public void setLink(Node<Type> link) {
        this.link = link;
    }
}

class List<Type> {
    public static final int MAX_SIZE = 50000;
    private Node<Type> head;
    private Node<Type> tail;
    private Node<Type> curr;
    private int num_items;

    public List()
    {
        this.head = null;
        this.tail = null;
        this.curr = null;
        this.num_items = 0;
    }

    public void InsertAfter(Type data)
    {
        if (this.num_items >= MAX_SIZE) {
            throw new IndexOutOfBoundsException("Too many items");
        }

        Node<Type> newNode = new Node<>(data);

        if (this.head == null) {
            this.head = this.tail = this.curr = newNode;
        }

        else {
            newNode.setLink(this.curr.getLink());
            this.curr.setLink(newNode);

        }

        else {
            newNode.setLink(this.curr.getLink());
            this.curr.setLink(newNode);
            this.curr = newNode;
        }
        this.num_items++;
    }

    public int getSize()
    {
        return this.num_items;
    }

    @Override
    public String toString()
    {
        if (this.head == null) {
            return "NULL";
        }
        else {
            Node<Type> n = this.head;
            String s = "";
            while(n != null){
                s += n.getData() + "";
                n = n.getLink();
            }
            return s;
        }
    }
}

//Implemnting the code of all the searching algorithms 
class SearchingAlgorithms {
    public static int bruteForce(String text, String pattern) {
        int count = 0;
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) count++;
        }
        return count;
    }

    public static int boyerMoore(String text, String pattern) {
        int count = 0;
        int n = text.length();
        int m = pattern.length();
        if (m == 0) return 0;

        int[] badChar = new int[256];
        for (int i = 0; i < 256; i++) badChar[i] = -1;
        for (int i = 0; i < m; i++) badChar[pattern.charAt(i)] = i;

        int s = 0;
        while (s <= n - m) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) j--;
            if (j < 0) {
                count++;
                s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }
        return count;
    }

    public static int kMP(String text, String pattern) {
        int count = 0;
        int n = text.length();
        int m = pattern.length();
        if (m == 0) return 0;

        int[] lps = new int[m];
        computeLPS(pattern, lps);

        int i = 0, j = 0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++; j++;
            }
            if (j == m) {
                count++;
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }
        return count;
    }

    private static void computeLPS(String pattern, int[] lps) {
        int m = pattern.length();
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) len = lps[len - 1];
                else { lps[i] = 0; i++; }
            }
        }
    }
}


public class CSC301Program1 {
    public static void main(String[] args) {
        String in_text = "IckleMePickleMeTickleMetooWentforarideinaflyingshoeHoorayWhatfunItstimeweflewSaidIckleMePickleMeTickleMetooIcklewascaptainPicklewascrewAndTickleservedcoffeeandmulliganstewAshigherAndhigherAndhighertheyflewIckleMePickleMeTickleMetooIckleMePickleMeTickleMetooOverthesunandbeyondtheblueHoldonStayinIhopewedoCriedIckleMePickleMeTickleMetooIckleMePickleMeTickleMetooNeverreturnedtotheworldtheyknewAndnobodyknowswhatshappenedtoDearIckleMePickleMeTickleMetoo";
        //String filename = "prog1input.txt";
        //System.out.println(txtpattern);

        List<Character> charList = new List<>();

        //Reading the file into the code
        //try {
            //Scanner scan = new Scanner(new File(filename));
            //while (scan.hasNextLine()) {
                //Inserting characters one at a time 
                //String line = scan.nextLine();
        System.out.println("Build list");
        for (int i = 0; i < in_text.length(); i++) {
            charList.InsertAfter(in_text.charAt(i));
            }
            charList.InsertAfter('\n');
            //}
            //System.out.println("File in link list:" + charList.getSize());
            //System.out.println(charList.toString());
        //}
        //catch (FileNotFoundException e) {
            //System.out.println("File not found");
            //return;
        //}
        //Converting the linked list to strings for searching 
        String text = charList.toString();
        String pattern = "";
        System.out.println("Get pattern");
        try (Scanner input = new Scanner(System.in)) {

        //Asking the user for a substring to search
            System.out.print("Enter substring to serach for: ");
            pattern = input.nextLine();
        }
        //input.close();
        System.out.println("Did I get here?");
        //Measuring the time of the brute force searching algorithm
        long start = System.nanoTime();
        int bFCount = SearchingAlgorithms.bruteForce(text, pattern);
        long end = System.nanoTime();
        long bFTime= end-start;

        //Measuring the time of Boyer-Moore Searching algorithm
        start = System.nanoTime();
        int bMCount = SearchingAlgorithms.boyerMoore(text, pattern);
        end = System.nanoTime();
        long bMTime = end - start;

        //Measuring the time of the KMP Searching Algorithm
        start = System.nanoTime();
        int kMPCount = SearchingAlgorithms.kMP(text, pattern);
        end = System.nanoTime();
        long kMPTime = end - start;

        //Printing the number of matches 
        System.out.println("\nResults:");
        System.out.println("Brute Force Algorithm found " + bFCount + " matches in " + bFTime + " ns");
        System.out.println("Boyer-Moore Algorithm found " + bMCount + " matches in " + bMTime + " ns");
        System.out.println("KMP Algorithm found " + kMPCount + " matches in " + kMPTime + " ns");

        //Determining the fastest Algorithm 
        long fastest = Math.min(bFTime, Math.min(bMTime, kMPTime));
        String winner = (fastest == bFTime) ? "Brute Force":
                        (fastest == bMTime) ? "Boyer-Moore": "KMP";
        
        //Printing the fastest Algorithm results
        System.out.println("\nConclusion: " + winner + " was fastest on this input.");


    }
}

