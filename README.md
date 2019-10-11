# Hibernate Entity Model

The purpose of this project is to create a library that provides a series of basic tools to access the main operations on the database, thus avoiding to rewrite these tools several times for each table of the db itself.

**To use this library it's assumed that each primary key is defined as an @embedded class that implements the Interface [KeyMapper](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/KeyMapper.java) as reported on the Library Composition section**

## Components used

### Base
* Java Used Version: 11
* [Gradle](https://gradle.org/) Used Version: 5.6.2

### Dependencies
* [hibernate-core](https://bintray.com/bintray/jcenter/org.hibernate%3Ahibernate-core/5.4.0.Final) Used Version: 5.4.0.Final
* [junit](https://bintray.com/bintray/jcenter/junit%3Ajunit) Used Version: 4.12


## Library Composition
The library is composed of two interfaces and two abstract classes: 
* [KeyMapper](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/KeyMapper.java) this interface must be implemented in any entity class; it's provides the follow methods:
    * ```Map<String, Object> getAsMap()``` that return the entity key as a list of couples of nameCamp - value.
    * ```public default boolean equals(Map<String, Object> keysMap)``` compares the passed keys in the map with the entity keys
    * ```public default boolean equals(KeyMapper keyMapper)``` compares the keys of the two entities
* [HibernateBaseConnector](/src/main/java/it/sirziphonprojects/hibernateentitymodel/connector/HibernateBaseConnector.java) this interface must be implemented in the helper class that bootstrap the hibernate connection; it's require to implement two methods:
    * ```public SessionFactory getSessionFactory();``` for get the sessionFactory instance
    * ```public void shutdown();``` for close the database connection
* [BaseModel](/src/main/java/it/sirziphonprojects/hibernateentitymodel/model/BaseModel.java) this abstract class provides a list of base methods for manage the entity:
    * ```public List<T> getList(boolean update)``` return the list of entities present into the database; if the parameter update is true, the list is reloaded before return;
    * ```public T getElement(Map<String, Object> keyMap, boolean searchInLocal)``` this method return the searched element; if no element match the passed key, <code>null</code> will be return. if the the param searchInLocal is true, the element will be searched into the current list present into the class; this for reduce the access requests to the database
    * ```public boolean insertElement(T element)``` this method insert the passed element into the db; return <code>true</code> if the element is successfully inserted, <code>false</code> otherwise
    * ```public boolean editElement(T updateElement)``` this method update the passed element; return <code>true</code> if the element is successfully changed, <code>false</code> otherwise
    * ```public boolean deleteElement(T element)``` this method delete the passed element from the db; return <code>true</code> if the element is successfully deleted, <code>false</code> otherwise
    * ```public boolean deleteElement(Map<String, Object> keyMap)``` variation of the deleteElement(T element), in this case the element is delete using only the primary key


## Usage example 

...TO DO...


## Changes 
Please see the [CHANGES](CHANGES.md) files for details


## Authors

* **[SirZiphon](https://github.com/SirZiphon)** - Initial work


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details