package kr.co.sboard.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "File")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;
    private int ano;
    private String ofile;
    private String sfile;
    private int download;

    @CreationTimestamp
    private String rdate;

    public FileDTO toDTO() {
        return FileDTO.builder()
                .ano(ano)
                .fno(fno)
                .ofile(ofile)
                .sfile(sfile)
                .download(download)
                .rdate(rdate)
                .build();
    }
}
