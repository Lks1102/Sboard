package kr.co.sboard.dto;

import kr.co.sboard.entity.ArticleEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDTO {

    private int no;
    private int parent;
    private int comment;
    private String cate;
    private String title;
    private String content;
    private int file;
    private MultipartFile fname;
    private int hit;
    private String writer;
    private String regip;

    private LocalDateTime rdate;

    public ArticleEntity toEntity() {
        return ArticleEntity.builder()
                .no(no)
                .parent(parent)
                .comment(comment)
                .cate(cate)
                .title(title)
                .content(content)
                .file(file)
                .hit(hit)
                .writer(writer)
                .regip(regip)
                .rdate(rdate)
                .build();
    }
}
