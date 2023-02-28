package com.thanu.work.todo2023.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Games implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;
    @Column(nullable = false)
    private String gameName;
    @Column(nullable = false)
    private Date gameDate;
    private String gameImg;


    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id",nullable = false)
    private User user;

    public Games(String gameName, User user) {
        this.gameName = gameName;
        this.user = user;
    }


}
