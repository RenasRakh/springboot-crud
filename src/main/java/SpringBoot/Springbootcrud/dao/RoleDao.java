package SpringBoot.Springbootcrud.dao;

import SpringBoot.Springbootcrud.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getRoleSet(Set<String> roles);
    Role getRoleById(int id);
    Role getRoleByName(String name);
}
