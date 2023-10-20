package kr.co.sboard.repository;

import kr.co.sboard.entity.TermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository  extends JpaRepository<TermsEntity, Integer> {
}
