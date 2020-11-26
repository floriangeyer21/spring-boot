package com.spingboot.demo.domain;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;
    @ManyToOne(cascade = CascadeType.PERSIST)
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
