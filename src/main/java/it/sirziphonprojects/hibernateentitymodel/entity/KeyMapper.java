package it.sirziphonprojects.hibernateentitymodel.entity;

import java.util.Map;

/**
 * Interface for key objects of entities
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.0
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
    public boolean equals(Map<String, Object> keysMap);
}