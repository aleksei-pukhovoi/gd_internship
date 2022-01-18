package jdbc.dao;

import jdbc.entity.Mentor;
import jdbc.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MentorDAO extends BaseDAO<Integer, Mentor> {

    private static final String GET_BY_ID = "SELECT * FROM mentors WHERE mentor_id = ?";

    private static final String GET_ALL = "SELECT * FROM mentors";

    private static final String CREATE =
            "INSERT INTO mentors(mentor_firstname, mentor_lastname, mentor_age, mentor_skill) VALUES(?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE mentors SET mentor_firstname = ?, mentor_lastname = ?, mentor_age = ?, mentor_skill = ? WHERE mentor_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM mentors WHERE mentor_id = ?";

    private final SkillDAO skillDAO;

    public MentorDAO(Connection connection, SkillDAO skillDAO) {
        super(connection);
        this.skillDAO = skillDAO;
    }

    @Override
    public Mentor findById(Integer id) {
        Mentor mentor = new Mentor();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mentor.setId(resultSet.getInt("mentor_id"));
                mentor.setFirstName(resultSet.getString("mentor_firstname"));
                mentor.setLastName(resultSet.getString("mentor_lastname"));
                mentor.setAge(resultSet.getInt("mentor_age"));
                mentor.setSkill(skillDAO.findById(resultSet.getInt("mentor_skill")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mentor;
    }

    @Override
    public List<Mentor> findAll() {
        List<Mentor> mentorList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Mentor mentor = new Mentor();
                mentor.setId(resultSet.getInt("mentor_id"));
                mentor.setFirstName(resultSet.getString("mentor_firstname"));
                mentor.setLastName(resultSet.getString("mentor_lastname"));
                mentor.setAge(resultSet.getInt("mentor_age"));
                mentor.setSkill(skillDAO.findById(resultSet.getInt("mentor_skill")));
                mentorList.add(mentor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mentorList;
    }

    @Override
    public Mentor create(Mentor entity) {
        if (entity == null) {
            throw new RuntimeException("Mentor can't be null");
        }
        Mentor mentor = null;
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, statement);
            statement.setInt(4, getSkillId(entity));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                mentor = this.findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mentor;
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
    public Mentor update(Mentor entity) {
        Mentor mentor;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            skillDAO.update(entity.getSkill());
            fillPreparedStatement(entity, statement);
            statement.setInt(4, getSkillId(entity));
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
            mentor = this.findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mentor;
    }

    public void fillPreparedStatement(Mentor entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setInt(3, entity.getAge());
    }

    private int getSkillId(Mentor entity) {
        Skill skill = entity.getSkill();
        if (skill == null) {
            throw new RuntimeException("Skill can't be null");
        }
        int skillId = skill.getId();
        if (skillId == 0) {
            return skillDAO.create(skill).getId();
        }
        skill = skillDAO.findById(skill.getId());
        if (skill == null) {
            throw new RuntimeException("Skill is not in database");
        }
        return skill.getId();
    }
}
