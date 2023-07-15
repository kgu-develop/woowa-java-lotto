package lotto.utils.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumberValidator extends Validator {
    private static final String COMMA = ",";

    @Override
    public void validate(final String userInput) {
        validateInputHasSpace(userInput);
        inputSplitByComma(userInput).forEach(this::validateInputIsNumeric);
    }

    private List<String> inputSplitByComma(final String userInput) {
        return Arrays.stream(userInput.split(COMMA))
                .collect(Collectors.toList());
    }
}
