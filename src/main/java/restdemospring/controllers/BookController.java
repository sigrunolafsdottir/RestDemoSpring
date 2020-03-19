package restdemospring.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restdemospring.models.*;
import restdemospring.repositories.BookDAO;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    BookDAO bookDao = new BookDAO();
    List<Book> bookList = bookDao.getAllBooks();

    @RequestMapping("/books")
    public List<Book> index() {
        return bookList;
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
            if (b.getId() == 5){
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

}
