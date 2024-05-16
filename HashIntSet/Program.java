package HashIntSet;

public class Program {

    public static void main (String[] args) {
        HashStringSet hash = new HashStringSet();
        hash.readBook("https://www.gutenberg.org/cache/epub/72848/pg72848.txt");
        System.out.println(hash.toString());
        System.out.println(hash.numBuckets());
        System.out.println(hash.numValues());
        System.out.println(hash.loadFactor());
        System.out.println(hash.memoryEfficiency());
    }
}
