package com.spingboot.demo.domain;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "review_id")
    private Long reviewId;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    private Long numerator;
    private Long denominator;
    private Long score;
    @Column(name = "order_date")
    private LocalDateTime localDate;
    private String summary;
    @Lob
    private String text;
}
