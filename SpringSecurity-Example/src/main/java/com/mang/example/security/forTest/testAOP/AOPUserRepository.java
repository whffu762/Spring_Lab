package com.mang.example.security.forTest.testAOP;

import com.mang.example.security.enums.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO USER(user_email, role) VALUES (?, ?)";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, user.getUserEmail());
        statement.setString(2, user.getRole().getValue());

        close(con, statement, null);    //반드시 닫아줘야 하기 때문에 예외가 터져도 닫히게끔 해야 함

        return user;
    }

    public void update(String userEmail) throws SQLException{
        String sql = "UPDATE USER SET role=? WHERE user_email=?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, UserRole.ADMIN.getValue());
        statement.setString(2, userEmail);

        close(con, statement, null);
    }

    public List<UserDTO> findAll() throws SQLException{
        String sql = "SELECT * FROM USER";

        Connection con = getConnection();;
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        List<UserDTO> result = new ArrayList<>();
        while (rs.next()){
            result.add(UserDTO.builder()
                    .userEmail(rs.getString("user_email"))
                    .role(UserRole.valueOf(rs.getString("role")))
                    .build());
        }

        close(con, statement, rs);

        return result;
    }
}
