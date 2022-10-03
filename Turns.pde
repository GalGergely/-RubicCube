class Turns {
  public String[] yperm = {"r", "U", "R", "U", "r", "u", "R", "F", "r", "u", "R", "U", "R", "f", "r"};
  public String[] tperm = {"r", "u", "R", "U", "R", "f", "r", "r", "U", "R", "U", "r", "u", "R", "F"};
  public String[] jperm = {"r", "u", "R", "F", "r", "u", "R", "U", "R", "f", "r", "r", "U", "R", "U"};
  public String[] lperm = {"l", "U", "R", "u", "L", "u", "u", "r", "U", "R", "u", "u", "r"};

  String[] returnTodo(int id) {
    String[] tmp = {};
    
    if (id==67) {
      tmp=append(tmp, "m");
      for (String d : lperm) {
        tmp=append(tmp,d);
      }
      tmp=append(tmp, "M");
    }
    
    return tmp;
  }
}
