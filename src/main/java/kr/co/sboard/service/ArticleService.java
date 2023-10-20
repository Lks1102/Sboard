package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;

    public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<ArticleEntity> result = articleRepository.findByParentAndCate(0, pageRequestDTO.getCate(), pageable);

        List<ArticleDTO> dtoList = result.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                .toList();

        int totalElement = (int) result.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElement)
                .build();

    }

    // 글등록
    @Transactional
    public void save(ArticleDTO dto) {

        // ano를 얻기 위해
        ArticleEntity savedEntity = articleRepository.save(dto.toEntity());

        FileDTO fileDTO = fileUpload(dto);

        // file upload 실행 할 경우
        if (fileDTO != null) {
            // file insert
            fileDTO.setAno(savedEntity.getNo());
            fileRepository.save(fileDTO.toEntity());

            savedEntity.insertFile();

        }

    }

    @Value("${spring.servlet.multipart.location}")
    private String filePath;
    public FileDTO fileUpload(ArticleDTO dto) {

        MultipartFile mf = dto.getFname();

        if(!mf.isEmpty()) {
            // 첨부된 파일이 있을경우
            String path = new File(filePath).getAbsolutePath();

            String oName = mf.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String sName = UUID.randomUUID().toString()+ext;

            try {
                mf.transferTo(new File(path, sName));
            } catch (IOException e) {
                log.error("fileUpload Error : " + e.getMessage());
            }
            return FileDTO.builder().ofile(oName).sfile(sName).build();
        }
        return null;

    }

}
