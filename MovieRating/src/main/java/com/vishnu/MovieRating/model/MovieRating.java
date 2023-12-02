package com.vishnu.MovieRating.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MovieRating {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="userId", nullable=false)
    private String userId;
    @Column
    private String movieId;
    private String status;
    private String rating;
}
