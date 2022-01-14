package jdbc.dao;

import jdbc.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InfoDAO extends BaseDAO<Integer, Info> {

    private static final String GET_BY_ID = "SELECT * FROM info WHERE info_id = ?";

    private static final String GET_ALL = "SELECT * FROM info";

    private static final String CREATE =
            "INSERT INTO info(info_student, info_from, tasks_done) VALUES(?, ?, ?)";

    private static final String UPDATE =
            "UPDATE info SET info_student = ?, info_from = ?, tasks_done = ? WHERE info_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM info WHERE info_id = ?";

    private final StudentDAO studentDAO;

    public InfoDAO(Connection connection, StudentDAO studentDAO) {
        super(connection);
        this.studentDAO = studentDAO;
    }

    @Override
    public Info findById(Integer id) {
        Info info = new Info();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                info.setId(resultSet.getInt("info_id"));
                info.setStudent(studentDAO.findById(resultSet.getInt("info_student")));
                info.setStartTime(resultSet.getTimestamp("info_from").toInstant());
                info.setTasksDone(resultSet.getInt("tasks_done"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info;
    }

    @Override
    public List<Info> findAll() {
        List<Info> infoList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Info info = new Info();
                info.setId(resultSet.getInt("info_id"));
                info.setStudent(studentDAO.findById(resultSet.getInt("info_student")));
                info.setStartTime(resultSet.getTimestamp("info_from").toInstant());
                info.setTasksDone(resultSet.getInt("tasks_done"));
                infoList.add(info);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return infoList;
    }

    @Override
    public Info create(Info entity) {
        if (entity == null) {
            throw new RuntimeException("Info can't be null");
        }
        Info info = null;
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                info = this.findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Info update(Info entity) {
        Info info;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            fillPreparedStatement(entity, statement);
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
            info = this.findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info;
    }

    public Info updateBatch(Info entity) {
        Student student = entity.getStudent();
        Mentor mentor = student.getMentor();
        Skill mentorSkill = mentor.getSkill();
        String updateMentorSkill= String.format(
                "UPDATE skills SET skill_name = '%s', skill_type = '%s' WHERE skill_id = %d",
                mentorSkill.getName(), mentorSkill.getType(), mentorSkill.getId());
        Skill studentSkill = student.getSkill();
        String updateStudentSkill= String.format(
                "UPDATE skills SET skill_name = '%s', skill_type = '%s' WHERE skill_id = %d",
                studentSkill.getName(), studentSkill.getType(), studentSkill.getId());
        Project project = student.getProject();
        String updateProject= String.format(
                "UPDATE projects SET project_name = '%s' WHERE project_id = %d",
                project.getName(), project.getId());
        String updateMentor= String.format(
                "UPDATE mentors SET mentor_firstname = '%s', mentor_lastname = '%s', mentor_age = %d, mentor_skill = %d WHERE mentor_id = %d",
                mentor.getFirstName(), mentor.getLastName(), mentor.getAge(), mentorSkill.getId(), mentor.getId());
        String updateStudent= String.format(
                ("UPDATE students SET student_firstname = '%s', student_lastname = '%s', student_age = %d, student_skill = %d, "
                        + "student_mentor = %d, student_project = %d WHERE student_id = %d"),
                student.getFirstName(), student.getLastName(), student.getAge(), studentSkill.getId(),
                mentor.getId(), project.getId(), student.getId());
        String updateInfo= String.format(
                ("UPDATE info SET info_student = %d, info_from = '%s', tasks_done = %d WHERE info_id = %d"),
                student.getId(), Timestamp.from(entity.getStartTime()), entity.getTasksDone(), entity.getId());
        Info info;
        try (Statement statement = this.connection.createStatement()) {
            if (mentorSkill.getId() == 0 && studentSkill.getId() == 0 && project.getId() == 0 && mentor.getId() == 0
                    && student.getId() == 0 && entity.getId() == 0) {
                throw new RuntimeException("Update error");
            }
            statement.addBatch(updateMentorSkill);
            statement.addBatch(updateStudentSkill);
            statement.addBatch(updateProject);
            statement.addBatch(updateMentor);
            statement.addBatch(updateStudent);
            statement.addBatch(updateInfo);
            info = this.findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info;
    }

    private void fillPreparedStatement(Info entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, getStudentId(entity));
        statement.setTimestamp(2, Timestamp.from(entity.getStartTime()));
        statement.setInt(3, entity.getTasksDone());
    }

    private int getStudentId(Info entity) {
        Student student = entity.getStudent();
        if (student == null) {
            throw new RuntimeException("Student can't be null");
        }
        int studentId = student.getId();
        if (studentId == 0) {
            return studentDAO.create(student).getId();
        }
        student = studentDAO.findById(student.getId());
        if (student == null) {
            throw new RuntimeException("Student is not in database");
        }
        return student.getId();
    }
}
