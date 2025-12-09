import java.util.Scanner;

public class studentreportcardsystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- CONSTANTS ---
        final int NUM_STUDENTS = 15; // Set to 15 to match sample (Requirements say at least 12)
        final int NUM_SUBJECTS = 6;
        final String[] SUBJECT_NAMES = {"English", "Math", "History", "Geography", "Science", "Programming"};

        // --- ARRAYS FOR STORAGE ---
        String[] studentNames = new String[NUM_STUDENTS];
        double[][] marks = new double[NUM_STUDENTS][NUM_SUBJECTS];
        double[] totalMarks = new double[NUM_STUDENTS];
        char[] ranks = new char[NUM_STUDENTS];

        // --- 1. INPUT SCHOOL DETAILS ---
        System.out.println("--- Enter School Details ---");
        System.out.print("Enter School Name: ");
        String schoolName = scanner.nextLine();

        System.out.print("Enter Teacher Name: ");
        String teacherName = scanner.nextLine();

        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        // --- 2. INPUT STUDENT RECORDS ---
        System.out.println("\n--- Enter Student Records ---");
        for (int i = 0; i < NUM_STUDENTS; i++) {
            System.out.println("Student " + (i + 1) + " Details:");

            System.out.print("Name: ");
            studentNames[i] = scanner.nextLine();

            double sum = 0;
            for (int j = 0; j < NUM_SUBJECTS; j++) {
                System.out.print("   " + SUBJECT_NAMES[j] + ": ");
                marks[i][j] = scanner.nextDouble();
                sum += marks[i][j];
            }
            scanner.nextLine(); // Consume newline

            // --- 3. COMPUTE RESULTS (Total & Rank) ---
            totalMarks[i] = sum;

            if (sum >= 540.0) {
                ranks[i] = 'A';
            } else if (sum >= 480.0) {
                ranks[i] = 'B';
            } else if (sum >= 420.0) {
                ranks[i] = 'C';
            } else if (sum >= 360.0) {
                ranks[i] = 'D';
            } else {
                ranks[i] = 'F';
            }
        }

        // --- 4. COMPUTE CLASS STATISTICS (Vertical Totals & Rank Counts) ---
        double[] subjectTotals = new double[NUM_SUBJECTS];
        double[] subjectAverages = new double[NUM_SUBJECTS];
        double grandTotalOfTotals = 0;

        // Count Ranks
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int i = 0; i < NUM_STUDENTS; i++) {
            // Add to vertical subject totals
            for (int j = 0; j < NUM_SUBJECTS; j++) {
                subjectTotals[j] += marks[i][j];
            }
            grandTotalOfTotals += totalMarks[i];

            // Count Rank
            switch (ranks[i]) {
                case 'A': countA++; break;
                case 'B': countB++; break;
                case 'C': countC++; break;
                case 'D': countD++; break;
                case 'F': countF++; break;
            }
        }

        // Calculate Subject Averages
        for (int j = 0; j < NUM_SUBJECTS; j++) {
            subjectAverages[j] = subjectTotals[j] / NUM_STUDENTS;
        }
        double grandAverage = grandTotalOfTotals / NUM_STUDENTS;


        // --- 5. OUTPUT FORMATTING ---
        // Print Header
        System.out.println("\n\n");
        printLine();
        System.out.printf("%45s %s\n", "School Name:", schoolName);
        System.out.printf("%45s %s\n", "Teacher:", teacherName);
        System.out.printf("%45s %s\n", "Grade:", grade);
        System.out.printf("%45s %d\n", "Year:", year);
        printLine();

        // Print Column Headers
        System.out.printf("%-18s", "Student Name");
        for (String subj : SUBJECT_NAMES) {
            System.out.printf("%-8s", subj); // Dynamic headers
        }
        System.out.printf("%8s %4s\n", "Total", "Rank");
        printLine();

        // Print Student Rows
        for (int i = 0; i < NUM_STUDENTS; i++) {
            System.out.printf("%-18s", studentNames[i]);
            for (int j = 0; j < NUM_SUBJECTS; j++) {
                System.out.printf("%7.2f ", marks[i][j]);
            }
            System.out.printf("%8.2f %4c\n", totalMarks[i], ranks[i]);
        }

        printLine();

        // Print Subject Totals (Footer)
        System.out.printf("%-18s", ""); // Empty space for Name column
        for (int j = 0; j < NUM_SUBJECTS; j++) {
            System.out.printf("%7.2f ", subjectTotals[j]);
        }
        System.out.printf("%8.2f\n", grandTotalOfTotals);

        // Print Line Separator
        System.out.println("---------------------------------------------------------------------------------------------");

        // Print Subject Averages (Footer)
        System.out.printf("%-18s", ""); // Empty space for Name column
        for (int j = 0; j < NUM_SUBJECTS; j++) {
            System.out.printf("%7.2f ", subjectAverages[j]);
        }
        System.out.printf("%8.2f\n", grandAverage);

        printLine();

        // Print Rank Summary
        System.out.printf("      Ranks      A's: %-3d   B's: %-3d   C's: %-3d   D's: %-3d   F's: %-3d\n",
                countA, countB, countC, countD, countF);

        printLine();

        scanner.close();
    }

    // Helper method to print the dashed lines to match the image width
    private static void printLine() {
        System.out.println("=============================================================================================");
    }
}