package day10;

import java.util.Set;

public class AdapterTreeNode {
    AdapterTreeNode parent;
    Adapter self;
    Set<AdapterTreeNode> children;
    Set<Adapter> used;
    int depth;

    public AdapterTreeNode(AdapterTreeNode parent, Adapter self, Set<Adapter> used, int depth) {
        this.parent = parent;
        this.self = self;
        this.used = used;
        this.depth = depth;
    }
}
