import java.util.*;

// Interface for scheduling
interface Schedulable {
    void scheduleExam();
}

// Base class
class Exam implements Schedulable {
    protected String subject;
    protected String date;
    protected String time;
    protected String type;

    public Exam(String subject, String date, String time, String type) {
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    // Method overriding (Polymorphism)
    @Override
    public void scheduleExam() {
        System.out.println("Exam scheduled for " + subject + " on " + date + " at " + time);
    }

    public void showDetails() {
        System.out.println("Subject: " + subject);
        System.out.println("Date   : " + date);
        System.out.println("Time   : " + time);
        System.out.println("Type   : " + type);
        System.out.println("-------------------------");
    }
}

// Derived class (Inheritance)
class OnlineExam extends Exam {
    private String meetingLink;

    public OnlineExam(String subject, String date, String time, String link) {
        super(subject, date, time, "Online");
        this.meetingLink = link;
    }

    @Override
    public void scheduleExam() {
        System.out.println("Online Exam scheduled for " + subject + " via link: " + meetingLink);
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Meeting Link: " + meetingLink);
        System.out.println("-------------------------");
    }
}

// Another derived class
class OfflineExam extends Exam {
    private String roomNo;

    public OfflineExam(String subject, String date, String time, String roomNo) {
        super(subject, date, time, "Offline");
        this.roomNo = roomNo;
    }

    @Override
    public void scheduleExam() {
        System.out.println("Offline Exam scheduled for " + subject + " in Room " + roomNo);
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Room No: " + roomNo);
        System.out.println("-------------------------");
    }
}

// Main Class
public class ExamScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Exam> exams = new ArrayList<>();

        int choice;
        do {
            System.out.println("\n=== Exam Scheduler Menu ===");
            System.out.println("1. Add Exam");
            System.out.println("2. View Exams");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Subject: ");
                        String subject = sc.nextLine();
                        System.out.print("Enter Date (DD/MM/YYYY): ");
                        String date = sc.nextLine();
                        System.out.print("Enter Time (HH:MM): ");
                        String time = sc.nextLine();
                        System.out.print("Exam Type (Online/Offline): ");
                        String type = sc.nextLine();

                        Exam exam;
                        if (type.equalsIgnoreCase("Online")) {
                            System.out.print("Enter Meeting Link: ");
                            String link = sc.nextLine();
                            exam = new OnlineExam(subject, date, time, link);
                        } else {
                            System.out.print("Enter Room Number: ");
                            String room = sc.nextLine();
                            exam = new OfflineExam(subject, date, time, room);
                        }

                        exam.scheduleExam();
                        exams.add(exam);
                    } catch (Exception e) {
                        System.out.println("Invalid Input! Try again.");
                        sc.nextLine(); // clear if necessary
                    }
                    break;

                case 2:
                    if (exams.isEmpty()) {
                        System.out.println("No exams scheduled yet.");
                    } else {
                        System.out.println("\n--- Exam Details ---");
                        for (Exam e : exams) {
                            e.showDetails();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting Exam Scheduler...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        sc.close();
    }
}
