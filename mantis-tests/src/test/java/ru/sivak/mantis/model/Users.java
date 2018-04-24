package ru.sivak.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author p.sivak.
 * @since 24.04.2018.
 */
public class Users extends ForwardingSet<User> {

    private Set<User> deligate;

    public Users(Users users) {
        this.deligate = new HashSet<>(users.deligate);
    }

    public Users() {
        this.deligate = new HashSet<>();
    }

    public Users(Collection<User> users) {
        this.deligate = new HashSet<>(users);
    }

    @Override
    protected Set<User> delegate() {
        return deligate;
    }

    public Users withAdded(User user) {
        Users users = new Users(this);
        users.add(user);
        return users;
    }

    public Users without(User user) {
        Users users = new Users(this);
        users.remove(user);
        return users;
    }
}
