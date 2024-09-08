package com.mang.example.security.forTest.testAOP;

import com.mang.example.security.enums.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AOPUserRepository {

    private final DataSource dataSource;

    private Connection getConnection() throws SQLException{
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    public UserDTO save(UserDTO user) throws SQLException {
        String sql = "INSERT INTO MEMBER (name, role) VALUES (?, ?)";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, user.getName());

        if(user.getRole() == null){
            statement.setString(2, null);
        } else {
            statement.setString(2, user.getRole().getValue());
        }
        statement.executeUpdate();

        close(con, statement, null);    //반드시 닫아줘야 하기 때문에 예외가 터져도 닫히게끔 해야 함

        return user;
    }

    public void update(String userEmail) throws SQLException{
        String sql = "UPDATE MEMBER SET role=? WHERE name=?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, UserRole.ADMIN.getValue());
        statement.setString(2, userEmail);
        statement.executeUpdate();

        close(con, statement, null);
    }

    public List<UserDTO> findAll() throws SQLException{
        String sql = "SELECT * FROM MEMBER";

        Connection con = getConnection();;
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        List<UserDTO> result = new ArrayList<>();
        while (rs.next()){
            UserDTO user = UserDTO.builder()
                    .name(rs.getString("name"))
                    .role(UserRole.exchange(rs.getString("role")))
                    .build();

            result.add(user);
        }

        close(con, statement, rs);

        return result;
    }

    public void delete() throws SQLException{
        String sql = "TRUNCATE TABLE MEMBER";

        Connection con = getConnection();;
        PreparedStatement statement = con.prepareStatement(sql);
        statement.executeUpdate();

        close(con, statement, null);
    }

    public UserDTO findByName(String name) throws SQLException{
        String sql = "SELECT * FROM MEMBER WHERE name = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();

        UserDTO result = null;
        if (rs.next()){
            result = UserDTO.builder()
                    .name(rs.getString("name"))
                    .role(UserRole.exchange(rs.getString("role")))
                    .build();
        }

        close(con, statement, rs);
        return result;
    }
}
