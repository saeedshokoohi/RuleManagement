package domain.Test;

import domain.BaseEntity;

import javax.persistence.*;

/**
 * Created by saeed on 12/12/2015.
 */
@Entity
@Table(name = "te_orgposition", schema = "public", catalog = "rm_db")
public class OrgPosition extends BaseEntity {
    private String title;
    private int level;
    private Personnel byPersonnel;



    @Basic
    @Column(name = "title", nullable = false, length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String ruleName) {
        this.title = ruleName;
    }

    @Column(name = "level", nullable = true, length = 200)
    public int getLevel() {
        return level;
    }

    public void setLevel(int age) {
        this.level = age;
    }



    @ManyToOne
    public Personnel getByPersonnel() {
        return byPersonnel;
    }

    public void setByPersonnel(Personnel manyToOne) {
        this.byPersonnel = manyToOne;
    }
}
