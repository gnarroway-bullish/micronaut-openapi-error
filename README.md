# Micronaut error

- demonstrates incompatible versions of micronaut-openapi when using enums with number fields 
- the enum was originally sourced from `com.yubico:webauthn-server-core:2.6.0`
    - simplified to be more concise
    - removed lombok to rule out interactions

### Actual behavior

When running the test, it fails with

```
  Caused by: com.fasterxml.jackson.databind.JsonMappingException: Cannot deserialize value of type `java.lang.Number` from String "EdDSA": not a valid number
```

### Expected behaviour

The `@JsonValue` and `@JsonCreator` annotations should be respected and not cause any issues.

- this worked in micronaut 3
- it seems broken from micronaut 4.0.0 to 4.7.6

### Workaround

- annotating each enum with `@JsonProperty` seems to work
- this can be validated by adjusting HelloController to return `COSEAlgorithmIdentifier2` (with 2 suffix)