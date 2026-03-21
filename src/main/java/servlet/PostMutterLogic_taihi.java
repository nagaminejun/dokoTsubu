package servlet;

import java.util.List;

import model.Mutter;

public class PostMutterLogic_taihi {
  public void execute(Mutter mutter, List<Mutter> mutterList) {
    mutterList.add(0, mutter); // 先頭に追加
  }
}