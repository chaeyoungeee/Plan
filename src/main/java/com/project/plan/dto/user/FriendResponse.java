package com.project.plan.dto.user;

import com.project.plan.domain.Friend;
import com.project.plan.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendResponse {
    private Long id;
    private String nickname;

    public FriendResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }


    public FriendResponse(Friend friend) {
        this.id = friend.getFriendId();
        this.nickname = friend.getFriendName();
    }
}
