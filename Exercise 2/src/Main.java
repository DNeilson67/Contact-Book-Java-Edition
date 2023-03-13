import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static class verification<A> {
        public int error;

        public static int errorVerif (int error){
            if (error == 1) {
                System.out.print("Failed\n");
                System.out.println("Cause of errors: One or more numbers are not integers.");
                return error;
            }
            else if (error == 2){
                System.out.print("Failed\n");
                System.out.println("Cause of errors: No username.");
                return error;
            }
            else if (error == 3){
                System.out.print("Failed"+"\nCause of errors: Invalid email format. Make sure @ and domains are included and also spaces are not allowed.\n");
                return error;
            }
            else if (error == -1){
                System.out.print("Failed\n");
                System.out.println("Cause of errors: No inputs.");
                return error;
            }
            else if (error == -2){
                System.out.print("Failed\n");
                System.out.println("Cause of errors: Duplicated information are disallowed.");
                return error;
            }
            else {
                System.out.println("Success");
                return error;
            }
        }
    }
    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        private Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }

    public static class KWSingleLinkedList<E> {
        private Node<E> head = null;
        private int size = 0;

        public void addFirst(E item) {
            head = new Node<>(item, head);
            size++;
        }
        public void printList(){
            Node<E> nodeRef = this.head;
            while (nodeRef != null){
                System.out.println(nodeRef.data);
                nodeRef = nodeRef.next;
            }
            System.out.printf("\n");
        }
        private Node<E> getNode(int index)
        {
            Node<E> node = head;
            for(int i = 0; i < index && node != null; i++)
            {
                node = node.next;
            }
            return node;
        }

        public E get(int index)
        {
            if(index < 0 || index >= size){
                throw new IndexOutOfBoundsException(Integer.toString(index)); //if out of bounds, throw an exception
            }
            Node<E> node =  getNode(index);
            return node.data; //otherwise return the data
        }

        public E remove(int index)
        {
            if(index < 0 || index > size){
                throw new IndexOutOfBoundsException(Integer.toString(index)); //if out of bounds, throw an exception
            }
            if(index == 0){
                return removeFirst();//if the index is the head, add the element as the head
            }
            else{ //otherwise add the new data at the given index
                Node<E> node = getNode(index-1);
                return removeAfter(node);
            }
        }

        private E removeAfter(Node<E> node)
        {
            Node<E> temp = node.next; //temporary reference to the node we will remove
            if(temp != null) //if there is a node to remove
            {
                node.next = temp.next; //remove it
                size--; //decrease size
                return temp.data; //return the removed data
            }
            return null; //otherwise return null
        }

        private E removeFirst()
        {
            Node<E> temp = head;
            if(head != null) //if there is a head
            {
                head = head.next; //remove the head and reference next item
                size--;//decrease the size
                return temp.data; //return the former head's data
            }
            return null; //return null if there is no head
        }

    }

    public static void main(String[] args) {
        String name = "";
        String number = "";
        String email = "";
        int error = 0;
        KWSingleLinkedList<String> names = new KWSingleLinkedList<>();
        KWSingleLinkedList<String> numbers = new KWSingleLinkedList<>();
        KWSingleLinkedList<String> emails = new KWSingleLinkedList<>();
        verification<String> verification = new verification<>();
        Scanner scan = new Scanner(System.in);
        while (true) {
            int quit = 0;
            System.out.println("*****************************\n" +
                    "(A)dd\n" +
                    "(D)elete\n" +
                    "(E)mail Search\n" +
                    "(P)rint List\n" +
                    "(S)earch\n" +
                    "(Q)uit\n" +
                    "*****************************\n" +
                    "Please Enter a command:");
            String commands = scan.nextLine();
            switch (commands) {
                case "A", "a":
                    while (true) {
                        System.out.print("Name: ");
                        name = scan.nextLine();
                        if (name.length() == 0) {
                            System.out.println("Empty String! Please Try again.");
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        error = 0;
                        System.out.print("Phone Number:");
                        number = scan.nextLine();
                        System.out.print("Verification status: ");
                        String[] splitNumber = number.split("");
                        String[] existNumbers = "0123456789".split("");

                        if (number.length() > 0) {
                            for (int i = 0; i < numbers.size; i++) {
                                if (Objects.equals(number, numbers.get(i))) {
                                    error = -2;
                                    break;
                                }
                            }

                            if (error == 0) {
                                for (int i = 0; i < number.length(); i++) {
                                    for (int j = 0; j <= 10; j++) {
                                        if (j == 10) {
                                            error = 1;
                                            break;
                                        } else if (j < 10) {
                                            if (Objects.equals(existNumbers[j], splitNumber[i])) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            error = -1;
                        }
                        error = verification.errorVerif(error);
                        if (error == 0){
                            break;
                        }
                    }

                    while (true) {
                        System.out.print("Email: ");
                        email = scan.nextLine();
                        System.out.print("Verification status: ");
                        if (Objects.equals(email, "@")){
                            error = 3;
                        }
                        else {
                            String[] userName = email.split("@");
                            if (email == "") {
                                error = -1;
                            } else if (userName[0] == "") {
                                error = 2;
                            }
                            else if ((email.contains("@")) && !(email.contains(" ")) && ((email.contains(".com")) || (email.contains(".net")) || (email.contains(".org")) || (email.contains(".biz")) || (email.contains(".info")) || (email.contains(".co.id")) || (email.contains(".or.id")) || (email.contains(".ac.id")) || (email.contains(".sch.id")))) {
                                break;
                            } else {
                                error = 3;
                            }
                        }
                        verification.errorVerif(error);
                        }

                    names.addFirst(name);
                    numbers.addFirst(number);
                    emails.addFirst(email);

                    System.out.printf("Name: %s\n" +
                            "Phone Number: %s\n" +
                            "Email: %s\n" +
                            "The contact has been added successfully.\n", name, number, email);
                    break;
                case "D","d":
                    if (names.size <= 0){
                        System.out.println("There are no existing contacts in the contact books.\nThe operations has been cancelled.");
                        break;
                    }

                    System.out.println("Names list:");
                    names.printList();
                    System.out.println("Input a name that you want to delete:");
                    String selectedNames = scan.nextLine();
                    for (int i = 0; i < names.size; i++) {
                        if (Objects.equals(names.get(i), selectedNames)){
                            System.out.println("We found a matching name from the contact book, here's the detailed information:");
                            System.out.printf("Name: %s\nPhone Number: %s\nEmail: %s\n", names.get(i), numbers.get(i), emails.get(i));
                            System.out.println("Are you sure you want to delete this? (Y/N)");
                            String confirm = scan.nextLine();
                            if (Objects.equals(confirm, "Y") || Objects.equals(confirm, "y")) {
                            names.remove(i);
                            emails.remove(i);
                            numbers.remove(i);
                            System.out.println("The contact has been deleted successfully.");
                            }
                            else {
                                System.out.println("The operations has been cancelled.");
                                break;
                            }
                        }
                        else{
                            System.out.printf("There is no '%s' in the contact book.", selectedNames);
                        }
                    }
                    break;
                case "E":
                    System.out.println("Email lists:");
                    emails.printList();
                    System.out.println("Input an existing email from the list:");
                    String selectedEmails = scan.nextLine();

                    for (int i = 0; i < emails.size; i++) {
                        if (Objects.equals(emails.get(i), selectedEmails)) {
                            System.out.println("We found a matching email address from the contact book, here's the detailed information:");
                            System.out.printf("Name: %s\nPhone Number: %s\nEmail: %s\n", names.get(i), numbers.get(i), emails.get(i));
                        }
                    }
                    break;
                case "P":
                    if (names.size < 1){
                        System.out.println("There are no existing contacts in the contact book.\n");
                    }
                    else {
                        System.out.println("Names list:");
                        names.printList();
                        System.out.println("Phone Numbers list:");
                        numbers.printList();
                        System.out.println("Emails list:");
                        emails.printList();
                    }
                    break;
                case "S":
                    System.out.println("Which type of list do you want to search (1/2/3)\n1. Names\n2.Phone Numbers\n3.Emails");
                    String choice = scan.nextLine();
                    if (Objects.equals(choice, "1")){
                        System.out.println("Input name:");
                        String searchNames = scan.nextLine();
                        for (int i = 0; i < names.size; i++) {
                            if (Objects.equals(names.get(i), searchNames)) {
                                System.out.println("We found a matching name from the contact book, here's the detailed information:");
                                System.out.printf("Name: %s\nPhone Number: %s\nEmail: %s\n", names.get(i), numbers.get(i), emails.get(i));
                            }
                            else{
                                System.out.println("Data not found.");
                            }
                        }
                    }
                    else if (Objects.equals(choice,"2")){
                        System.out.println("Phone Numbers list:");
                        numbers.printList();
                        System.out.println("Input numbers:");
                        String selectedNumbers = scan.nextLine();
                        for (int i = 0; i < numbers.size; i++) {
                            if (Objects.equals(numbers.get(i), selectedNumbers)) {
                                System.out.println("We found a matching Phone Number from the contact book, here's the detailed information:");
                                System.out.printf("Name: %s\nPhone Number: %s\nEmail: %s\n", names.get(i), numbers.get(i), emails.get(i));
                            }
                            else{
                                System.out.println("Data not found.");
                            }
                        }
                    }
                    else if (Objects.equals(choice,"3")){
                        System.out.println("Emails list:");
                        emails.printList();
                        System.out.println("Input Emails:");
                        selectedEmails = scan.nextLine();
                        for (int i = 0; i < emails.size; i++) {
                            if (Objects.equals(emails.get(i), selectedEmails)) {
                                System.out.println("We found a matching Email from the contact book, here's the detailed information:");
                                System.out.printf("Name: %s\nPhone Number: %s\nEmail: %s\n", names.get(i), numbers.get(i), emails.get(i));
                            }
                            else{
                                System.out.println("Data not found.");
                            }
                        }
                    }

                    break;
                case "Q":
                    System.out.println("Thank you for using our services.");
                    quit = 1;
                    break;
                default:
                    System.out.println("Unknown commands.");
                    break;
            }
            if (quit == 1){
                break;
            }
        }
    }
}