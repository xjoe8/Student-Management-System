
package lab5.student;


public class Student {
    private int studentID;
    private String fullname;
    private int age;
    private char gender;
    private String department;
    private float GPA;
    
    public Student(int studentID, String fullname, int age, char gender, String department, float GPA){
        this.studentID = studentID;
        this.fullname = fullname;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.GPA = GPA;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public String lineRepresentation(){
        return studentID + "," + fullname + "," + age + "," + gender + "," + department + "," + GPA + "\n";
    }
     
    
}
