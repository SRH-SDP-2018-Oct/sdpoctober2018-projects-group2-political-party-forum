package com.politicalforum.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyConnectionProvider;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class DynamicJasperReportDiscussion {


	public static void main(String[] args)  throws ServiceNotFoundException {
		
		try {
			ResultSet resultSet = null;
			Connection con = PoliticalPartyConnectionProvider.getPoliticalForumConnectionServices();
			resultSet = con.prepareStatement(
					"select discussionid, discussionname, discussionbody, groupfollowersid, dateofdiscussion from discussion ")
					.executeQuery();
			FastReportBuilder fastReportBuilder = new FastReportBuilder();
			DynamicReport dynamicReport = fastReportBuilder.addColumn("Discussion ID", "discussionid", String.class.getName(), 50)
											.addColumn("Discussion Name", "discussionname", String.class.getName(), 50)
											.addColumn("Discussion Body", "discussionbody", String.class.getName(), 20)
											.addColumn("Group Followerd ID", "groupfollowersdid", String.class.getName(), 50)
											.addColumn("Date of Discussion", "dateofdiscussion", String.class.getName(), 20)
											.setTitle("Discussion").setSubtitle("This report was generated at:- "+ new Date())
											.setPrintBackgroundOnOddRows(true).setUseFullPageWidth(true).build();
			JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(resultSet);
			JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), jrResultSetDataSource);
			JasperViewer.viewReport(jasperPrint);											
		} catch (Exception e) {
		
		// TODO Auto-generated method stub

	}
	}
}
