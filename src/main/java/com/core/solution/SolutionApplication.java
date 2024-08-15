package com.core.solution;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ComponentScan("com.core.solution")
@MapperScan("com.core.solution.mapper")
@SpringBootApplication()
public class SolutionApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SolutionApplication.class, args);
	}

	@Override
	public void run(String... args) {

		/*
		 * String sql =
		 * "INSERT INTO public.config_autoservicio(name_table, fields, name_file, enabled, creation_at, modification_at) VALUES ('demo1', '1,2,3', 'demo.xlsx', true, NOW(), NOW())"
		 * ; int rows = jdbcTemplate.update(sql); if (rows > 0) {
		 * System.out.println("A new row has been inserted."); }
		 */

		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		String table =  "public.config_autoservicio";
		List<String> columnNames = new ArrayList<String>();

		try {
			
			DataSource dataSource = jdbcTemplate.getDataSource();
			
			connection = DataSourceUtils.getConnection(dataSource);
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ".concat(table));
			
			if (rs != null) {
				ResultSetMetaData columns = rs.getMetaData();
				int i = 0;
				while (i < columns.getColumnCount()) {
					i++;
					System.out.print(columns.getColumnName(i) + "\t");
					columnNames.add(columns.getColumnName(i));
				}
				System.out.print("\n");
				while (rs.next()) {
					for (i = 0; i < columnNames.size(); i++) {
						System.out.print(rs.getString(columnNames.get(i)) + "\t");
					}
					System.out.print("\n");
				}
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			
			try {
				
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

}
