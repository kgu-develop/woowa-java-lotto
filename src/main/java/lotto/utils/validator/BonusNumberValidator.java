package lotto.utils.validator;

public class BonusNumberValidator extends Validator {
    @Override
    public void validate(final String userInput) {
        validateInputHasSpace(userInput);
        validateInputIsNumeric(userInput);
    }
}
