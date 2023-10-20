package kr.co.sboard.repository;

import kr.co.sboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    int countByUid(String uid);
    int countByHp(String hp);
    int countByNick(String nick);

}
