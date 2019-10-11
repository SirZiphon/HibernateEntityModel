package it.sirziphonprojects.hibernateentitymodel.entity;

import java.util.Map;

/**
 * This interface provides a set of methods for managing entity keys
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.1.0
 */
public interface KeyMapper {

    /**
     * this method return the list of keys as a map
     *
     * @return the list of keys as a map
     */
    public Map<String, Object> getAsMap();

    /**
     * this method confront the locals key with those passed in the map; return <code>true</code> if the keys match,
     * <code>false</code> otherwise
     *
     * @param keysMap
     * @return
     */
    public default boolean equals(Map<String, Object> keysMap) {
        // check if keysMap is null or empty
        if(keysMap == null || keysMap.isEmpty())
            return false;

        // get local keysMap
        Map<String, Object> localMap = this.getAsMap();

        // check if the keysMap have the same number of keys
        if (localMap.size() != keysMap.size())
            return false;

        // confront any camp
        for (String key : localMap.keySet()) {
            if (!localMap.get(key).equals(keysMap.get(key)))
                return false;
        }

        return true;
    }

    /**
     * this method confront the current instance with the one passed as parameter; return true if the keys match,
     * <code>false</code> otherwise
     *
     * @param keyMapper
     * @return
     */
    public default boolean equals(KeyMapper keyMapper) {
        // check if keyMapper is null
        if (keyMapper == null)
            return false;

        return this.equals(keyMapper.getAsMap());
    }
}
