public class LinkedList {

    private static class ListNode{
        private int data;
        private ListNode next;

        public ListNode(int data){
            this.data = data;
            this.next = null;
        }

    }

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        printNodes(head);
    }

    private static void printNodes(ListNode head) {
        System.out.print("LinkedList: ");
        while(head!=null) {
            System.out.print(head.data+" ");
            head = head.next;
        }
    }
}

