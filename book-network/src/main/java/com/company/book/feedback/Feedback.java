package com.company.book.feedback;

import com.company.book.book.Book;
import com.company.book.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private Double note;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
