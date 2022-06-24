package com.jojoldu.book.springboot.service.admin;

import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import com.jojoldu.book.springboot.web.dto.UsersListResponseDto;
import com.jojoldu.book.springboot.web.dto.UsersResponseDto;
import com.jojoldu.book.springboot.web.dto.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;

    @Transactional
    public UsersResponseDto findById (Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));

        return  new UsersResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, UsersUpdateRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));

        user.update(requestDto.getEmail(), requestDto.getName(), requestDto.getRole());

        return id;
    }

/*    @Transactional
    public void delete (Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. id="+ id));
        userRepository.delete(user);
    }*/

    @Transactional(readOnly = true)
    public List<UsersListResponseDto> findAllDesc() {
        return userRepository.findAllDesc().stream()
                .map(UsersListResponseDto::new)
                .collect(Collectors.toList());
    }
}
