package com.company.book.book;

import com.company.book.history.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class BookDtoConverter {

    public BookDto convert(Book from) {
        return new BookDto(
                from.getId(),
                from.getTitle(),
                from.getAuthorName(),
                from.getIsbn(),
                from.getSynopsis(),
                from.getOwner().fullName(),
                from.getBookCover().getBytes(StandardCharsets.UTF_8),
                from.getRate(),
                from.isArchived(),
                from.isShareable()
        );
    }

    public List<BookDto> convert(Page<Book> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }

    public BorrowedBookDto convertBorrowedBookDto(BookTransactionHistory from) {
        return new BorrowedBookDto(
                from.getBook().getId(),
                from.getBook().getTitle(),
                from.getBook().getAuthorName(),
                from.getBook().getIsbn(),
                from.getBook().getRate(),
                from.isReturned(),
                from.isReturnApproved()
        );
    }

    public List<BorrowedBookDto> convertBorrowedBookDto(Page<BookTransactionHistory> from) {
        return from.stream()
                .map(this::convertBorrowedBookDto)
                .toList();
    }
}
