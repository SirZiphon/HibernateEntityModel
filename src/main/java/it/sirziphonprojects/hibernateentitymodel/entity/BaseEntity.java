package it.sirziphonprojects.hibernateentitymodel.entity;

import java.util.Map;

/**
 * Abstract class for all entities of the db
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.0
 */
public abstract class BaseEntity {
    /**
     * this method check if the primary keys of the current instance match with the ones in the map
     *
     * @param keysMap
     * @return
     */
    public abstract boolean equals(Map<String, Object> keysMap);

    /**
     * this method return the primary key object of the entity
     *
     * @param <T>
     * @return
     */
    public abstract <T extends KeyMapper> T getKey();

    @Override
    public boolean equals(Object other) {
        // check if the parameter exist and is an instance of BaseEntity
        if (other == null || !(other instanceof BaseEntity))
            return false;

        return this.baseEntityEquals((BaseEntity) other);
    }

    /**
     * this method force the developer to Override the method equals of class Object
     *
     * @param other
     * @return
     */
    protected abstract boolean baseEntityEquals(BaseEntity other);

    @Override
    public int hashCode() {
        return this.baseEntityHashCode();
    }

    /**
     * this method force the developer to Ovverride the method hashCode of class Object
     *
     * @return
     */
    protected abstract int baseEntityHashCode();
}
