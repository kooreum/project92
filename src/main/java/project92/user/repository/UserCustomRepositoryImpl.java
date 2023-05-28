package project92.user.repository;

import project92.user.entity.QUser;
import project92.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Slf4j
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    private static final QUser qUser = QUser.user;

    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    // fetch Join으로 N+1 문제 해결가능
//    JPAQuery<User> query = new JPAQuery<>(getEntityManager());
//    List<User> users = query.select(user)
//            .from(user)
//            .innerJoin(user.role).fetchJoin()
//            .where(user.username.eq(username))
//            .fetch();

    // 리스트를 조회할때는 .fetch()
    @Override
    public List<User> findByEmail(String email) {
        return from(qUser)
                .select(qUser)
                .where(qUser.email.eq(email))
                .fetch();
    }

    @Override
    public User findByUsername(String username) {
        return from(qUser)
                .select(qUser)
                .where(qUser.username.eq(username))
                .fetchOne();
    }

    @Override
    public boolean existsByUsername(String username) {
        long count = from(qUser)
                .select(qUser)
                .where(qUser.username.eq(username))
                .fetchCount();
        log.info("username : {}", username);
        log.info("counting username : {}", count);
        return count > 0; // username이 존재하면 true
    }
}
