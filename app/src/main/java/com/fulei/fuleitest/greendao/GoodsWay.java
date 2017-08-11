package com.fulei.fuleitest.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by gcl on 2017/8/1.
 */
@Entity
public class GoodsWay {
    @Id(autoincrement = true)
    private Long id;
    private int x;
    private int y;
    private int z;

    @Generated(hash = 370141081)
    public GoodsWay(Long id, int x, int y, int z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Generated(hash = 388392882)
    public GoodsWay() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Long get_id() {
        return this.id;
    }

    public void set_id(Long id) {
        this.id = id;
    }


}
