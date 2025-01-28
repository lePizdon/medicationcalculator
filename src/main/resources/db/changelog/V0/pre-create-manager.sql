SET search_path = 'security';
INSERT INTO users (user_name, user_password_hash)
VALUES
('manager', '{bcrypt}$2b$12$F5ytZ.rVLaR.6VLdcee.Fu1PbIXvmMAVaHfVPBUTGlwXY6AMb8y/2');
INSERT INTO authority (authority_value)
VALUES
('ROLE_MANAGER');
INSERT INTO users_authority (id_user, id_authority)
VALUES (
        (SELECT id FROM users
         WHERE user_name = 'manager'),
        (SELECT id FROM authority
         WHERE authority_value = 'ROLE_MANAGER'))
