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

    private static final String UPDATE_INFO =
            "UPDATE info SET info_student = ?, info_from = ?, tasks_done = ? WHERE info_id = ?";

    private static final String UPDATE_SKILL =
            "UPDATE skills SET skill_name = ?, skill_type = ? WHERE skill_id = ?";

    private static final String UPDATE_PROJECT = "UPDATE projects SET project_name = ? WHERE project_id = ?";

    private static final String UPDATE_MENTOR =
            "UPDATE mentors SET mentor_firstname = ?, mentor_lastname = ?, mentor_age = ?, mentor_skill = ? WHERE mentor_id = ?";

    private static final String UPDATE_STUDENT =
            "UPDATE students SET student_firstname = ?, student_lastname = ?, student_age = ?, student_skill = ?, "
                    + "student_mentor = ?, student_project = ? WHERE student_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM info WHERE info_id = ?";

    private final StudentDAO studentDAO;
    private final MentorDAO mentorDAO;
    private final SkillDAO skillDAO;
    private final ProjectDAO projectDAO;

    public InfoDAO(Connection connection, StudentDAO studentDAO, MentorDAO mentorDAO, SkillDAO skillDAO, ProjectDAO projectDAO) {
        super(connection);
        this.studentDAO = studentDAO;
        this.mentorDAO = mentorDAO;
        this.skillDAO = skillDAO;
        this.projectDAO = projectDAO;
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
            statement.setInt(1, getStudentId(entity));
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
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_INFO)) {
            studentDAO.update(entity.getStudent());
            statement.setInt(1, getStudentId(entity));
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
        Skill studentSkill = student.getSkill();
        Project project = student.getProject();
        Info info;
        try (PreparedStatement psSkill = this.connection.prepareStatement(UPDATE_SKILL);
             PreparedStatement psProject = this.connection.prepareStatement(UPDATE_PROJECT);
             PreparedStatement psMentor = this.connection.prepareStatement(UPDATE_MENTOR);
             PreparedStatement psStudent = this.connection.prepareStatement(UPDATE_STUDENT);
             PreparedStatement psInfo = this.connection.prepareStatement(UPDATE_INFO)) {
            if (mentorSkill.getId() == 0 && studentSkill.getId() == 0 && project.getId() == 0 && mentor.getId() == 0
                    && student.getId() == 0 && entity.getId() == 0) {
                throw new RuntimeException("Update error");
            }
            skillDAO.fillPreparedStatement(mentorSkill, psSkill);
            psSkill.setInt(3, mentorSkill.getId());
            psSkill.addBatch();
            skillDAO.fillPreparedStatement(studentSkill, psSkill);
            psSkill.setInt(3, studentSkill.getId());
            psSkill.addBatch();
            psSkill.executeBatch();
            projectDAO.fillPreparedStatement(project, psProject);
            psProject.setInt(2, project.getId());
            psSkill.addBatch();
            psSkill.executeBatch();
            mentorDAO.fillPreparedStatement(mentor, psMentor);
            psMentor.setInt(4, mentorSkill.getId());
            psMentor.setInt(5, mentor.getId());
            psMentor.addBatch();
            psMentor.executeBatch();
            studentDAO.fillPreparedStatement(student, psStudent);
            psStudent.setInt(4, studentSkill.getId());
            psStudent.setInt(5, mentor.getId());
            psStudent.setInt(6, project.getId());
            psStudent.setInt(7, student.getId());
            psStudent.addBatch();
            psStudent.executeBatch();
            psInfo.setInt(1, student.getId());
            fillPreparedStatement(entity, psInfo);
            psInfo.setInt(4, entity.getId());
            psInfo.addBatch();
            psInfo.executeBatch();
            info = this.findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info;
    }

    private void fillPreparedStatement(Info entity, PreparedStatement statement) throws SQLException {
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
