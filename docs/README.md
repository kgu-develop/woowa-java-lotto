## Feature

- [ ] User는 `1000원` 단위로 로또 N장을 구매한다
    - 구매 후 로또 생성기로부터 N장의 로또를 생성한다
        - `1000원` 단위로 입력하지 않을 경우 `IllegalArgumentException`를 발생시키고 `[Error]`로 시작하는 에러 메시지를 출력 후 `종료`한다

- [X] LottoWinningMachine은 로또 당첨 번호를 생성한다
    - 총 7개의 중복되지 않는 숫자 (기본 당첨 번호 6개 + 보너스 번호 1개)

- [ ] LottoStatistics는 N장의 로또에 대한 당첨 통계를 산출한다

<br>
<hr>

## Model

### `Lotto`

- 로또 번호 List를 추상화시킨 모델
    - [X] 로또 번호는 1..45 범위 안에 존재해야 한다
    - [X] 각 번호들은 중복되지 않아야 한다

### `UserLotto`

- `1000원` 단위의 로또 1장을 구매하는 컴포넌트

### `UserLottos`

- `1000원` 단위의 로또 N장을 구매하는 컴포넌트
    - `List<UserLotto>`

### `LottoWinningMachine`

- 로또 당첨 번호 6개 + 보너스 번호를 생성하는 컴포넌트

### `WinningRank`

- 당첨과 관련된 순위를 표현하는 컴포넌트

### `LottoStatistics`

- 로또 당첨 내역 및 수익률을 산출하기 위한 통계용 컴포넌트

<br>
<hr>

## Utils

### `ExceptionConstants`

- 전역 예외 메시지 통합 컴포넌트

### `LottoConstants`

- Lotto 숫자 범위 & 전체 사이즈 관련 상수 전용 컴포넌트

<br>
<hr>

## View

### `InputView`

- 사용자 Input을 받기 위한 컴포넌트

### `OutputView`

- 로또 게임 진행과 관련된 출력 컴포넌트

<br>
<hr>

## Controller

### `GameController`

- 로또 게임 진행과 관련된 컨트롤러

<br>
