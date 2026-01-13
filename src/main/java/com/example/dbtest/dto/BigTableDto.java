package com.example.dbtest.dto;

import com.example.dbtest.enums.CompetitionLevel;
import com.example.dbtest.enums.Role;
import com.example.dbtest.enums.UserStatus;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BigTableDto {
    private String uuid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private UserStatus status;
    private Instant createdAtUser;
    List<NewsDto> newsDtos;
    List<CompetitionDto> competitionDtos;
}
