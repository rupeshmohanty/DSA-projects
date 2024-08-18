package StudentRecordManagement;

public class StudentRecordManagement {

    private ListNode head;

    public static class ListNode {
        private Student data;
        private ListNode next;

        public ListNode (Student data) {
            this.data = data;
        }
    }

    // show student records
    public void showRecord () {
        if (head == null){
            System.out.println("No student records in the system!");
            return;
        }

        ListNode currentNode = head;
        while (currentNode != null) {
            System.out.println("Student name: " + currentNode.data.getName());
            System.out.println("Student roll no: " + currentNode.data.getRollNo());
            System.out.println("Student course: " + currentNode.data.getCourse());
            System.out.println("Student total marks: " + currentNode.data.getTotalMarks());
            System.out.println("---------------------------------------------------------------");
            currentNode = currentNode.next;
        }
    }

    // Check student record
    public boolean checkRecord (Student value) {
        int rollNo = value.getRollNo();

        if (head != null) {
            ListNode currentNode = head;
            while (currentNode != null) {
                if (currentNode.data.getRollNo() == rollNo) {
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    // Create student record
    public void createRecord (Student value) {
        ListNode newStudent = new ListNode(value);

        if (head == null) {
            head = newStudent;
        } else {
            if (checkRecord(value)) {
                System.out.println("Student record already exists!");
            } else {
                if (head.next == null) {
                    head.next = newStudent;
                } else {
                    ListNode previousNode = null;
                    ListNode currentNode = head;
                    while (currentNode != null && currentNode.data.getRollNo() < value.getRollNo()) {
                        previousNode = currentNode;
                        currentNode = currentNode.next;
                    }

                    previousNode.next = newStudent;
                    newStudent.next = currentNode;
                }
            }
        }
    }

    // Search student record
    public boolean searchRecord (int rollNo) {
        if (head == null) {
            return false;
        }

        ListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.getRollNo() == rollNo) {
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    // Delete student record
    public int deleteRecord (int rollNo) {
        if (head == null) {
            return -1;
        } else {
            if (searchRecord(rollNo)) {
                ListNode previousNode = null;
                ListNode currentNode = head;

                if (currentNode != null && currentNode.data.getRollNo() == rollNo) {
                    head = head.next;
                    return 0;
                }

                while (currentNode.next != null && currentNode.data.getRollNo() != rollNo) {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }

                previousNode.next = currentNode.next;
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        StudentRecordManagement srm = new StudentRecordManagement();

        // Create student records
        Student student1 = new Student();
        student1.setName("Jack");
        student1.setRollNo(1);
        student1.setCourse("AI and ML");
        student1.setTotalMarks(65);

        Student student2 = new Student();
        student2.setName("Tom");
        student2.setRollNo(3);
        student2.setCourse("Backend Development");
        student2.setTotalMarks(70);

        Student student3 = new Student();
        student3.setName("Finn");
        student3.setRollNo(2);
        student3.setCourse("IoT Development");
        student3.setTotalMarks(56);

        // Insert student record
        srm.createRecord(student1);
        srm.createRecord(student2);
        srm.createRecord(student3);
        System.out.println("Student records in system after inserting record(s): ");
        srm.showRecord();

        // Delete student record
        int status = srm.deleteRecord(1);
        if (status == 0) {
            System.out.println("Student records in system after deleting record: ");
            srm.showRecord();
        } else {
            System.out.println("The student record does not exist");
        }
    }
}
