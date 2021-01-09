package com.example.springboardproject.Controller;

import com.example.springboardproject.Dto.BoardDto;
import com.example.springboardproject.Service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    @Autowired
    private BoardService boardService;

  //@RestController 사용시
/*   @GetMapping("/")
    public ModelAndView list(Model model){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("board/list");
       List<BoardDto>boardDtos=new ArrayList<>();
       boardDtos=boardService.getBoardList();
       model.addAttribute("boardList",boardDtos);

        return  modelAndView;
    }*/





  @GetMapping("/post/{no}")
  public String detail(@PathVariable("no") Long no,Model model){

     BoardDto boardDto=boardService.getPost(no);
     model.addAttribute("boardDto",boardDto);

     return "board/detail.html";
  }


    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardDtoList=boardService.getBoardList();
        model.addAttribute("boardList",boardDtoList);

        return "board/list.html";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }
    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/";
    }


    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable Long id){
      boardService.deletePost(id);
      return "redirect:/";

    }
    @GetMapping("/post/edit/{no}") //게시물 수정페이지
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{no}")//게시물 수정
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }



}
