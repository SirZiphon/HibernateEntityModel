package it.sirziphonprojects.hibernateentitymodel.entity;

import javax.persistence.EmbeddedId;
import java.util.Map;
import java.util.Objects;

/**
 * Abstract class for all entities of the db
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.1
 */
public abstract class BaseEntity<T extends KeyMapper> {

    @EmbeddedId
    protected T id;

    // id getter and setter
    public T getId() { return this.id; }
    public void setId(T id) { this.id = id; }

    /**
     * this method check if the primary keys of the current instance match with the ones in the map
     *
     * @param keysMap
     * @return
     */
    public boolean equals(Map<String, Object> keysMap) {
        // check if the parameter is null or empty
        if (keysMap == null || keysMap.isEmpty())
            return false;

        return this.id.equals(keysMap);
    }

    @Override
    public boolean equals(Object other) {
        // check if the parameter exist and is an instance of BaseEntity
        if (other == null || !(other instanceof BaseEntity) || this.getClass() == other.getClass())
            return false;

        BaseEntity otherBaseEntity = (BaseEntity) other;

        // check if the id objects are instantiated
        if (this.id == null || otherBaseEntity.getId() == null)
            return false;

        return this.id.equals(otherBaseEntity.getId().getAsMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
