package com.project.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseApplication<T> {
    private LocalDateTime localDateTime;
    private T result;

    public static <T> ResponseApplication<T> result(@Nullable T result) {
        return ResponseApplication.<T>builder()
                .localDateTime(LocalDateTime.now())
                .result(result)
                .build();
    }
}
