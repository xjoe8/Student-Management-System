package lab5;

public class Student {
    private int studentID;
    private String fullname;
    private int age;
    private char gender;
    private String department;
    private float GPA;

    public Student(int studentID, String fullname, int age, char gender, String department, float GPA) {
        
        setStudentID(studentID);
        setFullname(fullname);
        setAge(age);
        setGender(gender);
        setDepartment(department);
        setGPA(GPA);
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        if (studentID > 0) {
            this.studentID = studentID;
        } 
        else {
            System.out.println("Invalid student ID. Must be positive.");
        }
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        if (fullname != null && !fullname.trim().isEmpty()) {
            this.fullname = fullname.trim();
        } 
        else {
            System.out.println("Invalid full name. Cannot be empty.");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 16 && age <= 100) {
            this.age = age;
        } 
        else {
            throw new IllegalArgumentException("Invalid age. Must be between 16 and 100.");
        }
    }


    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if ( Character.isLetter(gender) ) {
            gender = Character.toUpperCase(gender);
            if (gender == 'M' || gender == 'F') {
                this.gender = gender;
                return;
            } 
        }
            else {
                System.out.println("Invalid gender. Must be 'M' or 'F'.");
            }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department.trim();
        } 
        else {
            System.out.println("Invalid department. Cannot be empty.");
        }
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        if (GPA >= 0.0f && GPA <= 4.0f) {
            this.GPA = GPA;
        } 
        else {
            System.out.println("Invalid GPA. Must be between 0.0 and 4.0.");
        }
    }

    public String lineRepresentation() {
        return studentID + "," + fullname + "," + age + "," + gender + "," + department + "," + String.format("%.2f", GPA);
    }
}
