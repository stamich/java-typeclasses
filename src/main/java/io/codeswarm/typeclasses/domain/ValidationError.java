package io.codeswarm.typeclasses.domain;

/**
 * Closed hierarchy of validation failures used by the smart-constructor
 * examples in milestone 0.4.1.
 */
public sealed interface ValidationError
        permits BlankUserId, BlankPersonName, PersonNameTooLong, InvalidAge {

    /**
     * Returns a human-readable description of this validation failure.
     *
     * @return validation message
     */
    String message();
}
