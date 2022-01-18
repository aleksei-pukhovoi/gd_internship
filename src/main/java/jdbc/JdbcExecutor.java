package jdbc;

import jdbc.dao.*;
import jdbc.entity.*;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;

public class JdbcExecutor {
    public static void main(String[] args) {
        JdbcExecutor executor = new JdbcExecutor();
        Connection connection = null;
        try {
            connection = executor.getConnection();
            connection.setAutoCommit(false);

            SkillDAO skillDAO = new SkillDAO(connection);
            ProjectDAO projectDAO = new ProjectDAO(connection);
            MentorDAO mentorDAO = new MentorDAO(connection, skillDAO);
            StudentDAO studentDAO = new StudentDAO(connection, skillDAO, mentorDAO, projectDAO);
            InfoDAO infoDAO = new InfoDAO(connection, studentDAO, mentorDAO, skillDAO, projectDAO);

            System.out.print("Read one row: ");
            Info byId = infoDAO.findById(10);
            System.out.println(byId);
            System.out.println("Read all rows: ");
            List<Info> infoList = infoDAO.findAll();
            System.out.println(infoList);
            System.out.println("+++++++++++++++++++++++");

            //create
            System.out.print("Create new row: ");
            Skill studentSkill = executor.createSkill("Java", "Backend");
            Skill mentorSkill = executor.createSkill("Spring", "Backend");
            Mentor mentor = executor.createMentor(mentorSkill);
            Project project = executor.createProject();
            Student student = executor.createStudent(studentSkill, mentor, project);
            Info info = executor.createInfo(student);
            Info infoDB = infoDAO.create(info);
            System.out.println("Create info: " + infoDB);
            System.out.println("+++++++++++++++++++++++");

            //update
            System.out.println("Update last row: ");
            Student studentUpdate = infoDB.getStudent();
            studentUpdate.setFirstName("Petr");
            Mentor mentorUpdate = studentUpdate.getMentor();
            mentorUpdate.setFirstName("Vasiliy");
            Project projectUpdate = studentUpdate.getProject();
            projectUpdate.setName("Genom");
            infoDB.setTasksDone(6);
            infoDB.setStartTime(Instant.now());
            Info infoUpdate = infoDAO.update(infoDB);
            System.out.println(infoUpdate);

            System.out.println("Update last row with batch: ");
            studentUpdate.setFirstName("Igor");
            infoDB.setStartTime(Instant.now().minus(30, ChronoUnit.DAYS));
            infoDB.setTasksDone(8);
            Info infoUpdateBatch = infoDAO.updateBatch(infoDB);
            System.out.println(infoUpdateBatch);
            System.out.println("+++++++++++++++++++++++");

            //delete
            System.out.println("Delete last row: ");
            infoDAO.delete(infoUpdate.getId());
            System.out.println("Read all rows: ");
            System.out.println(infoDAO.findAll());

            connection.commit();

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/internship";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("useUnicode", "true");
        return DriverManager.getConnection(url, properties);
    }

    private Info createInfo(Student newStudent) {
        Info info = new Info();
        info.setStudent(newStudent);
        info.setStartTime(Instant.now().minus(20, ChronoUnit.DAYS));
        info.setTasksDone(5);
        return info;
    }

    private Skill createSkill(String name, String type) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setType(type);
        return skill;
    }

    private Project createProject() {
        Project project = new Project();
        project.setName("Parking");
        return project;
    }

    private Mentor createMentor(Skill newSkill) {
        Mentor mentor = new Mentor();
        mentor.setFirstName("Egor");
        mentor.setLastName("Kotov");
        mentor.setAge(35);
        mentor.setSkill(newSkill);
        return mentor;
    }

    private Student createStudent(Skill newSkill, Mentor newMentor, Project newProject) {
        Student student = new Student();
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");
        student.setAge(24);
        student.setSkill(newSkill);
        student.setMentor(newMentor);
        student.setProject(newProject);
        return student;
    }
}
