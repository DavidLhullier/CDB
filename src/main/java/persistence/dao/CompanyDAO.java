package persistence.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.RowMapper;

import model.Company;
import persistence.binding.mapper.CompanyMapper;

@Repository
public class CompanyDAO {

	private final String REQUEST_GET_ALL_COMPANY = "SELECT id, name FROM company;";
	private final String REQUEST_GET_ONE_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id = :id ;";

	private final String REQUEST_START_TRANSACTION = "START TRANSACTION ;";
	private final String REQUEST_ROLLBACK = "ROLLBACK;";
	private final String REQUEST_COMMIT = "COMMIT;";

	private final String REQUEST_DELETE_ONE_COMPANY_BY_ID = "DELETE FROM company WHERE id = ? ;";
	private final String REQUEST_DELETE_COMPUTER_BY_COMPANY_ID = "DELETE FROM computer WHERE company_id = ? ;";

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	private CompanyMapper companyMapper;

	public CompanyDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate,
			CompanyMapper companyMapper) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
		this.companyMapper = companyMapper;
	}

	//@Override
	public List<Company> getAllCompany() {
		return jdbcTemplate.query(REQUEST_GET_ALL_COMPANY, companyMapper);
	}

	public Company getCompanyById(int id) {		
		MapSqlParameterSource requestParameter = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.query(REQUEST_GET_ONE_COMPANY_BY_ID, requestParameter, companyMapper).get(0);
	}

	@Transactional(rollbackFor = { Exception.class })
	public void deleteCompanyById(int id) {
		
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		

		/*
		 PreparedStatement request = con.prepareStatement(
					REQUEST_START_TRANSACTION );
				request.execute();
				
		SqlParameterSource request = new MapSqlParameterSource().addValue("id", id);
		
		namedParameterJdbcTemplate.update(REQUEST_DELETE_COMPUTER_BY_COMPANY_ID, request);			
		namedParameterJdbcTemplate.update(REQUEST_DELETE_ONE_COMPANY_BY_ID, request);
		

		PreparedStatement commitRequest = con.prepareStatement(REQUEST_COMMIT);
		commitRequest.execute();
		*/
		
		/*
		try(Connection con = DataSource.getConnection();) {


			try( PreparedStatement request = con.prepareStatement(
					REQUEST_START_TRANSACTION ); ){
				request.execute();


				PreparedStatement deleteComputerRequest = con.prepareStatement(
						REQUEST_DELETE_COMPUTER_BY_COMPANY_ID ); 
				deleteComputerRequest.setInt(1, id);
				deleteComputerRequest.executeUpdate();


				PreparedStatement deleteComapnyRequest = con.prepareStatement(
						REQUEST_DELETE_ONE_COMPANY_BY_ID ); 
				deleteComapnyRequest.setInt(1, id);
				deleteComapnyRequest.executeUpdate();


				PreparedStatement commitRequest = con.prepareStatement(REQUEST_COMMIT);
				commitRequest.execute();

				CDBLogger.logInfo(ComputerDAO.class.toString(), "Company and Computer delete");

			} catch (SQLException e) {
				e.printStackTrace();
				PreparedStatement rollbackRequest = con.prepareStatement(REQUEST_ROLLBACK);
				rollbackRequest.execute();
				CDBLogger.logInfo(ComputerDAO.class.toString(), "Company and Computer not delete");

			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
*/


	}


}