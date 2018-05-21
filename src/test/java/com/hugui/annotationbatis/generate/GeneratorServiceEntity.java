package com.hugui.annotationbatis.generate;

import org.junit.Test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author HuGui
 * @class com.hugui.annotationbatis.generate GeneratorServiceEntity.java
 * @date 2018年5月20日
 */

public class GeneratorServiceEntity {

	@Test
	public void generateCode() {
		String packageName = "com.hugui.annotationbatis";
		boolean serviceNameStartWithI = true;// user -> UserService, 设置成true: user -> IUserService
		generateByTables(serviceNameStartWithI, packageName, "user", "order");
	}

	private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
		GlobalConfig config = new GlobalConfig();
		String dbUrl = "jdbc:mysql://localhost:3306/annotationbatis";
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL).setUrl(dbUrl).setUsername("root").setPassword("1234")
				.setDriverName("com.mysql.jdbc.Driver");
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setCapitalMode(true).setEntityLombokModel(false).setDbColumnUnderline(true)
				.setNaming(NamingStrategy.underline_to_camel).setInclude(tableNames);// 修改替换成你需要的表名，多个表名传数组
		config.setActiveRecord(false).setAuthor("hugui").setOutputDir("E:\\").setFileOverride(true);
		if (!serviceNameStartWithI) {
			config.setServiceName("%sService");
		}

		new AutoGenerator().setGlobalConfig(config).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
				.setPackageInfo(
						new PackageConfig().setParent(packageName).setController("controller").setEntity("entity"))
				.execute();
	}

}
