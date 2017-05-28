package tieba.utils;

public class ExpToLevel {
	private static final int exp_interval = 100;
	
	public static int getLevelByExp(Integer exp){
		int level = 1;
		while(true){
			exp -= level*exp_interval;
			if(exp>=0){
				level += 1;
			}else{
				break;
			}
		}
		return level;
	}
}
