package lotto.domain.user;

import java.util.List;

public class User {
  private List<Lotto> lottos;
  
  public User(List<Lotto> lottos) {
    this.lottos = lottos;
  }
  
  public List<Lotto> getLottos() {
    return lottos;
  }
}
