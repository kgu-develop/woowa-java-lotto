package lotto.utils.validator;

import static lotto.utils.ExceptionConstants.InputException.INPUT_MUST_NOT_CONTAINS_SPACE;

public abstract class Validator {
    abstract void validate(final String userInput);

    void validateInputHasSpace(final String userInput) {
        if (hasSpace(userInput)) {
            throw new IllegalArgumentException(INPUT_MUST_NOT_CONTAINS_SPACE.message);
        }
    }

    private static boolean hasSpace(String userInput) {
        return userInput.chars()
                .anyMatch(Character::isWhitespace);
    }
}
