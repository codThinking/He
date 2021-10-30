package dao;

import pojo.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return  如果返回null，说明没有这个用户，反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1操作失败，返回其它影响操作的行数
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return 如果返回null，说明没有这个用户，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password);
}
