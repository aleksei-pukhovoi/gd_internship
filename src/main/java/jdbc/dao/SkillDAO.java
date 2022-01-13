package jdbc.dao;

import jdbc.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDAO extends BaseDAO<Integer, Skill> {

    private static final String GET_BY_ID = "SELECT * FROM skills WHERE skill_id = ?";

    private static final String GET_ALL = "SELECT * FROM skills";

    private static final String CREATE = "INSERT INTO skills(skill_name, skill_type) VALUES(?,?)";

    private static final String UPDATE = "UPDATE skills SET skill_name = ?, skill_type = ? WHERE skill_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM skills WHERE skill_id = ?";

    public SkillDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Skill findById(Integer id) {
        Skill skill = new Skill();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                skill.setId(resultSet.getInt("skill_id"));
                skill.setName(resultSet.getString("skill_name"));
                skill.setType(resultSet.getString("skill_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    @Override
    public List<Skill> findAll() {
        List<Skill> skillList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getInt("skill_id"));
                skill.setName(resultSet.getString("skill_name"));
                skill.setType(resultSet.getString("skill_type"));
                skillList.add(skill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skillList;
    }

    @Override
    public Skill create(Skill entity) {
        if (entity == null) {
            throw new RuntimeException("Skill can't be null");
        }
        Skill skill = null;
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                skill = this.findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skill;
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
    public Skill update(Skill entity) {
        Skill skill;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)) {
            fillPreparedStatement(entity, statement);
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
            skill = this.findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    private void fillPreparedStatement(Skill entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getType());
    }
}
