public class Student {
    private int id;
    private String fullName;
    private int age;
    private String gender;
    private String department;
    private double gpa;

    public Student(int id, String fullName, int age, String gender, String department, double gpa) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.gpa = gpa;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public String toCSV() {
        return id + "," + escape(fullName) + "," + age + "," + gender + "," + escape(department) + "," + gpa;
    }

    public static Student fromCSV(String line) {
        String[] parts = CSV.split(line);
        if (parts.length < 6) return null;
        int id = Integer.parseInt(parts[0]);
        String fullName = CSV.unescape(parts[1]);
        int age = Integer.parseInt(parts[2]);
        String gender = parts[3];
        String department = CSV.unescape(parts[4]);
        double gpa = Double.parseDouble(parts[5]);
        return new Student(id, fullName, age, gender, department, gpa);
    }

    private static String escape(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }

    private static class CSV {
        static String[] split(String line) {
            java.util.List<String> out = new java.util.ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean inQ = false;
            for (int i=0;i<line.length();i++) {
                char c = line.charAt(i);
                if (inQ) {
                    if (c=='"') {
                        if (i+1<line.length() && line.charAt(i+1)=='"') { sb.append('"'); i++; }
                        else { inQ = false; }
                    } else {
                        sb.append(c);
                    }
                } else {
                    if (c=='"') inQ = true;
                    else if (c==',') { out.add(sb.toString()); sb.setLength(0); }
                    else sb.append(c);
                }
            }
            out.add(sb.toString());
            return out.toArray(new String[0]);
        }
        static String unescape(String s) { return s == null ? "" : s; }
    }
}
