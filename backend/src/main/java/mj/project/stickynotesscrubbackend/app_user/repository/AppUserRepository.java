package mj.project.stickynotesscrubbackend.app_user.repository;

import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByUsername(String username);
    boolean existsAppUserByUsername(String username);
}
