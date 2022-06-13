package com.learningspring.bookStore.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class B {

    @ManyToOne
    @JoinColumn(name = "A_ID", referencedColumnName = "ID")
    private A a;
    @Id
    @Column(nullable = false)
    private Long id;

    public B() {
    }

    public void setA(A a) {
        setA(a, true);
    }

    void setA(A a, boolean add) {
        this.a = a;
        if (a != null && add) {
            a.addB(this, false);
        }
    }

    public A getA() {
        return a;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof B))
            return false;

        final B b = (B)object;

        if (id != null && b.getId() != null) {
            return id.equals(b.getId());
        }
        return false;
    }
}