import java.io.*;
import java.util.*;

public class FileHandler {
    private final File dataFile;

    public FileHandler(String filePath) {
        this.dataFile = new File(filePath);
        try {
            if (!dataFile.exists()) dataFile.createNewFile();
        } catch (IOException e) {
            System.err.println("Error creating data file: " + e.getMessage());
        }
    }

    // Load all students from file
    public List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();
        if (!dataFile.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                Student s = Student.fromCSV(line);
                if (s != null) list.add(s);
            }
        } catch (IOException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
        return list;
    }

    // Save all students (overwrite)
    public void saveStudents(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(dataFile))) {
            for (Student s : students) pw.println(s.toCSV());
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    // Append a new student
    public void addStudent(Student s) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(dataFile, true))) {
            pw.println(s.toCSV());
        } catch (IOException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    // Update student data by ID
    public boolean updateStudent(Student updated) {
        List<Student> all = loadStudents();
        boolean found = false;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == updated.getId()) {
                all.set(i, updated);
                found = true;
                break;
            }
        }
        if (found) saveStudents(all);
        return found;
    }

    // Delete student by ID
    public boolean deleteStudentById(int id) {
        List<Student> all = loadStudents();
        boolean removed = all.removeIf(s -> s.getId() == id);
        if (removed) saveStudents(all);
        return removed;
    }
}
