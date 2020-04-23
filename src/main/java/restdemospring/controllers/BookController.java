package restdemospring.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import restdemospring.models.*;
import restdemospring.repositories.BookDAO;
import restdemospring.repositories.BookDaoDB;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class BookController {

    BookDAO bookDaoDB = new BookDAO();
    List<Book> bookList = bookDaoDB.getAllBooks();

    @RequestMapping("/books")
    public List<Book> index() {
        return bookList;
    }

    @RequestMapping("/book")
    public Book oneBook() {
        return new Book("Avalons dimmor", "M. Bradley Zimmer", 10);
    }

    @RequestMapping(value = "/bookJSON", produces = "application/json")
    public Book oneBookJSON() {
        return new Book("Avalons dimmor", "M. Bradley Zimmer", 10);
    }

    @RequestMapping(value = "/bookXML", produces = "application/xml")
    public Book oneBookXML() {
        return new Book("Avalons dimmor",
                "Bradley Zimmer", 10);
    }


    @RequestMapping("/booksHTML")
    public String getBooksHTML(){
        String res = "<HTML><HEAD><TITLE>Books</TITLE></HEAD><BODY><TABLE>";
        for (Book b : bookList){
            res += "<TR><TD>"+b.getId()+"</TD><TD>"+b.getAuthor()+"</TD><TD>"+b.getTitle()+"</TD></TR>";
        }
        res += "</TABLE></HTML>";
        return res;
    }

    @RequestMapping("/book/{id}")
    public Book getBookById(@PathVariable int id){
        Book res = new Book();
        for (Book b : bookList){
            if (b.getId() == id){
                res = b;
            }
        }
        return res;
    }

    @RequestMapping("/booksBetween/{idFrom}/{idTo}")
    public List<Book> getBooksBetween(@PathVariable int idFrom, @PathVariable int idTo){
         List<Book> res = new ArrayList();
        for (Book b : bookList){
            int id = b.getId();
            if (id >= idFrom && id <= idTo){
                res.add(b);
            }
        }
        return res;
    }

    @RequestMapping("/book/{id}/delete")
    public Response deleteBookById(@PathVariable("id") int id){
        Response res = new Response("Book deleted", Boolean.FALSE);

        int indexToRemove = -1;
        for (int i = 0; i < bookList.size(); i++){
            if (bookList.get(i).getId() == id){
                indexToRemove = i;
            }
        }

        if (indexToRemove != -1){
            bookList.remove(indexToRemove);
            res.setStatus(Boolean.TRUE);
        }

        return res;
    }


    @PostMapping("/book/add")
    public Response addBook(@RequestBody Book b){
        System.out.println(b.getId()+" "+b.getAuthor()+" "+b.getTitle());
        Response res = new Response("Book added", Boolean.FALSE);
        bookList.add(b);
        res.setStatus(Boolean.TRUE);
        return res;
    }

    @PostMapping("/book/update")
    public Response upsertBook(@RequestBody Book b){
        Response res = new Response("Book updated", Boolean.FALSE);

        int indexToUpdate = -1;
        for (int i = 0; i < bookList.size(); i++){
            if (bookList.get(i).getId() == b.getId()){
                indexToUpdate = i;
            }
        }

        if (indexToUpdate == -1){
            bookList.add(b);
            res.setMessage("Book inserted");
            res.setStatus(Boolean.TRUE);
        }
        else{
            bookList.set(indexToUpdate, b);
            res.setStatus(Boolean.TRUE);
        }
        return res;
    }

}
