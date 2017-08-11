package com.fulei.fuleitest.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GOODS_WAY".
*/
public class GoodsWayDao extends AbstractDao<GoodsWay, Long> {

    public static final String TABLENAME = "GOODS_WAY";

    /**
     * Properties of entity GoodsWay.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property X = new Property(1, int.class, "x", false, "X");
        public final static Property Y = new Property(2, int.class, "y", false, "Y");
        public final static Property Z = new Property(3, int.class, "z", false, "Z");
    }


    public GoodsWayDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsWayDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GOODS_WAY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"X\" INTEGER NOT NULL ," + // 1: x
                "\"Y\" INTEGER NOT NULL ," + // 2: y
                "\"Z\" INTEGER NOT NULL );"); // 3: z
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GOODS_WAY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GoodsWay entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getX());
        stmt.bindLong(3, entity.getY());
        stmt.bindLong(4, entity.getZ());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GoodsWay entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getX());
        stmt.bindLong(3, entity.getY());
        stmt.bindLong(4, entity.getZ());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GoodsWay readEntity(Cursor cursor, int offset) {
        GoodsWay entity = new GoodsWay( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // x
            cursor.getInt(offset + 2), // y
            cursor.getInt(offset + 3) // z
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GoodsWay entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setX(cursor.getInt(offset + 1));
        entity.setY(cursor.getInt(offset + 2));
        entity.setZ(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GoodsWay entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GoodsWay entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GoodsWay entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}