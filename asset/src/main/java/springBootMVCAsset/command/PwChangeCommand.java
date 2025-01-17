package springBootMVCAsset.command;

import lombok.Data;

@Data
public class PwChangeCommand {
	String userNum;
	String grade;
	String newPw;
	String newPwCon;
}
