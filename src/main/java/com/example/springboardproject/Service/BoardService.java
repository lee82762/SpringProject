package com.example.springboardproject.Service;

import com.example.springboardproject.Domain.Entity.BoardEntity;
import com.example.springboardproject.Domain.Repository.BoardRepository;
import com.example.springboardproject.Dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//서비스
@Service
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;


    @Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDto;
    }

    public List<BoardDto> getBoardList(){
        List<BoardEntity> boardEntityList=boardRepository.findAll();
        List<BoardDto>boardDto=new ArrayList<>();

        for(BoardEntity boardEntity:boardEntityList){
            BoardDto boardDto1=BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .createdDate(boardEntity.getCreatedDate())
                    .build();
            boardDto.add(boardDto1);
        }
        return boardDto;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    @Transactional
    public Long updatePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getId();

    }

    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }


}
