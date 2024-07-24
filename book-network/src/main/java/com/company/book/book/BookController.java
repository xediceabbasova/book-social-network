package com.company.book.book;

import com.company.book.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Integer> saveBook(@Valid @RequestBody BookRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.save(request, connectedUser));
    }

    @GetMapping("{/book-id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(bookService.getById(bookId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookDto>> getAllBooks(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                             Authentication connectedUser) {
        return ResponseEntity.ok(bookService.getAllBooks(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookDto>> getAllBooksByOwner(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                    @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                                    Authentication connectedUser) {
        return ResponseEntity.ok(bookService.getAllBooksByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookDto>> getAllBorrowedBooks(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                                             Authentication connectedUser) {
        return ResponseEntity.ok(bookService.getAllBorrowedBooks(page, size, connectedUser));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookDto>> getAllReturnedBooks(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                                             Authentication connectedUser) {
        return ResponseEntity.ok(bookService.getAllReturnedBooks(page, size, connectedUser));
    }

    @PatchMapping("/shareable/{book-id}")
    public ResponseEntity<Integer> updateShareableStatus(@PathVariable("book-id") Integer bookId,
                                                         Authentication connectedUser) {
        return ResponseEntity.ok(bookService.updateShareableStatus(bookId, connectedUser));
    }

    @PatchMapping("/archived/{book-id}")
    public ResponseEntity<Integer> updateArchivedStatus(@PathVariable("book-id") Integer bookId,
                                                         Authentication connectedUser) {
        return ResponseEntity.ok(bookService.updateArchivedStatus(bookId, connectedUser));
    }

}
