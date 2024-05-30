package HashIntSet;

import org.junit.Test;

public class HashStringSetPractice {
    
    HashStringSet hash = new HashStringSet();

    @Test
    public void testLookUp() {
        hash.readBook("https://www.gutenberg.org/cache/epub/67098/pg67098.txt");
        System.out.println(hash.toString());
    }

}
