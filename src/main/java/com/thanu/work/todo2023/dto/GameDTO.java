package com.thanu.work.todo2023.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameDTO implements Serializable {
    @Null(message = "Id will automatically generate")
    private Integer gameId;
    @Pattern(regexp = "[A-Fa-f0-9\\-]{36}",message = "Invalid user ID")
    private String userIdInGame;
    @NotBlank(message = "Game name can not be an empty value")
    private String gameName;
    private Date gameDate;
    private String gameImg;

}
