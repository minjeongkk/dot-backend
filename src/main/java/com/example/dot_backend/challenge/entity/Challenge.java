package com.example.dot_backend.challenge.entity;


import com.example.dot_backend.challenge.dto.ChallengeResponseDto;
import com.example.dot_backend.challenge.enums.State;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {
    @Id
    @Column(name="challenge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userId")
    private Long userId;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long alarmed;
    private Long count;
    private Long totalCount;
    @ElementCollection
    @CollectionTable(
            name = "challenge_days",
            joinColumns = @JoinColumn(name = "challenge_id")
    )
    private List<String> days = new ArrayList<>();
    private LocalDateTime createdDate;
    private LocalDate updatedDate;
    @Enumerated(EnumType.STRING)
    private State checked;

    public void updateState(Long count) {
        this.count = count;
        this.checked = State.DONE;
        this.updatedDate = LocalDate.now();
    }

    public void updateCheckState() {
        this.checked = State.NOT_STARTED;
    }

    public ChallengeResponseDto getChallengeResponseDto() {
        return ChallengeResponseDto.builder()
                .id(this.id)
                .userId(this.userId)
                .title(this.title)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .alarmed(this.alarmed)
                .count(this.count)
                .totalCount(this.totalCount)
                .days(this.days)
                .checked(this.checked)
                .build();
    }
}
