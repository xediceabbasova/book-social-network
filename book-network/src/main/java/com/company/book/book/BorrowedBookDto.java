package com.company.book.book;

public record BorrowedBookDto(
        Integer id,
        String title,
        String authorName,
        String isbn,
        double rate,
        boolean returned,
        boolean returnApproved
) {
}
