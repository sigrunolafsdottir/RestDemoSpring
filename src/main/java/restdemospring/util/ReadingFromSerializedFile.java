package restdemospring.util;

import restdemospring.models.Book;

import java.util.ArrayList;
import java.util.List;

public class ReadingFromSerializedFile {

    SerializationManager sm = new SerializationManager();
    List<Book> bookList = new ArrayList<>();


    public static void main(String[] args) {
        SerializationManager sm = new SerializationManager();
        List<Book> bookList = new ArrayList<>();
        bookList = (List<Book>)sm.deSerializeList("src/main/java/restdemospring/repositories/allaBocker2.ser");
        bookList.forEach(b -> System.out.println(b.getTitle()));
    }
}
