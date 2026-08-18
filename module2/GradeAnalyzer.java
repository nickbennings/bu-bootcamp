import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    public static void main(String[] args) {
        // Read scores from file
        ArrayList<Integer> scores = readScores("scores.txt");

        // Handle empty file or no valid scores
        if (scores.isEmpty()) {
            System.out.println("No valid scores were found.");
            return;
        }

        // Calculate average
        double avg = calculateAverage(scores);

        // Find highest and lowest scores
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }

            if (score < lowest) {
                lowest = score;
            }
        }

        // Write and print report
        writeReport(scores, avg, highest, lowest, "report.txt");
    }

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip blank lines
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    int score = Integer.parseInt(line);

                    if (score >= 0 && score <= 100) {
                        scores.add(score);
                    } else {
                        System.out.println("Warning: score out of range skipped: " + line);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Warning: invalid score skipped: " + line);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }

        return scores;
    }

    // Returns the average of a list of scores
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (int score : scores) {
            total += score;
        }

        return total / scores.size();
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {

        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        // Count grade bands
        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;

            line = "=== Grade Analysis Report ===";
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("Total scores processed: %d", scores.size());
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            System.out.println();

            line = String.format("Average score: %.2f", avg);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("Highest score: %d", high);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("Lowest score: %d", low);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            System.out.println();

            line = "Grade distribution:";
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("  A (90-100):   %d", countA);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("  B (80-89):    %d", countB);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("  C (70-79):    %d", countC);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("  D (60-69):    %d", countD);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            line = String.format("  F (below 60): %d", countF);
            System.out.println(line);
            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing report file.");
        }
    }
}