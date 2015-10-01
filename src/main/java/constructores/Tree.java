package constructores;

public class Tree {
  /* definir variables */
  private int height;
  private String name;
  /*inicialitzar arbre*/
  public Tree() {
    this(0, null);
  }
  /* assignar valors a Tree */
  public Tree(int height) {
    this(height, null);
  }
  public Tree(String name) {
    this(0, name);
  }
  public Tree(int height, String name) {
    this.height = height;
    this.name = name;
  }
  /* sortida a consola */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Un ");
    if (name == null)
      sb.append("árbol");
    else
      sb.append(name);
    if (height != 0)
      sb.append(" de ").append(height).append(" metros");
    return sb.toString();
  }
}