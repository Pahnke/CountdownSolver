import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Path {

  private final List<PathNode> nodes;

  public Path(List<PathNode> nodes) {
    this.nodes = nodes;
  }

  public int invertedDistFromTarget(int target) {
    return Math.abs(Numbers.RADIUS
        - distFromTarget(target));
  }

  public int distFromTarget(int target) {
    return Math.abs(this.getResult() - target);
  }

  // The value at the end of the path
  public int getResult() {
    return nodes.get(nodes.size() - 1).getResult();
  }

  @Override
  public int hashCode() {
    List<PathNode> tempNodes = new ArrayList<>(nodes);
    tempNodes.sort(Comparator.comparing(PathNode::getResult));
    return tempNodes.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Path)) {
      return false;
    }

    Path other = (Path) obj;
    if (this.getResult() != other.getResult()) {
      return false;
    }

    if (this.getNodes().size() != other.getNodes().size()) {
      return false;
    }

    // If the method of working it out is the same up to associativity
    // or commutativity (not and), then the results of each step will be
    // the same upto re-ordering
    List<PathNode> tNodes = new ArrayList<>(this.getNodes());
    List<PathNode> oNodes = new ArrayList<>(other.getNodes());
    tNodes.sort(Comparator.comparing(PathNode::getResult));
    oNodes.sort(Comparator.comparing(PathNode::getResult));

    return tNodes.equals(oNodes);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (PathNode node : nodes) {
      builder.append(node.toString());
      builder.append("\n");
    }
    return builder.toString();
  }

  public List<PathNode> getNodes() {
    return nodes;
  }
}
