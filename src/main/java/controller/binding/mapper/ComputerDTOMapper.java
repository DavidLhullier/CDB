package controller.binding.mapper;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import controller.binding.dto.ComputerAddDTO;
import controller.binding.dto.ValidationDTO;
import logger.CDBLogger;
import model.Company;
import model.Computer;
import model.Computer.ComputerBuilder;

@Component("computerDTOMapper")
public class ComputerDTOMapper {

	@Autowired
	@Qualifier("validationDTO")
	private ValidationDTO vDTO;

	public Optional <Computer> mapToComputer(ComputerAddDTO computerDTO) {		
		try {
			vDTO.valide(computerDTO);
		} catch (Exception e) {
			CDBLogger.logInfo(ComputerDTOMapper.class.toString(), e);
			return Optional.empty();
		}

		ComputerBuilder computerBuilder = new ComputerBuilder();
		
		computerBuilder.setName(computerDTO.getName()).
				setCompany(new Company(Integer.valueOf(computerDTO.getCompanyId()), "tygui"));
		 

		
		if( !computerDTO.getIntroduced().isEmpty() & computerDTO.getIntroduced() != null) {
			computerBuilder.setIntroduced(Date.valueOf(computerDTO.getIntroduced()).toLocalDate());
		}

		if( !computerDTO.getDiscontinued().isEmpty() & computerDTO.getDiscontinued() != null) {
			computerBuilder.setDiscontinued(Date.valueOf(computerDTO.getDiscontinued()).toLocalDate());
		}

		Optional<Computer> computerTmp = Optional.ofNullable(computerBuilder.build());
		return computerTmp;



	}

}
