package it.sirziphonprojects.hibernateentitymodel.singleidkey;

/**
 * This abstract class provides a set of methods for managing entity key
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.0
 * @since version 2.0.0
 *
 * @param <T> the type of the entity key
 */
public abstract class BaseEntity<T> {

    public abstract T getId();
    public abstract void setId(T Id);

    @Override
    public boolean equals(Object other) {
        // check if the param or this id is null
        if (other == null || this.getId() == null)
            return false;

        // check if the param is an instance of the same class as the current object
        if (this.getClass() == other.getClass()) {
            BaseEntity<T> otherBaseEntity = (BaseEntity<T>) other;

            return this.getId().equals(otherBaseEntity.getId());
        }

        // check if the param is an instance of the same class as the key
        if (other.getClass() == this.getId().getClass()) {
            T otherT = (T) other;

            return this.getId().equals(otherT);
        } else {
            return false;
        }
    }

    @Override
    public abstract int hashCode();
}
