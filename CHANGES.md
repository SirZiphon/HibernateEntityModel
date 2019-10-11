# Change Log

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