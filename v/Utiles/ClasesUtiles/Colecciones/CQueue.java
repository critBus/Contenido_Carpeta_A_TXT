package Utiles.ClasesUtiles.Colecciones;



public class CQueue<T>{
    private CNodeSL<T> head;
    private CNodeSL<T> tail;
    private int Count;

    public CQueue(){
        head = null;
        tail = null;
        Count=0;
    }

    public void Enqueue(T elem){
        if(head == null){
            head = new CNodeSL<T>(elem, null);
            tail = head;
        }
        else{
            tail.next = new CNodeSL<T>(elem, null);
            tail = tail.next;
        }
        Count++;
    }

    public T Dequeue() throws Exception{
        if(head == null)
            throw new Exception("The queue is empty");
        T res = head.value;
        head = head.next;
        Count--;
        return res;
    }

    public T Front() throws Exception{
        if(head == null)
            throw new Exception("The queue is empty");
        return head.value;
    }

    public boolean IsEmpty(){
        return head == null;
    }

    public void Clear(){
        head = null;
    }
    
    public int ObtenerCantReal(){
        return Count;
    } 
}
