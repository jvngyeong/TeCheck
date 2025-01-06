package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

import DDL.command.FindCommand;

@Mapper
public interface HelpMapper {
	String findId(FindCommand findIdCommand);

	String userCheck(FindCommand findCommand);

	void pwChange(FindCommand findCommand);
}
