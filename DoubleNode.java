
package canva;

/**
 *
 * @author josea
 */
public class DoubleNode <T> {
    private DoubleNode<T> prev;
    private DoubleNode<T> next;
    private T d;
    private T d1;
    
     public DoubleNode(T data){
        this.prev = null;
        this.next = null;
        this.d = data;
    }
     
    public DoubleNode(DoubleNode p, T data, DoubleNode n){
      prev = p;
      next = n;
      d = data;
    }
      
    public T getData(){
      return d;
    }
      
    public DoubleNode getNext(){
      return next;
    }
      
    public DoubleNode getPrev(){
     return prev;
    }
       
    public void setData(T data, T data1){
    d = data;
    }
        
    public void setNext(DoubleNode n){
    next = n;
    }
        
    public void setPrev(DoubleNode p){
    prev = p;
    }

}
