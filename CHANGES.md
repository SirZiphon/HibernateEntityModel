# Change Log

## V2.0.0
* changed the package organization: now the [BaseModel](/src/main/java/it/sirziphonprojects/hibernateentitymodel/customidkey/BaseModel.java) and [KeyMapper](/src/main/java/it/sirziphonprojects/hibernateentitymodel/customidkey/KeyMapper.java) are under ```it.sirziphonprojects.hibernateentitymodel.customidkey```
* added two new classes under package ```it.sirziphonprojects.hibernateentitymodel.singleidkey```: [BaseEntity](/src/main/java/it/sirziphonprojects/hibernateentitymodel/singleidkey/BaseModel.java) and [BaseModel](/src/main/java/it/sirziphonprojects/hibernateentitymodel/singleidkey/BaseModel.java); these class must be used for manage entities where the primary key is only one named **id**
## V1.3.0
* changes on [BaseModel](/src/main/java/it/sirziphonprojects/hibernateentitymodel/model/BaseModel.java): 
    * now the generic Type is an extension of the [KeyMapper](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/KeyMapper.java) interface
* removed class BaseEntity because unused
* fixed issue under method ```public boolean insertElement(T element)```: now the PersistenceException is managed
* changes on [KeyMapper](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/KeyMapper.java):
    * added default method ```public default boolean equals(Map<String, Object> keysMap)```
    * added default method ```public default boolean equals(KeyMapper keyMapper)```

## V1.2.1
* abstract class [BaseEntity](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/BaseEntity.java) V1.0.1 set id as protected

## V1.2.0
* abstract class [BaseEntity](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/BaseEntity.java) removed methods ``` protected abstract boolean baseEntityEquals(BaseEntity other);``` and ```protected abstract int baseEntityHashCode();```; optimized methods ```equals``` and ```hashCode```

## V1.1.2
* fixed minor issues

## V1.1.1 
* abstract class [BaseEntity](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/BaseEntity.java) removed unused method ```getKey()```

## V1.1.0
* abstract class [BaseEntity](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/BaseEntity.java) added parameter id  an implementation of method ```public boolean equals(Map<String, Object> keysMap)```
* interface [KeyMapper](/src/main/java/it/sirziphonprojects/hibernateentitymodel/entity/KeyMapper.java) added method ```public boolean equals(Map<String, Object> keysMap)```

## V1.0.1
* fixed minor issues

## V1.0.0
* Initial release