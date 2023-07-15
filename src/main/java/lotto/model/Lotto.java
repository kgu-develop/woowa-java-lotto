package lotto.model;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;
import static lotto.utils.ExceptionConstants.LottoException.*;
import static lotto.utils.LottoConstants.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateEachLottoElementIsInRange(numbers);
        validateTotalLottoSize(numbers);
        validateLottoHasDuplicateElement(numbers);
        sort(numbers);
        this.numbers = numbers;
    }

    private void validateEachLottoElementIsInRange(final List<Integer> numbers) {
        if (hasOutOfRange(numbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_IS_NOT_IN_RANGE.message);
        }
    }

    private boolean hasOutOfRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < MIN_VALUE || number > MAX_VALUE);
    }

    private void validateTotalLottoSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_IS_NOT_FULFILL.message);
        }
    }

    private void validateLottoHasDuplicateElement(final List<Integer> numbers) {
        if (hasDuplicateNumber(numbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MUST_BE_UNIQUE.message);
        }
    }

    private boolean hasDuplicateNumber(final List<Integer> baseballs) {
        return baseballs.stream()
                .distinct()
                .count() != LOTTO_SIZE;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
