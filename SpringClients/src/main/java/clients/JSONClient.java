package clients;

import models.Response;
import org.springframework.web.client.RestTemplate;
import models.Book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONClient {

    private static void getBooksAsString(){
        final String uri = "http://localhost:8080/books.json";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    }

    private static Book getBook(){
        final String uri = "http://localhost:8080/book.json";
        RestTemplate restTemplate = new RestTemplate();
        Book result = restTemplate.getForObject(uri, Book.class);
        System.out.println(result.getTitle()+" by "+result.getAuthor());
        return result;
    }

    private static Book getBookById(int id){
        final String uri = "http://localhost:8080/book/{id}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        RestTemplate restTemplate = new RestTemplate();
        Book result = restTemplate.getForObject(uri, Book.class, params);
        System.out.println(result.getTitle()+" by "+result.getAuthor());
        return result;
    }

    private static List<Book> getBooks(){
        final String uri = "http://localhost:8080/books.json";
        RestTemplate restTemplate = new RestTemplate();
        Book[] resultArray = restTemplate.getForObject(uri, Book[].class);
        List<Book> result = Arrays.asList(resultArray);
        for (Book b : result){
            System.out.println(b.getTitle()+" by "+b.getAuthor());
        }
        return result;
    }

    private static void createBook(String title, String author, int id)
    {
        final String uri = "http://localhost:8080/book/add";
        Book newBook = new Book(title, author, id);
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.postForObject( uri, newBook, Response.class);
        System.out.println(result.getMessage());
    }

    public static void main(String[] args){
        createBook("a", "b", 9);
    }
}
