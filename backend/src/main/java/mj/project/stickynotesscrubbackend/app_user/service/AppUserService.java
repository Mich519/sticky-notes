package mj.project.stickynotesscrubbackend.app_user.service;

import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.app_user.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findAppUserByUsername(username);
        if(appUserOptional.isEmpty())
            throw new UsernameNotFoundException("");
        return appUserOptional.get();
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> findUserByUsername(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return appUserRepository.existsAppUserByUsername(username);
    }

    public AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }
}
