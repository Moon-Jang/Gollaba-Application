package kr.mj.gollaba.unit.user.repository;

import kr.mj.gollaba.unit.common.RepositoryTest;
import kr.mj.gollaba.user.repository.UserRepository;
import kr.mj.gollaba.unit.user.factory.UserFactory;
import kr.mj.gollaba.user.entity.User;
import kr.mj.gollaba.user.type.UserRoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @DisplayName("회원 객체 추가")
    @Test
    void save() throws Exception {
        //given
        User user = UserFactory.create();

        //when
        User savedUser = userRepository.save(user);

        flushAndClear();

        User foundUser = userRepository.findById(savedUser.getId())
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(foundUser.getId()).isEqualTo(savedUser.getId());
        assertThat(foundUser.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(foundUser.getNickName()).isEqualTo(savedUser.getNickName());
        assertThat(passwordEncoder.matches(UserFactory.TEST_PASSWORD, foundUser.getPassword())).isTrue();
    }

    @DisplayName("회원 객체 수정")
    @Test
    void save_update() throws Exception {
        //given
        User savedUser = userRepository.save(UserFactory.create());

        flushAndClear();

        String newNickname = "김길동";
        String newPassword = "gkdnwj1234*";
        UserRoleType newRole = UserRoleType.ROLE_ADMIN;

        //when
        savedUser.updateNickName(newNickname);
        savedUser.updatePassword(passwordEncoder.encode(newPassword));
        savedUser.updateUserRole(newRole);

        userRepository.save(savedUser);
        User foundUser = userRepository.findById(savedUser.getId())
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(foundUser.getId()).isEqualTo(savedUser.getId());
        assertThat(newNickname).isEqualTo(savedUser.getNickName());
        assertThat(newRole).isEqualTo(savedUser.getUserRole());
        assertThat(passwordEncoder.matches(newPassword, foundUser.getPassword())).isTrue();
    }

    @DisplayName("회원 객체 존재 유무")
    @Test
    public void existsByEmail() throws Exception {
        //given
        User user = userRepository.save(UserFactory.create());

        flushAndClear();

        //when
        final boolean true_result = userRepository.existsByEmail(user.getEmail());
        final boolean false_result = userRepository.existsByEmail("notExist");

        //then
        assertThat(true_result).isTrue();
        assertThat(false_result).isFalse();
    }

    @DisplayName("회원 객체 조회 By userId")
    @Test
    public void findByEmail() throws Exception {
        //given
        User user = userRepository.save(UserFactory.create());

        flushAndClear();

        //when
        User foundUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getNickName()).isEqualTo(user.getNickName());
        assertThat(passwordEncoder.matches(UserFactory.TEST_PASSWORD, foundUser.getPassword())).isTrue();
    }

}