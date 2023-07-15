package lotto.utils.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.utils.ExceptionConstants.InputException.WINNING_NUMBER_MUST_BE_SPLIT_BY_COMMA;

public class WinningNumberValidator extends Validator {
    private static final String COMMA = ",";

    @Override
    public void validate(final String userInput) {
        validateInputHasSpace(userInput);
        inputSplitByComma(userInput).forEach(this::validateInputElementIsNumeric);
    }

    private List<String> inputSplitByComma(final String userInput) {
        return Arrays.stream(userInput.split(COMMA))
                .collect(Collectors.toList());
    }

    void validateInputElementIsNumeric(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(WINNING_NUMBER_MUST_BE_SPLIT_BY_COMMA.message);
        }
    }
}
