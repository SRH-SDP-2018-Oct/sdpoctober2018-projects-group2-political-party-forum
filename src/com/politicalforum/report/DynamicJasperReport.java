package com.politicalforum.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import com.politicalforum.exceptions.ServicNotFoundException;
import com.politicalforum.providers.PoliticalPartyConnectionProvider;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class DynamicJasperReport {

	public static void main(String[] args) throws ServicNotFoundException {

		try {
			ResultSet resultSet = null;
			Connection con = PoliticalPartyConnectionProvider.getPoliticalForumConnectionServices();
			resultSet = con.prepareStatement(
					"select firstname, lastname, region, emailid, aadharnumber, gender, age, isanonymous from userdetails")
					.executeQuery();
			FastReportBuilder fastReportBuilder = new FastReportBuilder();
			DynamicReport dynamicReport = fastReportBuilder.addColumn("First Name", "firstname", String.class.getName(), 20)
											.addColumn("Last Name", "lastname", String.class.getName(), 20)
											.addColumn("Region", "region", String.class.getName(), 20)
											.addColumn("Email ID", "emailid", String.class.getName(), 20)
											.addColumn("Aadhar Number", "aadharnumber", String.class.getName(), 20)
											.addColumn("Gender", "gender", String.class.getName(), 20)
											.addColumn("Age", "age", String.class.getName(), 20)
											.addColumn("Is Anonymous", "isanonymous", String.class.getName(), 20)
											.setTitle("User Detail List").setSubtitle("This report was generated at:- "+ new Date())
											.setPrintBackgroundOnOddRows(true).setUseFullPageWidth(true).build();
			JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(resultSet);
			JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), jrResultSetDataSource);
			JasperViewer.viewReport(jasperPrint);											
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
