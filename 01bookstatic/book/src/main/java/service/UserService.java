package service;

import pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return  返回null表示登录失败，返回有值表示登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名存在false表示用户名可用
     */
    public boolean existsUsername(String username);
}
