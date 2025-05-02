// package com.dev.attendance.Repository.emp;

// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Timestamp;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.stereotype.Repository;

// import com.dev.attendance.DTO.EmpDTO;
// import com.dev.attendance.DTO.request.EmpCreateRequest;
// import com.dev.attendance.DTO.response.EmpResponse;



// @Repository
// public class EmpRepository {
    
//     public void redgEmp(JdbcTemplate jdbcTemplate,EmpCreateRequest request){
//         String sql = "insert into emp(id, name, teamName, role, birthday, workStartDate) values (?,?,?,?,?,SYSDATE())";
    
//         jdbcTemplate.update(sql, request);
//     }

//     public List<EmpDTO> getAllEmp(JdbcTemplate jdbcTemplate){
//         EmpDTO dto = new EmpDTO();
//         String sql = "select * from emp";
        
//         return jdbcTemplate.query(sql, new RowMapper<EmpDTO>(){
//             @Override
//             public EmpDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
//             long id = rs.getLong("id");
//             String name = rs.getString("name");
//             String teamName = rs.getString("teamName");
//             String role = rs.getString("role");

//             LocalDateTime birthdayTimestamp = rs.getTimestamp("birthday").toLocalDateTime();
//             LocalDateTime workStartDateTimestamp = rs.getTimestamp("workStartDate").toLocalDateTime();

//             return new EmpDTO(id, name, teamName, role, birthdayTimestamp, workStartDateTimestamp);
//             }
//         });
//     }
    
// }
