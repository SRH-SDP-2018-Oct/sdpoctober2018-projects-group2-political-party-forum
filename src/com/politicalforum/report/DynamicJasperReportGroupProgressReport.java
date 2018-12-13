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

public class DynamicJasperReportGroupProgressReport {

	public static void generateGroupProgressReport(String groupProgressReportId) {

		try {
			ResultSet resultSet = null;
			Connection con = PoliticalPartyConnectionProvider.getPoliticalForumConnectionServices();
			resultSet = con.prepareStatement(
					"select groupprogressreportid, workname, workbody, contractor, startdate, enddate, dateofcompletion, fund, reportdate, groupdetailsid from groupprogressreport where GROUPPROGRESSREPORTID='"
							+ groupProgressReportId + "'")
					.executeQuery();
			FastReportBuilder fastReportBuilder = new FastReportBuilder();
			DynamicReport dynamicReport = fastReportBuilder
					.addColumn("Group Progress Report ID", "groupprogressreportid", String.class.getName(), 20)
					.addColumn("Work Name", "workname", String.class.getName(), 20)
					.addColumn("Work Body", "workbody", String.class.getName(), 20)
					.addColumn("Contractor", "contractor", String.class.getName(), 20)
					.addColumn("Start Date", "startdate", String.class.getName(), 20)
					.addColumn("End Date", "enddate", String.class.getName(), 20)
					.addColumn("Date of Completion", "dateofcompletion", String.class.getName(), 20)
					.addColumn("Fund", "fund", String.class.getName(), 20)
					.addColumn("Report Date", "reportdate", String.class.getName(), 20)
					.addColumn("Group Details ID", "groupdetailsid", String.class.getName(), 20)
					.setTitle("Group Progress Report").setSubtitle("This report was generated at:- " + new Date())
					.setPrintBackgroundOnOddRows(true).setUseFullPageWidth(true).build();
			JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(resultSet);
			JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(),
					jrResultSetDataSource);
			JasperViewer.viewReport(jasperPrint);

		} catch (ServiceNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
