package com.example.blogsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Blog {


//Blog Class:
//id - title - body (Add All validation required)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    title :String
    @NotEmpty(message = "title shouldn't be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String title;

    //body : String max 300 letters
    @NotEmpty(message = "body can't be empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String body;

    //variable to link id that coming from logging  in


    //------------------Relation-------------------------//

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}