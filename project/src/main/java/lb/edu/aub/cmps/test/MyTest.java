package lb.edu.aub.cmps.test;

import java.util.List;

import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.mappers.BuildingMapper;
import lb.edu.aub.cmps.service.BuildingService;
import lb.edu.aub.cmps.service.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;


public class MyTest {
	public static void main(String[] args) {
	//	new BuildingService();
		try {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
			BuildingMapper bm = sqlSession.getMapper(BuildingMapper.class);
			
			List<Building> bldgs = bm.getAllBuildings();
			System.out.println(bldgs.get(0).getName());
			//Department d = departmentMapper.(1);
			//System.out.println(u);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally {
		//	sqlSession.close();
		}
		
		
	}
}
