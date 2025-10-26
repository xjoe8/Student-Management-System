package lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FileHandler {
    private final String filename;
    private final ArrayList<Student> students;
    
    public FileHandler (String filename){
        this.filename = filename;
        this.students = new ArrayList<>();
    }
    
    public void loadStudents(){
        students.clear();
        
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        char gender = parts[3].charAt(0);
                        String dept = parts[4];
                        float gpa = Float.parseFloat(parts[5]);
                        students.add(new Student(id, name, age, gender, dept, gpa));
                    } catch (NumberFormatException ignored) {
                        System.out.println("Skipped invalid student line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
    
    private void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                writer.write(s.lineRepresentation());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
    private boolean validateStudent(Student s) {
        if (s.getFullname() == null || s.getFullname().trim().isEmpty()) return false;
        if (s.getDepartment() == null || s.getDepartment().trim().isEmpty()) return false;
        if (s.getAge() < 16) return false;
        if (s.getGPA() < 0.0 || s.getGPA() > 4.0) return false;
        if (s.getGender() != 'M' && s.getGender() != 'F') return false;
        return true;
    }
    
    public boolean addStudent(Student newStudent) {
        if (!validateStudent(newStudent)) {
            System.out.println("Validation failed for student: " + newStudent.getFullname());
            return false;
        }
        for (Student s : students) {
            if (s.getStudentID() == newStudent.getStudentID()) {
                System.out.println("Duplicate ID: " + newStudent.getStudentID());
                return false;
            }
        }
        students.add(newStudent);
        saveStudents();
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getStudentID() == id) return s;
        }
        return null;
    }

    public ArrayList<Student> searchByName(String name) {
        ArrayList<Student> result = new ArrayList<>();  //for students with the same name
        for (Student s : students) {
            if (s.getFullname().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }
    
    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID() == updatedStudent.getStudentID()) {
                if (!validateStudent(updatedStudent)) 
                    return false;
                students.set(i, updatedStudent);
                saveStudents();
                return true;
            }
        }
        return false;   //if student not found
    }

    public boolean deleteStudent(int id) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getStudentID() == id) {
                it.remove();
                saveStudents();
                return true;
            }
        }
        return false;
    }

    public int getNextStudentID() {
        try {
            Idgenerator idGen = new Idgenerator(filename);
            return idGen.getGenID();
        } 
        catch (FileNotFoundException e) {
            return 1; //if the file doesn’t exist yet, start from 1
        }
    }
}
