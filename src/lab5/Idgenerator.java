import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private final File seqFile;
    private final AtomicInteger current;

    public IdGenerator(String seqFilePath, int startAt) {
        this.seqFile = new File(seqFilePath);
        this.current = new AtomicInteger(readLastId(startAt));
    }

    private int readLastId(int fallback) {
        if (!seqFile.exists()) return fallback;
        try (BufferedReader br = new BufferedReader(new FileReader(seqFile))) {
            String s = br.readLine();
            return s == null ? fallback : Integer.parseInt(s.trim());
        } catch (Exception e) {
            return fallback;
        }
    }

    public synchronized int nextId() {
        int id = current.incrementAndGet();
        try (PrintWriter pw = new PrintWriter(new FileWriter(seqFile))) {
            pw.println(id);
        } catch (IOException ignored) {}
        return id;
    }
}
