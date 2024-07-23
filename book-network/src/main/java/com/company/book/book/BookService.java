package com.company.book.book;

import com.company.book.common.PageResponse;
import com.company.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDtoConverter converter;

    public BookService(BookRepository bookRepository, BookDtoConverter converter) {
        this.bookRepository = bookRepository;
        this.converter = converter;
    }

    public Integer save(BookRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Book book = Book.builder()
                .id(request.id())
                .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
        book.setOwner(user);
        return bookRepository.save(book).getId();
    }

    public BookDto getById(Integer bookId) {
        return converter.convert(findById(bookId));
    }

    protected Book findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + bookId));
    }

    public PageResponse<BookDto> getAllBooks(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());

        List<BookDto> bookDtoList = converter.convert(books);
        return new PageResponse<>(
                bookDtoList,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }
}
