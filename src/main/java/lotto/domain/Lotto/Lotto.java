package lotto.domain.Lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateLottoHasDuplicate(numbers);
        validateLottoNumbersRange(numbers);
        this.numbers = numbers;
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호가 6자리가 아닙니다.");
        }
    }
    
    private void validateLottoHasDuplicate(List<Integer> numbers) {
        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException("로또 번호에 중복된 번호가 있습니다.");
        }
    }
    
    private boolean hasDuplicate(List<Integer> numbers) {
        return numbers.stream().distinct().count() != 6;
    }
    
    private void validateLottoNumbersRange(List<Integer> numbers) {
        if (isNotInRange(numbers)) {
            throw new IllegalArgumentException("로또 번호 중에 1 ~ 45가 아닌 것이 있습니다.");
        }
    }
    
    private boolean isNotInRange(List<Integer> numbers) {
        return numbers.stream().anyMatch(number -> number < 1 || number > 45);
    }
    
    @Override
    public String toString() {
        return numbers.toString();
    }
}
