package restdemospring.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restdemospring.models.Book;
import restdemospring.models.Response;
import restdemospring.repositories.BookDaoDB;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class BookDBController {

    private static BookDaoDB bookDao = new BookDaoDB();


    @RequestMapping("/db/books")
    public List<Book> getBooks(){
        return bookDao.getAllBooks();
    }

    @RequestMapping("/db/booksHTML")
    public String getBooksHTML(){
        String res = "<HTML><HEAD><TITLE>Books</TITLE></HEAD><BODY><TABLE>";
        for (Book b : bookDao.getAllBooks()){
            res += "<TR><TD>"+b.getId()+"</TD><TD>"+b.getAuthor()+"</TD><TD>"+b.getTitle()+"</TD></TR>";
        }
        res += "</TABLE></HTML>";
        return res;
    }

    @RequestMapping("/db/book/{id}")
    public Book getBookById(@PathParam("id") int id){
        Book res = new Book();
        for (Book b : bookDao.getAllBooks()){
            if (b.getId() == id){
                res = b;
            }
        }
        return res;
    }

    @RequestMapping("/db/book/{id}/delete")
    public Response deleteBookById(@PathParam("id") int id){
        Response res = new Response("Book deleted", bookDao.deleteBook(id));
        return res;
    }

    @PostMapping("/db/book/add")
    public Response addBook(Book b){
        Response res = new Response("Book added", bookDao.addBook(b));
        return res;
    }

    @PostMapping("/db/book/update")
    public Response upsertBook(Book b){
        Response res = new Response("Book updated", bookDao.updateBook(b));
        return res;
    }

}