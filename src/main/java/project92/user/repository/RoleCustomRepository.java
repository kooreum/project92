package project92.user.repository;

import project92.user.entity.Role;

public interface RoleCustomRepository {

    Role findByRoleName(Role.RoleName roleName);
}