
package canva;

public class DoublyLinkedList <T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    int size = 0;
    
    public boolean isEmpty(){
       return head == null;
    }
     
    public void addDataAtEnd(T c){
        DoubleNode n = new DoubleNode(tail, c, null);
        if(isEmpty()){
            head = tail = n;
        }else{
            tail.setNext(n);
            tail = n;
        }
        this.size++;
    }
    
    public void addDataAtFront(T c){
        DoubleNode n = new DoubleNode(null, c, head);
        if(isEmpty()){
            head = tail = n;
        }else{
            head.setPrev(n);
            head = n;
        }
        this.size++;
    }
    
     public T removeDataFromFront(){
        T data = head.getData();
        if(!isEmpty()){
            if(head == tail){
                head = tail = null;
            }else{
                head = head.getNext();
                head.setPrev(null);
            }
        }
        this.size--;
        return data;
    }
     
    public T removeDataAtEnd(){
        T data = tail.getData();

        if(!isEmpty()){
            if(head == tail){
                head = tail = null;
            }else{
                tail = tail.getPrev();
                tail.setNext(null);
            }
        }
        this.size--;
        return data;
    }
    
    public int countBNodes(){
        DoubleNode data = head;
        int count = 0;
        while(data != null){
            count++;
            data = data.getNext();
        }
        return count;
    }
    
    public boolean deleteData(T d){
        DoubleNode data = head;
        while(data.getData() != d){
            data = data.getNext();
        }

        if(data != null){
            if(data.getPrev() == null){
                head = data.getNext();
                if(!isEmpty()){
                    head.setPrev(null);
                }
            }else if(data.getNext() == null){
                tail = data.getPrev();
                if(!isEmpty()){
                    tail.setNext(null);
                }
            }else{
                data.getPrev().setNext(data.getNext());
                data.getNext().setPrev(data.getPrev());
            }
        }
         this.size--;
        return data != null;
    }
    
    public void insert(int index, T data){
        if(index > this.size){
            System.out.println("Error: Index error");
            return;
        }
        DoubleNode<T> newDNode = new DoubleNode<>(data); //Creamos nuestro nuevo nodo
        DoubleNode<T> tmp = head; //Creamos un nodo temporal que apunta a la cabeza
        int cont = 0;

        while (cont < index - 1){
            tmp = tmp.getNext(); //Se va cambiando el nodo temporal al siguiente nodo
            cont++;
        }
        //Quiero introducir mi nodo [c]-> entre [a]->[b]
        newDNode.setNext(tmp.getNext()); //[c]->[b]
        tmp.setNext(newDNode); //[a]->[c]
        this.size++;
    }
    
    public T index(int index){
        if(index > this.size){
            System.out.println("Error: Index error");
        }
        
        DoubleNode<T> tmp = head; //Creamos un nodo temporal que apunta a la cabeza
        
        int cont = 0;
        while (cont < index - 1){
            tmp = tmp.getNext(); //Se va cambiando el nodo temporal al siguiente nodo
            cont++;
        }
        return tmp.getData();
    }
    
    public void reverse(){
        DoubleNode cn = head;
        DoubleNode tmp = null;
        while(cn != null){
            tmp = cn.getNext();
            cn.setNext(cn.getPrev());
            cn.setPrev(tmp);

            cn = cn.getPrev();
        }
        tmp = head;
        head = tail;
        tail = tmp;
        head.setPrev(null);
        tail.setNext(null);
    }
    
    public String toString(){
        DoubleNode data = head;
        String str = "";
        while(data != null){
            str += data.getData();
            //System.out.print(data.getData());
            data = data.getNext();
        }

        return str;
    }

}
