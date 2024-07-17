package edu.duke.ece651.team2.courseManagement;

import edu.duke.ece651.team2.shared.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    public List<String> getLines(String filename, boolean hasHeader) throws IOException {
        List<String> lines = new ArrayList<>();
        FileReader filereader;
        BufferedReader bufferedReader;

        filereader = new FileReader(filename);
        bufferedReader = new BufferedReader(filereader);

        String line;
        if (hasHeader) {
            bufferedReader.readLine();
        }

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        bufferedReader.close();

        return lines;
    }

    public List<Student> getStudents(List<String> lines, String delimiter, int legalNameIndex, int emailIndex, int displayNameindex, int universityId) {
        List<Student> students = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(delimiter);
            Student student = new Student(values[legalNameIndex], values[emailIndex], universityId, values[displayNameindex]);
            students.add(student);
        }
        return students;
    }

    public List<Integer> getIds(List<String> lines) {
        List<Integer> ids = new ArrayList<>();
        for(String line : lines) {
            ids.add(Integer.valueOf(line));
        }
        return ids;
    }

}
