public class List2<Type> {
    //Maximum allowed size
    public static final int MAX_SIZE = 1000000;

    //Head is the first node, the tail is the last node, and curr is the pointer
    private Node3<Type> head;
    private Node3<Type> tail;
    private Node3<Type> curr;
    //Number of elements in the list
    private int num_items;

    //Constructors
    public List2() {
        this.head = null;
        this.tail = null;
        this.curr = null;
        this.num_items = 0;
    }

    //Copy constructor
    public List2(List2<Type> l) {
        this.head = this.tail = this.curr = null;
        this.num_items = 0;

        //While loop that loops through the other list and insert its data
        Node3<Type> n = l.head;
        while (n != null) {
            this.InsertAfter(n.getData());
            n = n.getLink();
        }
    }

    //Moving the current pointer to the first node
    public void First() { this.curr = this.head; }
    //Moving the current pointer to the last node
    public void Last() { this.curr = this.tail; }

    //Moving the current pointer to a specific location
    public void SetPos(int pos) {
        if (this.head == null || pos < 0 || pos >= this.num_items) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        this.curr = this.head;
        //For loop that goes through the list until reaching a specific location
        for (int i = 0; i < pos; i++) {
            this.curr = this.curr.getLink();
        }
    }

    //Moving the current pointer one node backwards
    public void Prev() {
        if (this.head == null || this.curr == this.head) return;
        Node3<Type> temp = this.head;
        //While loop that finds the node before the current node
        while (temp.getLink() != this.curr) temp = temp.getLink();
        this.curr = temp;
    }

    //Moving the current pointer one node ahead
    public void Next() {
        if (this.curr != null && this.curr.getLink() != null) {
            this.curr = this.curr.getLink();
        }
    }

    //Getting the position index of the current node
    public int GetPos() {
        if (this.curr == null) return -1;
        int pos = 0;
        Node3<Type> n = this.head;
        //While loop that finds the current node
        while (n != null) {
            if (n == this.curr) return pos;
            n = n.getLink();
            pos++;
        }
        //Only returns when the node or position is not found
        return -1;
    }

    //Getting the value at the current node
    public Type GetValue() {
        if (this.curr == null) throw new IllegalStateException("No current element");
        return this.curr.getData();
    }

    //Getting the size of the list
    public int GetSize() {return this.num_items;}

    //Inserting the new node before the current position
    public void InsertBefore(Type data) {
        if (this.num_items >= MAX_SIZE) throw new IndexOutOfBoundsException("Too many items");
        Node3<Type> newNode = new Node3<>(data);

        if (this.head == null) {
            //If the list is empty the new node becomes the head, tail, and curr
            this.head = this.tail = this.curr = newNode;
        } else if (this.curr == this.head) {
            //Inserting before the head
            newNode.setLink(this.head);
            this.head = this.curr = newNode;
        } else {
            //Finding the previous node
            Node3<Type> prev = this.head;
            while (prev.getLink() != this.curr) prev = prev.getLink();
            newNode.setLink(this.curr);
            prev.setLink(newNode);
            this.curr = newNode;
        }
        this.num_items++;
    }

    //Inserting the new node after the current position
    public void InsertAfter(Type data) {
        if (this.num_items >= MAX_SIZE) throw new IndexOutOfBoundsException("Too many items");
        Node3<Type> newNode = new Node3<>(data);

        if (this.head == null) {
            //If the list is empty the new node becomes the head, tail, and curr
            this.head = this.tail = this.curr = newNode;
        } else if (this.curr == this.tail) {
            //Inserting the new node at the end
            this.curr.setLink(newNode);
            this.tail = this.curr = newNode;
        } else {
            //Linking the new node after the current node
            newNode.setLink(this.curr.getLink());
            this.curr.setLink(newNode);
            this.curr = newNode;
        }
        this.num_items++;
    }

    //Removing teh current node
    public void Remove() {
        if (this.curr == null || this.num_items == 0) throw new IllegalStateException("No items to remove");

        if (this.curr == this.head) {
            //Removing the head
            this.head = this.head.getLink();
            this.curr = this.head;
            //The list becomes empty
            if (this.head == null) this.tail = null;
        } else {
            //Finding the previous node
            Node3<Type> prev = this.head;
            while (prev.getLink() != this.curr) prev = prev.getLink();
            prev.setLink(this.curr.getLink());

            if (this.curr == this.tail) {
                //removing the tail of the linked list
                this.tail = prev;
                this.curr = this.tail;
            } else {
                //Moving the curr forward
                this.curr = this.curr.getLink();
            }
        }
        this.num_items--;
    }

    //Replacing the data at the current node
    public void Replace(Type data) {
        if (this.curr == null || this.num_items == 0) throw new IllegalStateException("No items to replace");
        this.curr.setData(data);
    }

    //Checking if the list is empty
    public boolean IsEmpty() { return (this.head == null); }
    //Checking if the list is full
    public boolean IsFull() { return this.num_items >= MAX_SIZE; }


    //Comparing two lists for equality
    public boolean Equals(List2<Type> l) {
        if (l == null) return false;
        if (this.num_items != l.num_items) return false;

        Node3<Type> n1 = this.head;
        Node3<Type> n2 = l.head;

        //While loop that compares the values in both lists
        while (n1 != null && n2 != null) {
            if (!n1.getData().equals(n2.getData())) return false;
            n1 = n1.getLink();
            n2 = n2.getLink();
        }
        return true;
    }

    //Concatenate the list with another list
    public List2<Type> Add(List2<Type> l) {
        List2<Type> result = new List2<>();
        Node3<Type> n = this.head;
        int count = 0;

        //While loop that copies the nodes from this list
        while (n != null && count < MAX_SIZE) {
            result.InsertAfter(n.getData());
            n = n.getLink();
            count++;
        }

        //While loop that copies the nodes from the other list
        Node3<Type> m = (l != null) ? l.head : null;
        while (m != null && count < MAX_SIZE) {
            result.InsertAfter(m.getData());
            m = m.getLink();
            count++;
        }
        return result;
    }

    //Converting the list to a string
    @Override
    public String toString() {
        if (this.head == null) return "NULL";
        Node3<Type> n = this.head;
        StringBuilder sb = new StringBuilder();
        //While loop that appends each of the data to the string
        while (n != null) {
            sb.append(n.getData()).append(" ");
            n = n.getLink();
        }
        return sb.toString().trim();
    }
}

