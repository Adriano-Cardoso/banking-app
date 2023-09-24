CREATE TABLE TB_USER_PROFILES (
    user_id INT,
    profile_id INT,
    FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    FOREIGN KEY (profile_id) REFERENCES tb_profile(profile_id)
);
