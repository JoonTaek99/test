package com.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import com.board.dtos.FileBoardDto;
import com.board.command.BookInsertCommad;
import com.board.command.ReserveBookCommand;
import com.board.dtos.BookDto;
import com.board.service.FileService;
import com.board.service.BookService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/book")
public class BookController {

   @Autowired
   private BookService bookService;
   
   @Autowired
   private FileService fileService;
   
   
   @GetMapping(value = "/bookList")
	public String bookList(Model model, HttpServletRequest request) {
		System.out.println("책목록 보기");
		List<BookDto> list = bookService.getAllList();
		request.getSession().setAttribute("ldto", list);
		model.addAttribute("list", list);
		
		return "book/bookList";
	}
   
   @GetMapping(value = "/selectList")
	public String SelectList(Model model, String title) {
		System.out.println("검색목록 보기");
		List<BookDto> list = bookService.getSelectList(title);
		model.addAttribute("list", list);
		return "book/bookList";
	}
   
   @GetMapping(value = "/bookInsert")
   public String boardInsertForm(Model model) {
      System.out.println("책추가폼 이동");
      model.addAttribute("bookInsertCommand", new BookInsertCommad());
      
      return "book/bookInsertForm";
   }
   
   @PostMapping(value = "/bookInsert")
   public String boardInsert(@Validated BookInsertCommad bookInsertCommad,
                           BindingResult result, MultipartRequest multipartRequest,
                           HttpServletRequest request,
                           Model model) throws IllegalStateException, IOException {
      
      if(result.hasErrors()) {
         System.out.println("글을 모두 입력해주세요");
         return "book/bookInsertForm";
      }
      
      bookService.insertBook(bookInsertCommad, multipartRequest, request);
      
      return "redirect:/book/bookList";
   }
   
   @GetMapping(value = "/bookDetail")
   public String boardDetail(int book_seq, Model model, BookInsertCommad bookInsertCommad) {
	   System.out.println("책 상세내용");
      BookDto dto = bookService.getBook(book_seq);
      model.addAttribute("bookInsertCommand", new BookInsertCommad());
      model.addAttribute("bdto", dto);
//      int seq = bookInsertCommad.getBook_seq();
//      bookService.readCount(seq);//조회수 증가

      return "book/bookDetail";
   }
   
   @GetMapping(value = "/reserveBook")
   public String reserveBook(int book_seq, String reserver, Model model, ReserveBookCommand reserveBookCommand) {
	   System.out.println(book_seq + ", " + reserver);
	   bookService.reserveBook(book_seq, reserver);
	   return "redirect:/book/bookList";
   }
   
//   @PostMapping(value = "/newsBoardUpdate")
//   public String boardUpdate(@Validated NewsUpdateBoardCommand updateBoardCommand
//                              ,BindingResult result) {
//      System.out.println("수정시작");
//      if(result.hasErrors()) {
//         System.out.println("수정내용을 모두 입력해주세요");
//         return "news/newsBoardDetail";
//      }
//      newsBoardService.updateBoard(updateBoardCommand);
//      
//      return "redirect:/news/newsBoardDetail?board_seq="+updateBoardCommand.getBoard_seq();
//      
//   }
   
//   @GetMapping(value = "/download")
//   public void download(int file_seq, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//      FileBoardDto fdto = fileService.getFileInfo(file_seq);
//      
//      fileService.fileDownload(fdto.getOrigin_filename(),fdto.getStored_filename(),request,response);
//   }
//   
//   @RequestMapping(value="mulDel",method = {RequestMethod.GET, RequestMethod.POST})
//   public String mulDel(@Validated NewsDelBoardCommand delBoardCommand
//                   ,BindingResult result
//                      , Model model,String pnum) {
//      if(result.hasErrors()) {
//         System.out.println("최소하나 체크하기");
//         List<NewsBoardDto> list=newsBoardService.getAllList(pnum);
//         model.addAttribute("list", list);
//         return "news/newsboardList";
//      }
//      newsBoardService.mulDel(delBoardCommand.getSeq());
//      System.out.println("글삭제함");
//      return "redirect:/news/boardList";
//   }

}