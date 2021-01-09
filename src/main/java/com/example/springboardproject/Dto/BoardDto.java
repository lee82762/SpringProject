package com.example.springboardproject.Dto;

import com.example.springboardproject.Domain.Entity.BoardEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }



    public BoardEntity toEntity(){
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return boardEntity;
    }
}
