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

public class DynamicJasperReportGroupDetails {

	public static void main(String[] args)  throws ServiceNotFoundException {
		
		try {
			ResultSet resultSet = null;
			Connection con = PoliticalPartyConnectionProvider.getPoliticalForumConnectionServices();
			resultSet = con.prepareStatement(
					"select groupdetailsid, groupdetailsname, groupdetailsbody, userid, dateofcreation from groupdetails ")
					.executeQuery();
			FastReportBuilder fastReportBuilder = new FastReportBuilder();
			DynamicReport dynamicReport = fastReportBuilder.addColumn("Group Details ID", "groupdetailsid", String.class.getName(), 20)
											.addColumn("Group Details Name", "groupdetailsname", String.class.getName(), 20)
											.addColumn("Group Details Body", "groupdetailsbody", String.class.getName(), 20)
											.addColumn("User ID", "userid", String.class.getName(), 20)
											.addColumn("Date of Creation", "dateofcreation", String.class.getName(), 20)
											.setTitle("Group Detail List").setSubtitle("This report was generated at:- "+ new Date())
											.setPrintBackgroundOnOddRows(true).setUseFullPageWidth(true).build();
			JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(resultSet);
			JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), jrResultSetDataSource);
			JasperViewer.viewReport(jasperPrint);											
		} catch (Exception e) {
		// TODO Auto-generated method stub

		}

	}
}
