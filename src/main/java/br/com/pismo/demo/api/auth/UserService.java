/*
 * UserService.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.auth;

import java.util.List;

/**
 * Interface of business class of {@link User}.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
public interface UserService {
    /**
     * Save data of entity {@link User}.
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * Update data of entity {@link User}.
     *
     * @param user
     * @return
     */
    User update(User user);

    /**
     * Delete data of entity {@link User}, by id.
     *
     * @param id
     */
    void delete(String id);

    /**
     * Search users by name.
     *
     * @param name
     * @return
     */
    List<User> search(String name);
}
