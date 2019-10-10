package it.sirziphonprojects.hibernateentitymodel.entity;

import java.util.Map;

/**
 * Abstract class for all entities of the db
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.0
 */
public abstract class BaseEntity<T extends KeyMapper> {
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
     * @return
     */
    public abstract T getKey();

    @Override
    public boolean equals(Object other) {
        // check if the parameter exist and is an instance of BaseEntity
        if (other == null || !(other instanceof BaseEntity) || this.getClass() == other.getClass())
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
