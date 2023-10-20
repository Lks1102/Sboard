package kr.co.sboard.controller.article;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/list")
    public String list(Model model, PageRequestDTO pageRequestDTO) {

        PageResponseDTO pageResponseDTO = articleService.findByParentAndCate(pageRequestDTO);
        log.info("리스폰스 DTO : " + pageRequestDTO.toString());
        model.addAttribute(pageResponseDTO);

        return "/article/list";
    }

    @GetMapping("/article/register")
    public String register() {
        return "/article/register";
    }

    @PostMapping("/article/register")
    public String register(HttpServletRequest request, ArticleDTO dto) {

        dto.setRegip(request.getRemoteAddr());
        log.info("Regip Setting : " + dto);

        articleService.save(dto);

        return "redirect:/article/list";
    }
}
