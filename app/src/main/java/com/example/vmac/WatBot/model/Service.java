package com.example.vmac.WatBot.model;

import java.io.Serializable;

/**
 * Created by felipemacedo on 24/08/17.
 */

public class Service implements Serializable {
    protected Long id;
    protected String name;
    protected String username;
    protected String password;
    protected boolean selected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String toString() {
        return this.name;
    }
}
