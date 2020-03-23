package restdemospring.repositories;

import restdemospring.models.Book;
import restdemospring.util.SerializationManager;

import java.util.ArrayList;
import java.util.List;

public class BookDaoSerPersistent implements IBookDAO {

        SerializationManager sm = new SerializationManager();
        List<Book> bookList = new ArrayList<>();
        String bookListPath = "src/main/java/restdemospring/repositories/allaBocker.ser";

        public List<Book> getAllBooks(){
            return  (List<Book>)sm.deSerializeList(bookListPath);
        }

        public void persistBooks(List<Book> bookList){
            sm.serializeList(bookList, bookListPath);
        }
}
