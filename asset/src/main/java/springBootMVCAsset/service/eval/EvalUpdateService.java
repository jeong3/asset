package springBootMVCAsset.service.eval;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.EvalCommand;
import springBootMVCAsset.domain.EvalDTO;
import springBootMVCAsset.mapper.EvalMapper;

@Service
public class EvalUpdateService {
	@Autowired
	EvalMapper evalMapper;
	public void execute(EvalCommand evalCommand) {
		EvalDTO dto = new EvalDTO();
		BeanUtils.copyProperties(evalCommand, dto);
		evalMapper.EvalUpdate(dto);
		
	}

}
