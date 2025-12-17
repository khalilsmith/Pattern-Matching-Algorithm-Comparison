public class Node3<Type> {
    private Type data;
    private Node3<Type> link;

    public Node3(Type data) {
        this.data = data;
        this.link = null;
    }

    public Type getData() { return data; }
    public void setData(Type data) { this.data = data; }
    public Node3<Type> getLink() { return link; }
    public void setLink(Node3<Type> link) { this.link = link; }
}

