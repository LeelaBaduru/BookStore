package com.learningspring.bookStore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class A {

    @OneToMany(cascade={CascadeType.ALL}, mappedBy="a")
    private List<B> bList =new ArrayList<B>();
    @Id
    @Column(nullable = false)
    private Long id;


    public void addB(B b) {
        addB(b, true);
    }

    void addB(B b, boolean set) {
        if (b != null) {
            if(getBList().contains(b)) {
                getBList().set(getBList().indexOf(b), b);
            }
            else {
                getBList().add(b);
            }
            if (set) {
                b.setA(this, false);
            }
        }
    }

    public void removeB(B b) {
        getBList().remove(b);
        b.setA(null);
    }

    public void setBList(List<B> bList) {
        this.bList = bList;
    }

    public List<B> getBList() {
        return bList;
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof A))
            return false;

        final A a = (A)object;

        if (id != null && a.getId() != null) {
            return id.equals(a.getId());
        }
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}