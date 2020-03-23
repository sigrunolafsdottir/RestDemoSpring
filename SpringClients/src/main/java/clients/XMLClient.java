package clients;

import models.Book;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLClient {

    private static void getBooksAsXMLString()
    {
        final String uri = "http://localhost:8080/books.xml";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    }

    private static Book getBook(){
        final String uri = "http://localhost:8080/book.xml";
        RestTemplate restTemplate = new RestTemplate();
        Book result = restTemplate.getForObject(uri, Book.class);
        System.out.println(result.getTitle()+" by "+result.getAuthor());
        return result;
    }



    public static void main(String[] args){
        getBook();
    }

}
