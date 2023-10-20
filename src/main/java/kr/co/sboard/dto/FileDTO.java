package kr.co.sboard.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.sboard.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private int fno;
    private int ano;
    private String ofile;
    private String sfile;
    private int download;

    private String rdate;

    public FileEntity toEntity() {
        return FileEntity.builder()
                .fno(fno)
                .ano(ano)
                .ofile(ofile)
                .sfile(sfile)
                .download(download)
                .rdate(rdate)
                .build();
    }
}
