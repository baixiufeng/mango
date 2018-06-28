package com.mango.model;

import javax.persistence.*;

@Entity
@Table(name="s_role")
public class SysRole {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private int id;

    /**
     * 角色对应的用户实体
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private Users SUser;

    /**
     * 角色名称
     */
    @Column(name="name",length=100)
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Users getSUser() {
        return SUser;
    }
    public void setSUser(Users sUser) {
        SUser = sUser;
    }

}
