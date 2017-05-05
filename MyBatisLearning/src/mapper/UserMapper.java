package mapper;

import po.User;

/**
 * Created by huangfugui on 2017/4/10.
 */
public interface UserMapper {
    public User getUser(Long id);
    public int insertUser(User user);
}
