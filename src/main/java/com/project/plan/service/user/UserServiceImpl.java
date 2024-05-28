package com.project.plan.service.user;

import com.project.plan.domain.User;
import com.project.plan.dto.user.FriendResponse;
import com.project.plan.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Primary
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    //가입
    @Transactional
    public Long join(User user) {
        validateDuplicateUsername(user.getUsername());
        validateDuplicateNickname(user.getNickname());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void validateDuplicateUsername(String name) {
        Optional<User> findUser = userRepository.findByUsername(name);
        if (findUser == null) {
            throw new IllegalStateException("이미 존재하는 유저네임입니다.");
        }
    }

    public void validateDuplicateNickname(String name) {
        Optional<User> findUser = userRepository.findByNickname(name);
        if (findUser.isPresent()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    @Override
    //닉네임으로 멤버 찾기
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public FriendResponse addFriend(Long id, String nickname) {
        User user = userRepository.findById(id);

        User friend = userRepository.findByNickname(nickname).orElseThrow(NoSuchElementException::new);
        user.addFriend(friend.getId(), nickname);
        return new FriendResponse(friend.getId(), nickname);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    @Override
    //전체 멤버 찾기
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    //닉네임 변경하기
    @Transactional
    public void updateNickname(Long id, String nickname) {
        User user = userRepository.findById(id);
        if (user.getNickname() == nickname) {
            throw new IllegalStateException("현재 닉네임과 다른 닉네임을 입력해주세요.");
        }
        validateDuplicateNickname(nickname);

        user.setNickname(nickname);
    }
}
